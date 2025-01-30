package it.unibo.oop.relario.view.api;

import it.unibo.oop.relario.utils.impl.GameState;

/**
 * Interface for the view of cut scene.
 */
public interface CutSceneView {

    /**
     * Shows the start scene of the game.
     */
    void showStartScene();

    /**
     * Shows the passage scene from a room to the next one.
     */
    void showNextRoomScene();

    /**
     * Shows the final scene of the game depending on the final state.
     * @param state the final state of the game.
     */
    void showFinalScene(GameState state);
}
