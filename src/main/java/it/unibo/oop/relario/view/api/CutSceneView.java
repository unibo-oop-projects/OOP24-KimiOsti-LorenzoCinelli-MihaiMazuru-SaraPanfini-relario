package it.unibo.oop.relario.view.api;

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
     * Shows the victory scene of the game.
     * @param isGoodEnding is used to choose between the good or bad ending.
     */
    void showVictoryScene(boolean isGoodEnding);

    /**
     * Shows the defeat scene of the game.
     */
    void showDefeatScene();
}
