package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.GameController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.map.Room;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation for the Game Controller.
 */
public final class GameControllerImpl implements GameController {

    private final MainController controller;
    private final Room curRoom;
    private final MainView view;

    public GameControllerImpl(final MainController controller, final MainView view) {
        this.controller = controller;
        this.curRoom = controller.getCurRoom();
        this.view = view;
    }

}
