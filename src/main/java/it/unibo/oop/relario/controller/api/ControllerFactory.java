package it.unibo.oop.relario.controller.api;

/**
 * A factory for controllers in various phases of the game.
 */
public interface ControllerFactory {

    /**
     * Creates the main menu controller.
     * @return a controller handling main menu events.
     */
    Controller createMainMenuController();

    /**
     * Creates the game controller.
     * @return a controller handling standard game events.
     */
    Controller createGameController();

    /**
     * Creates the inventory menu controller.
     * @return a controller handling inventory menu events.
     */
    Controller createInventoryMenuController();

    /**
     * Creates the combat controller.
     * @return a controller handling combat interface events.
     */
    Controller createCombatController();
}
