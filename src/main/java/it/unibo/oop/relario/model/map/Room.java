package it.unibo.oop.relario.model.map;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureItem;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Interface for a room in the game map.
 */

public interface Room {

    /**
     * Retrieves the dimension of the room.
     * @return the dimension of the room
     */
    Dimension getDimension();

    /**
     * Retrieves all the living beings in the room and their positions.
     * @return a map of positions and living beings
     */
    Map<Position, LivingBeing> getPopulation();

    /**
     * Retrieves all the furniture in the room and their positions.
     * @return a map of positions and furniture items
     */
    Map<Position, FurnitureItem> getFurniture();

    /**
     * Retrieves the content of a specific cell of the room.
     * @param position of the cell that has to be checked
     * @return an optional containing the entity in the cell, or an empty optional if the cell is empty
     */
    Optional<Entity> getCellContent(Position position);

    /**
     * Checks if the given cell of the room is available or not.
     * @param position that has to be checked
     * @return true if the cell is available, false otherwise
     */
    boolean isCellAvailable(Position position);

    /**
     * Adds a specified furniture item in a specified position of the room.
     * @param position where the furniture item has to be placed
     * @param furniture that has to be added
     */
    void addFurniture(Position position, FurnitureItem furniture);

    /**
     * Adds a specified living being in a specified position of the room.
     * @param position where the living being has to be placed
     * @param character that has to be added
     */
    void addCharacter(Position position, LivingBeing character);

    /**
     * Removes an entity from a specified position.
     * @param position of the entity
     */
    void removeEntity(Position position);

    /**
     * Retrieves the list of boundary cells of the room.
     * @return a list of positions
     */
    List<Position> getPerimeter();

    /**
     * Retrieves the list of inner cells of the room.
     * @return a list of positions
     */
    List<Position> getInnerCells();

    /**
     * Checks if a specified position is within the room's edges.
     * @param position that has to be checked
     * @return true if the position is valid, false otherwise
     */
    boolean isPositionValid(Position position);

    /**
     * Retrieves the exit position of the room.
     * @return the exit position
     */
    Position getExit();

}
