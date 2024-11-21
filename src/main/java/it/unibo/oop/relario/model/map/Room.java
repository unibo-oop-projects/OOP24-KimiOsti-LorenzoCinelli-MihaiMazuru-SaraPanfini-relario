package it.unibo.oop.relario.model.map;

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

    Dimension getDimension();

    Map<Position, LivingBeing> getPopulation();

    Map<Position, FurnitureItem> getFurniture();

    /**
     * Retrieves the content of a specific cell of the room.
     * @param position of the cell that has to be checked
     * @return an optional containing the entity in the cell or an empty optional if the cell is empty
     */
    Optional<Entity> getCellContent(Position position);

    boolean isCellAvailable(Position position);

    boolean addFurniture(Position position, FurnitureItem furniture);

    boolean addCharacter(Position position, LivingBeing character);

    void removeEntity(Position position);

    Room getNextRoom();

}
