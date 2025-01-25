package it.unibo.oop.relario.controller.impl;

import java.util.List;

import it.unibo.oop.relario.controller.api.MainController;
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
    private final MainController controller;
    private final MenuManager menuModel;
    private GameState prevState;

    /**
     * Create a new view for the main menu.
     * @param controller is the main controller.
     */
    public MenuControllerImpl(final MainController controller) {
        menuModel = new MenuManager();
        this.controller = controller;
        this.view = this.controller.getMainView();
        this.prevState = GameState.NONE;
    }

    @Override
    public void showMenu(final GameState menuType, final GameState prevState) {
        this.prevState = prevState;
        this.view.showPanel(menuType);
    }

    @Override
    public List<MenuElement> getInGameMenuElements() {
        return this.menuModel.getInGameMenu();
    }

    @Override
    public List<MenuElement> getStartMenuElements() {
        return this.menuModel.getStartMenu();
    }

    @Override
    public void notify(final Event event) {
        if (event.equals(Event.ESCAPE) && !this.prevState.equals(GameState.NONE)) {
            switch (prevState) {
                case INVENTORY -> this.controller.getInventoryController().init(GameState.MENU_IN_GAME);
                case COMBAT -> this.controller.getCombatController().resumeCombat();
                case GAME -> this.controller.getGameController().run(true);
                default -> throw new IllegalArgumentException();
            }
        }
    }

}
