package it.unibo.oop.relario.controller.api;

/**
 * Controller for managing mani menu options.
 */
public interface MainMenuController {

    /**
     * Makes the game progress to the game phase. 
     */
    void progress();

    /**
     * Shuts down the app.
     */
    void exit();
}
