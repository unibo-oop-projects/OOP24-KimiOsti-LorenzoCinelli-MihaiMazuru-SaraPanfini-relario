package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.InteractionsHandler;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.Interactions;
import it.unibo.oop.relario.model.map.Room;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation for the game's interactions handler.
 */
public final class InteractionsHandlerImpl implements InteractionsHandler {

    private final MainController controller;
    private final MainView view;

    /**
     * Constructor for the game's interaction handler.
     * @param controller the controller for return calls.
     * @param view the game view's access point to display the interaction's effects.
     */
    public InteractionsHandlerImpl(final MainController controller, final MainView view) {
        this.controller = controller;
        this.view = view;
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
            if (curRoom.getPlayer().getPosition().get().equals(curRoom.getExit())) {
                this.controller.moveToNextRoom();
            } else {
                /* [TODO]: handle interaction scenarios */
            }
        }

        this.controller.getGameController().resume(this.controller.getCurRoom().isPresent());
    }

}
