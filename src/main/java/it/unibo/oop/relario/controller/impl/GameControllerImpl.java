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

    /**
     * Constructor for the game controller.
     * @param controller its own container class, used to access the Model.
     * @param view the main View of the application, used to access the Game View.
     */
    public GameControllerImpl(final MainController controller, final MainView view) {
        this.controller = controller;
        this.curRoom = controller.getCurRoom();
        this.view = view;
    }

    @Override
    public void run() {
        this.view.showGameView();
        //[TODO] - starts the main loop.
    }

}
