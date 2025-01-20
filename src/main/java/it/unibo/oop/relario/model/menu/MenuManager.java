package it.unibo.oop.relario.model.menu;

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
        final MenuElement quitElem = new MenuElement(Command.QUIT.getName(), Command.QUIT);

        this.startMenu.addElem(new MenuElement(Command.PLAY.getName(), Command.PLAY));
        this.inGameMenu.addElem(new MenuElement(Command.CLOSE.getName(), Command.CLOSE));
        this.startMenu.addElem(quitElem);
        this.inGameMenu.addElem(quitElem);
    }

    /**
     * Retrieves the start menu.
     * @return the start menu.
     */
    public Menu getStartMenu() {
        return this.startMenu;
    }

    /**
     * Retrieves the in game menu.
     * @return the in game menu.
     */
    public Menu getInGameMenu() {
        return this.inGameMenu;
    }

}
