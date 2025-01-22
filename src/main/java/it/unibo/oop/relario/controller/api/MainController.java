package it.unibo.oop.relario.controller.api;

import java.util.Optional;

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
    MenuController getMenuController();

    /**
     * @return the cut scene Controller.
     */
    CutSceneController getCutSceneController();

    /**
     * @return the current room in the gameplay.
     */
    Optional<Room> getCurRoom();

    /**
     * Moves to the next room.
     */
    void moveToNextRoom();
}
