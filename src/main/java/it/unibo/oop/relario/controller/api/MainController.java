package it.unibo.oop.relario.controller.api;

/**
 * Interface for container Controller class.
 */
public interface MainController {
    /**
     * @return the combat Controller.
     */
    CombatController getCombatController();

    /**
     * @return the main game Controller.
     */
    GameController getGameController();

    /**
     * @return the inventory Controller.
     */
    InventoryController getInventoryController();

    /**
     * @return the main menu Controller.
     */
    MainMenuController getMainMenuController();
}
