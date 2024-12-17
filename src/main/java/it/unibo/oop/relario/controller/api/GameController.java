package it.unibo.oop.relario.controller.api;

/**
 * Controller for managing game loop.
 */
public interface GameController {
    /**
     * Shows the Game view and starts the main loop.
     */
    void run();

    /**
     * Resumes the main loop, if the game is still running.
     * @param isExploring indicates whether the game is still running.
     */
    void resume(boolean isExploring);
}
