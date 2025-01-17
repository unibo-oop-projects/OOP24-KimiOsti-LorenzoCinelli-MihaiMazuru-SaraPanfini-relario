package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.InteractionsHandler;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.Interactions;
import it.unibo.oop.relario.model.map.Room;

/**
 * Implementation for the game's interactions handler.
 */
public final class InteractionsHandlerImpl implements InteractionsHandler {

    @Override
    public void handleInteraction(final Room curRoom, final MainController controller) {
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
                controller.moveToNextRoom();
            } else {
                /* [TODO]: handle interaction scenarios */
            }
        }

        controller.getGameController().resume(controller.getCurRoom().isPresent());
    }

}
