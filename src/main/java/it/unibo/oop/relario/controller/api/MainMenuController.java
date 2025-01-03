package it.unibo.oop.relario.controller.api;

import java.util.List;

import it.unibo.oop.relario.model.menu.MenuElement;

/**
 * Controller for managing mani menu options.
 */
public interface MainMenuController {

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
