package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.InteractionsHandler;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.map.Room;

/**
 * Implementation for the game's interactions handler.
 */
public final class InteractionsHandlerImpl implements InteractionsHandler {

    @Override
    public void handleInteraction(final Room curRoom, final MainController controller) {
        /* [TODO] : add method implementation */

        controller.getGameController().resume(controller.getCurRoom().isPresent());
    }

}
