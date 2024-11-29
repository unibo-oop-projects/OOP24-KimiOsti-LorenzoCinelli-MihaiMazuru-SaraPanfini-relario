package it.unibo.oop.relario.controller.api;

import it.unibo.oop.relario.model.map.Room;

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

    /**
     * @return the current room in the gameplay.
     */
    Room getCurRoom();

    /**
     * Moves to the next room.
     * @return the next room.
     */
    Room moveToNextRoom();
}
