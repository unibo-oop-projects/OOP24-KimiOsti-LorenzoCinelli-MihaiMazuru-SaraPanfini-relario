package it.unibo.oop.relario.model.menu;

/**
 * Implementation of the elements of a menu.
 */
public class MenuElement {

    private final String elemName;
    private final Command elemCommand;

    /**
     * Initializies a new menu element.
     * @param elemName is the menu element's name.
     * @param elemCommand is the command associated to the menu element. 
     */
    public MenuElement(final String elemName, final Command elemCommand) {
        this.elemName = elemName;
        this.elemCommand = elemCommand;
    }

    /**
     * Retrieves the name of the menu element.
     * @return menu element's name:
     */
    public String getElemName() {
        return this.elemName;
    }

    /**
     * Retrieves the command associated to the menu element.
     * @return menu element's command.
     */
    public Command getElemCommad() {
        return this.elemCommand;
    }

}
