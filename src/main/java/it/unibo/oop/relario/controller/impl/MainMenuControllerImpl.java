package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.MainMenuController;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation of the main menu controller.
 */
public final class MainMenuControllerImpl implements MainMenuController {

    private final MainView view;

    /**
     * Create a new view for the main menu.
     */
    public MainMenuControllerImpl(final MainView view) {
        this.view = view;
        this.view.showPanel(GameState.MENU);
    }

    @Override
    public void progress() {
        this.view.showPanel(GameState.GAME);
    }

    @Override
    public void exit() {
        this.view.showPreviousPanel();
    }
}
