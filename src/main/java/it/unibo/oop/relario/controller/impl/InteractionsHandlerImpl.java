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

    /**
     * Constructor for the game's interaction handler.
     * @param controller the controller for return calls.
     */
    public InteractionsHandlerImpl(final MainController controller) {
        this.controller = controller;
    }

    @Override
    public void handleInteraction(final Room curRoom) {
        if (
            curRoom.getPlayer().getPosition().get().equals(curRoom.getExit())
            && (curRoom.getQuest().isEmpty() || curRoom.getQuest().get().isCompleted(curRoom))
        ) {
            this.controller.getCutSceneController().show(GameState.GAME);
        } else if (
            curRoom.getPlayer().getPosition().isPresent()
            && Interactions.canInteract(
                curRoom.getPlayer().getPosition().get(),
                curRoom.getPlayer().getDirection(),
                curRoom.getPopulation(),
                curRoom.getFurniture()
            )
        ) {
            final var entity = curRoom.getCellContent(
                curRoom.getPlayer().getDirection().move(curRoom.getPlayer().getPosition().get())
            );
            if (entity.isPresent()) {
                if (entity.get() instanceof Npc) {
                    this.interactWithNpc((Npc) entity.get(), curRoom);
                } else if (entity.get() instanceof Enemy) {
                    this.startEnemyCombat((Enemy) entity.get());
                } else if (entity.get() instanceof InteractiveFurniture) {
                    this.interactWithFurniture((InteractiveFurniture) entity.get(), curRoom);
                } else if (entity.get() instanceof WalkableFurniture
                    && ((WalkableFurniture) entity.get()).hasEnemy()) {
                    this.startEnemyCombat(((WalkableFurniture) entity.get()).removeEnemy());
                } else {
                    this.resumeGame();
                }
            }
        } else {
            this.resumeGame();
        }
    }

    private void interactWithNpc(final Npc npc, final Room curRoom) {
        final var output = npc.interact();
        if (output.getLoot().isPresent()) {
            if (curRoom.getPlayer().addToInventory(output.getLoot().get())) {
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
        this.controller.getCombatController().initializeCombat(enemy);
    }

    private void interactWithFurniture(final InteractiveFurniture furniture, final Room curRoom) {
        if (furniture.hasLoot()) {
            final var loot = furniture.dropLoot();
            if (curRoom.getPlayer().addToInventory(loot)) {
                this.showOutputText("Ecco qualcosa che mi tornerà utile!");
            } else {
                this.showOutputText("Sembra che io non abbia più spazio per portarmelo dietro...");
                furniture.addLoot(loot);
            }
        }
        this.resumeGame();
    }

    private void showOutputText(final String text) {
        final var gameView = this.controller.getMainView().getPanel(GameState.GAME);
        if (gameView instanceof GameView) {
            ((GameView) gameView).showInteractionText(text);
        }
    }

    private void resumeGame() {
        this.controller.getGameController().run(this.controller.getCurRoom().isPresent());
    }

}
