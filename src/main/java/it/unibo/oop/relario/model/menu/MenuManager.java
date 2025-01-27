package it.unibo.oop.relario.model.menu;

import java.util.List;

/**
 * Manages the two different menu types.
 */
public class MenuManager {

    private final Menu startMenu;
    private final Menu inGameMenu;

    /**
     * Initiliazes the start menu and the in game menu.
     */
    public MenuManager() {
        this.startMenu = new MenuImpl();
        this.inGameMenu = new MenuImpl();

        this.startMenu.addElem(new MenuElement(Command.PLAY));
        this.startMenu.addElem(new MenuElement(Command.INFO));
        this.startMenu.addElem(new MenuElement(Command.QUIT));
        this.inGameMenu.addElem(new MenuElement(Command.CLOSE));
        this.inGameMenu.addElem(new MenuElement(Command.INFO));
        this.inGameMenu.addElem(new MenuElement(Command.QUIT));
    }

    /**
     * Retrieves the start menu.
     * @return the start menu.
     */
    public List<MenuElement> getStartMenu() {
        return this.startMenu.getElem();
    }

    /**
     * Retrieves the in game menu.
     * @return the in game menu.
     */
    public List<MenuElement> getInGameMenu() {
        return this.inGameMenu.getElem();
    }

}
