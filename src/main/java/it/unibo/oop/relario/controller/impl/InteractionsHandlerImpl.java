package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.InteractionsHandler;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.Interactions;
import it.unibo.oop.relario.model.map.Room;

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
