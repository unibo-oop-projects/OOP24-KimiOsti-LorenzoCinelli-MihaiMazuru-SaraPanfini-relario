package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.GameController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation for the Game Controller.
 */
public final class GameControllerImpl implements GameController {

    private final MainController controller;
    private final MainView view;
    private boolean exploring;

    /**
     * Constructor for the game controller.
     * @param controller its own container class, used to access the Model.
     * @param view the main View of the application, used to access the Game View.
     */
    public GameControllerImpl(final MainController controller, final MainView view) {
        this.controller = controller;
        this.view = view;
        this.exploring = false;
    }

    @Override
    public void run() {
        this.exploring = true;
        this.view.showPanel(GameState.GAME);
        this.mainLoop();
    }

    @Override
    public void resume(boolean isExploring) {
        this.exploring = isExploring;
        if (!this.exploring) {
            this.view.showPanel(GameState.GAME_OVER);
        } else {
            this.mainLoop();
        }
    }

    private void mainLoop() {
    }
}
