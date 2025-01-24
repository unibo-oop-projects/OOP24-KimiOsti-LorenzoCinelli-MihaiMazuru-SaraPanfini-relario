package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.InteractionsHandler;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.Interactions;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.furniture.api.InteractiveFurniture;
import it.unibo.oop.relario.model.entities.furniture.api.WalkableFurniture;
import it.unibo.oop.relario.model.entities.npc.Npc;
import it.unibo.oop.relario.model.map.Room;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.impl.GameView;

/**
 * Implementation for the game's interactions handler.
 */
public final class InteractionsHandlerImpl implements InteractionsHandler {

    private final MainController controller;
    private final GameView view;
    private Room curRoom;

    /**
     * Constructor for the game's interaction handler.
     * @param controller the controller for return calls.
     * @param view the game view's access point to display the interaction's effects.
     */
    public InteractionsHandlerImpl(final MainController controller, final GameView view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void handleInteraction(final Room curRoom) {
        this.curRoom = curRoom;
        if (
            curRoom.getPlayer().getPosition().isPresent()
            && Interactions.canInteract(
                this.curRoom.getPlayer().getPosition().get(),
                this.curRoom.getPlayer().getDirection(),
                this.curRoom.getPopulation(),
                this.curRoom.getFurniture()
            )
        ) {
            if (this.curRoom.getPlayer().getPosition().get().equals(this.curRoom.getExit())
                && (this.curRoom.getQuest().isEmpty() || this.curRoom.getQuest().get().isCompleted())
            ) {
                this.controller.getCutSceneController().show(GameState.GAME);
            } else {
                final var entity = this.curRoom.getCellContent(
                    this.curRoom.getPlayer().getDirection().move(this.curRoom.getPlayer().getPosition().get())
                );
                if (entity.isPresent()) {
                    if (entity.get() instanceof Npc) {
                        this.interactWithNpc((Npc) entity.get());
                    } else if (entity.get() instanceof Enemy) {
                        this.startEnemyCombat((Enemy) entity.get());
                    } else if (entity.get() instanceof InteractiveFurniture) {
                        this.interactWithFurniture((InteractiveFurniture) entity.get());
                    } else if (entity.get() instanceof WalkableFurniture) {
                        this.startEnemyCombat(((WalkableFurniture) entity.get()).removeEnemy());
                    }
                }
            }
        }
    }

    private void interactWithNpc(final Npc npc) {
        final var output = npc.interact();
        if (output.getLoot().isPresent()) {
            if (this.curRoom.getPlayer().addToInventory(output.getLoot().get())) {
                npc.confirmLootTaken();
                this.showOutputText(output.getDialogue());
            } else {
                this.showOutputText("Sembra che io non abbia spazio per questo oggetto...");
            }
        } else {
            this.showOutputText(output.getDialogue());
        }
        this.resumeGame();
    }

    private void startEnemyCombat(final Enemy enemy) {
        this.controller.getCombatController().initializeCombat(this.curRoom.getPlayer(), enemy);
    }

    private void interactWithFurniture(final InteractiveFurniture furniture) {
        if (furniture.hasLoot()) {
            final var loot = furniture.dropLoot();
            if (this.curRoom.getPlayer().addToInventory(loot)) {
                this.showOutputText("Ecco qualcosa che mi tornerà utile!");
            } else {
                this.showOutputText("Sembra che io non abbia più spazio per portarmelo dietro...");
                furniture.addLoot(loot);
            }
        }
        this.resumeGame();
    }

    private void showOutputText(final String text) {
        this.view.showInteractionText(text);
    }

    private void resumeGame() {
        this.controller.getGameController().run(this.controller.getCurRoom().isPresent());
    }

}
