package it.unibo.oop.relario.controller.api;

import it.unibo.oop.relario.utils.impl.GameState;

/**
 * Controller for managing game loop.
 */
public interface GameController extends Observer {
    /**
     * Shows the Game view and starts the main loop.
     */
    void run();

    /**
     * Resumes the main loop, if the game is still running.
     * @param isExploring indicates whether the game is still running.
     */
    void resume(boolean isExploring);

    /**
     * Changes the game state, and shows the correct view accordingly.
     * @param state the new state to which the transition is required.
     */
    void changeGameState(GameState state);
}
