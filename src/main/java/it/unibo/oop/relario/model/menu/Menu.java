package it.unibo.oop.relario.model.menu;

import java.util.List;

/**
 * Interface that models a generic menu.
 */
public interface Menu {

    /**
     * Adds a new menu element to the menu.
     * @param elem is the menu element to be added.
     */
    void addElem(MenuElement elem);

    /**
     * Retrieves all the menu elements in the menu.
     * @return a list of menu elements.
     */
    List<MenuElement> getElem();

}
