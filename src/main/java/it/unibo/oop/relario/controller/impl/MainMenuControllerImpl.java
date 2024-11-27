package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.MainMenuController;
import it.unibo.oop.relario.view.impl.MainMenuView;
import it.unibo.oop.relario.view.impl.MainView;

/**
 * Implementation of the main menu controller.
 */
public final class MainMenuControllerImpl implements MainMenuController {
    private final MainView myFrame; 

    /**
     * Create a new view for the main menu.
     * @param myFrame is the main frame where the new view will be added.
     */
    public MainMenuControllerImpl(final MainView myFrame) {
        this.myFrame = myFrame;
        new MainMenuView(myFrame, this);
    }

    @Override
    public void progress(final MainView myFrame) {
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
