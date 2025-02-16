package it.unibo.oop.relario.controller.api;

import it.unibo.oop.relario.utils.impl.GameState;

/**
 * Interface for cut scene controller.
 */
public interface CutSceneController {

    /**
     * Shows the view and finally calls the controller for the next state.
     * @param state is the current state of the game.
     */
    void show(GameState state);

    /**
     * Updates the game data.
     * @param nextState is the next state of the game.
     */
    void progressGame(GameState nextState);

    /**
     * Calls the next controller based on the next state.
     * @param nextState is the next state of the game.
     */
    void progressView(GameState nextState);
}
