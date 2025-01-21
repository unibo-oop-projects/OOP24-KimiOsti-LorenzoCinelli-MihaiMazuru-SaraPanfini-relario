package it.unibo.oop.relario.controller.impl;

import java.util.List;

import it.unibo.oop.relario.controller.api.MenuController;
import it.unibo.oop.relario.model.menu.MenuElement;
import it.unibo.oop.relario.model.menu.MenuManager;
import it.unibo.oop.relario.utils.impl.Event;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation of the main menu controller.
 */
public final class MenuControllerImpl implements MenuController {

    private final MainView view;
    private final MenuManager menuModel;

    /**
     * Create a new view for the main menu.
     * @param view is the main view.
     */
    public MenuControllerImpl(final MainView view) {
        menuModel = new MenuManager();
        this.view = view;
    }

    @Override
    public void showMenu(final GameState menuType) {
        /* [TODO]: implement method */
    }

    @Override
    public List<MenuElement> getInGameMenuElements() {
        return this.menuModel.getInGameMenu().getElem();
    }

    @Override
    public List<MenuElement> getStartMenuElements() {
        return this.menuModel.getStartMenu().getElem();
    }

    @Override
    public void notify(final Event event) {
        if (event.equals(Event.ESCAPE) 
        && this.view.getCurrentPanel().equals(GameState.MENU_IN_GAME)) {
            this.view.showPreviousPanel();
        }
    }

}
