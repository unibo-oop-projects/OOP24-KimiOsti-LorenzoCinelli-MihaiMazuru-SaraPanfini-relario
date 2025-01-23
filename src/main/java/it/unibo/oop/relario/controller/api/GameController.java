package it.unibo.oop.relario.controller.api;

/**
 * Controller for managing game loop.
 */
public interface GameController extends Observer {
    /**
     * Starts the game loop, if the game is still running, otherwise ends the game.
     * @param isExploring indicates whether the game is still running.
     */
    void run(boolean isExploring);
}
