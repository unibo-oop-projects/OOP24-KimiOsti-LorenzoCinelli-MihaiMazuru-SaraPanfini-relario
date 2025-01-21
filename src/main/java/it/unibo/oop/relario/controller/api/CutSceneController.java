package it.unibo.oop.relario.controller.api;

import it.unibo.oop.relario.utils.impl.GameState;

/**
 * Interface for cut scene controller.
 */
public interface CutSceneController {

    /**
     * Shows the view and finally calls the controller for the next state.
     * @param nextState is the next state of the game after the cut scene.
     */
    void show(GameState nextState);
}
