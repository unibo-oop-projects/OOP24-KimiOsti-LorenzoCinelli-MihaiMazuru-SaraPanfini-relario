package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.api.MainMenuController;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation of the main menu controller.
 */
public final class MainMenuControllerImpl implements MainMenuController {

    private final MainView view;
    private final MainController controller;

    /**
     * Create a new view for the main menu.
     * @param view is the main view.
     * @param controller is the main controller,
     */
    public MainMenuControllerImpl(final MainView view, final MainController controller) {
        this.view = view;
        this.controller = controller;
        this.view.showPanel(GameState.MENU);
    }

    @Override
    public void progress() {
        this.controller.getGameController().run();
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
