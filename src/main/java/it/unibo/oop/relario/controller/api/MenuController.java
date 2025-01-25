package it.unibo.oop.relario.controller.api;

import java.util.List;

import it.unibo.oop.relario.model.menu.MenuElement;
import it.unibo.oop.relario.utils.impl.GameState;

/**
 * Controller for managing mani menu options.
 */
public interface MenuController extends Observer {

    /**
     * Shows the menu indicated by the state.
     * @param menuType the type of menu to be displayed.
     * @param prevState the previous state.
     */
    void showMenu(GameState menuType, GameState prevState);

    /**
     * Retrieves all the menu elements in the in game menu.
     * @return a list of menu elements.
     */
    List<MenuElement> getInGameMenuElements();

    /**
     * Retrieves all the menu elements in the start menu.
     * @return a list of menu elements.
     */
    List<MenuElement> getStartMenuElements();

}
