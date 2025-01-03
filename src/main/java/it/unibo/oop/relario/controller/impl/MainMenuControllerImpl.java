package it.unibo.oop.relario.controller.impl;

import java.util.List;

import it.unibo.oop.relario.controller.api.MainMenuController;
import it.unibo.oop.relario.model.menu.MenuElement;
import it.unibo.oop.relario.model.menu.MenuManager;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation of the main menu controller.
 */
public final class MainMenuControllerImpl implements MainMenuController {

    private final MainView view;
    private final MenuManager menuModel;

    /**
     * Create a new view for the main menu.
     * @param view is the main view.
     */
    public MainMenuControllerImpl(final MainView view) {
        menuModel = new MenuManager();
        this.view = view;
        this.view.showPanel(GameState.MENU);
    }

    public List<MenuElement> getInGameMenuElements() {
        return this.menuModel.getInGameMenu().getElem();
    }

    public List<MenuElement> getStartMenuElements() {
        return this.menuModel.getStartMenu().getElem();
    }

}
