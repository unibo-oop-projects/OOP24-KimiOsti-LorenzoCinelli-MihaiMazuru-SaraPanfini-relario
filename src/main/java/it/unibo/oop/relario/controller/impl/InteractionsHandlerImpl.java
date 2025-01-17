package it.unibo.oop.relario.controller.impl;

import java.util.Map;
import java.util.function.Consumer;

import it.unibo.oop.relario.controller.api.InteractionsHandler;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.Interactions;
import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.model.entities.npc.Npc;
import it.unibo.oop.relario.model.map.Room;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation for the game's interactions handler.
 */
public final class InteractionsHandlerImpl implements InteractionsHandler {

    private final MainController controller;
    private final MainView view;
    private final Map<String, Consumer<Entity>> classNameToInteraction;

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
            Furniture.class.getName(), (e) -> this.interactWithFurniture((Furniture) e)
        );
    }

    @Override
    public void handleInteraction(final Room curRoom) {
        if (
            curRoom.getPlayer().getPosition().isPresent()
            && Interactions.canInteract(
                curRoom.getPlayer().getPosition().get(),
                curRoom.getPlayer().getDirection(),
                curRoom.getPopulation(),
                curRoom.getFurniture()
            )
        ) {
            if (curRoom.getPlayer().getPosition().get().equals(curRoom.getExit())
                && (curRoom.getQuest().isEmpty() || curRoom.getQuest().get().isCompleted())
            ) {
                this.controller.moveToNextRoom();
            } else {
                final var entity = curRoom.getCellContent(
                    curRoom.getPlayer().getDirection().move(curRoom.getPlayer().getPosition().get())
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
        /* [TODO]: visualizzare il dialogo e ottenere un eventuale oggetto */
    }

    private void startEnemyCombat(final Enemy enemy) {
        /* [TODO]: avviare il combattimento */
    }

    private void interactWithFurniture(final Furniture furniture) {
        /* [TODO]: interazione con la furniture, che sia walkable o interactive */
    }

}
