package it.unibo.oop.relario.model.map;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.quest.Quest;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Interface representing a room in the game map.
 */

public interface Room {

    /**
     * Retrieves the current room instance.
     * @return the current room
     */
    Room getRoom();

    /**
     * Retrieves the main character in the room.
     * @return the player
     */
    MainCharacter getPlayer();

    /**
     * Retrieves the quest assigned to the room, if any.
     * @return an optional containing the quest or empty if no quest is assigned
     */
    Optional<Quest> getQuest();

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
    Map<Position, Furniture> getFurniture();

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
     * Adds a specified entity in a specified position of the room.
     * @param position where the entity has to be placed
     * @param entity that has to be added
     */
    void addEntity(Position position, Entity entity);

    /**
     * Removes an entity from a specified position.
     * @param position of the entity
     */
    void removeEnemy(Position position);

    /**
     * Assigns a quest to the room.
     * @param quest to assign
     */
    void setQuest(Optional<Quest> quest);

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

    /**
     * Retrieves the entry position of the room.
     * @return the entry position
     */
    Position getEntry();

    /**
     * Updates the state of the room.
     */
    void update();

    List<Position> getCellsByState(CellState state);

}
