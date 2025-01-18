package it.unibo.oop.relario.controller.impl;

import java.util.Map;
import java.util.function.Consumer;

import it.unibo.oop.relario.controller.api.InteractionsHandler;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.Interactions;
import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.furniture.api.InteractiveFurniture;
import it.unibo.oop.relario.model.entities.furniture.api.WalkableFurniture;
import it.unibo.oop.relario.model.entities.npc.Npc;
import it.unibo.oop.relario.model.map.Room;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;
import it.unibo.oop.relario.view.impl.GameView;

/*
 * [TODO]:
 * gestione interazione con la furniture
 * gestione avvio combattimento
 * gestione ripresa del game loop a seconda dello scenario
 */

/**
 * Implementation for the game's interactions handler.
 */
public final class InteractionsHandlerImpl implements InteractionsHandler {

    private final MainController controller;
    private final MainView view;
    private final Map<String, Consumer<Entity>> classNameToInteraction;
    private Room curRoom;

    /**
     * Constructor for the game's interaction handler.
     * @param controller the controller for return calls.
     * @param view the game view's access point to display the interaction's effects.
     */
    public InteractionsHandlerImpl(final MainController controller, final MainView view) {
        this.controller = controller;
        this.view = view;
        this.classNameToInteraction = Map.of(
            Npc.class.getName(), (e) -> this.interactWithNpc((Npc) e),
            Enemy.class.getName(), (e) -> this.startEnemyCombat((Enemy) e),
            InteractiveFurniture.class.getName(), (e) -> this.interactWithFurniture((InteractiveFurniture) e),
            WalkableFurniture.class.getName(), (e) -> this.startEnemyCombat(null/* ((WalkableFurniture) e).removeEnemy() */)
        );
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
                this.controller.moveToNextRoom();
            } else {
                final var entity = this.curRoom.getCellContent(
                    this.curRoom.getPlayer().getDirection().move(this.curRoom.getPlayer().getPosition().get())
                );
                if (entity.isPresent()) {
                    this.classNameToInteraction.get(entity.get().getClass().getName()).accept(entity.get());
                }
            }
        }

        /* [TODO]: risolvi il fatto che qui, se parte un combattimento, comunque fai resume del game loop */
        this.controller.getGameController().resume(this.controller.getCurRoom().isPresent());
    }

    private void interactWithNpc(final Npc npc) {
        final var output = npc.interact();
        if (output.getLoot().isPresent()) {
            this.curRoom.getPlayer().addToInventory(output.getLoot().get());
        }
        ((GameView) this.view.getPanel(GameState.GAME.getState())).showInteractionText(output.getDialogue());
    }

    private void startEnemyCombat(final Enemy enemy) {
        /* [TODO]: avviare il combattimento */
    }

    private void interactWithFurniture(final InteractiveFurniture furniture) {
        /* [TODO]: gestire l'interazione con l'arredamento */
    }

}
