package it.unibo.oop.relario.model.menu;

/**
 * Implementation of the elements of a menu.
 */
public class MenuElement {

    private final Command elemCommand;

    /**
     * Initializies a new menu element.
     * @param elemCommand is the command associated to the menu element. 
     */
    public MenuElement(final Command elemCommand) {
        this.elemCommand = elemCommand;
    }

    /**
     * Retrieves the name of the menu element.
     * @return menu element's name:
     */
    public String getElemName() {
        return this.elemCommand.getName();
    }

    /**
     * Retrieves the command associated to the menu element.
     * @return menu element's command.
     */
    public Command getElemCommad() {
        return this.elemCommand;
    }

}
