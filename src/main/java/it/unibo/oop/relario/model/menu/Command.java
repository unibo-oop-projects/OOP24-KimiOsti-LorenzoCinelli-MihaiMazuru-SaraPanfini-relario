package it.unibo.oop.relario.model.menu;

/**
 * Different commands that be associated to a menu element.
 */
public enum Command {

    /** Starts a new game. */
    PLAY("Play"),

    /** Quit he game. */
    QUIT("Quit"),

    /** Close the menu. */
    CLOSE("Close");

    private final String name;

    /**
     * Initializes a new command.
     * @param name is the command's name.
     */
    Command(final String name) {
        this.name = name;
    }

    /**
     * Retrieves the name associated to the command.
     * @return the name's command.
     */
    public String getName() {
        return this.name;
    }

}
