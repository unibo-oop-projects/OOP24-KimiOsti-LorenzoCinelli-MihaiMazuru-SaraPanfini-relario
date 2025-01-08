package it.unibo.oop.relario.model.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of a generci menu.
 */
public class MenuImpl implements Menu {

    private final List<MenuElement> elements;

    /**
     * Initiliazes a new empty menu.
     */
    public MenuImpl() {
        this.elements = new ArrayList<>();
    }

    @Override
    public void addElem(MenuElement elem) {
        elements.add(elem);
    }

    @Override
    public List<MenuElement> getElem() {
        return Collections.unmodifiableList(elements);
    }
    
}
