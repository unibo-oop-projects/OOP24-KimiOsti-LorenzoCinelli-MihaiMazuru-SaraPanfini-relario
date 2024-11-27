package it.unibo.oop.relario.controller.api;

import it.unibo.oop.relario.view.impl.MainView;

/**
 * Controller for managing mani menu options.
 */
public interface MainMenuController {

    /**
     * Makes the game progress to the game phase. 
     * @param myFrame is the main frame that contains all game panels.
     */
    void progress(MainView myFrame);

    /**
     * Shuts down the app.
     */
    void exit();
}
