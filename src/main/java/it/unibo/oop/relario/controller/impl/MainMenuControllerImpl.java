package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.MainMenuController;

/**
 * Implementation of the main menu controller.
 */
public final class MainMenuControllerImpl implements MainMenuController {

    /**
     * Create a new view for the main menu.
     */
    public MainMenuControllerImpl() {
    }

    @Override
    public void progress() {
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
