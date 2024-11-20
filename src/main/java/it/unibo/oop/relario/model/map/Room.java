package it.unibo.oop.relario.model.map;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Interface for a room in the game map.
 */

public interface Room {

    /**
     * Retrieves the content of a specific cell of the room.
     * @param position of the cell that has to be checked
     * @return an optional containing the entity in the cell or an empty optional if the cell is empty
     */
    Optional<Entity> getCellContent(Position position);

}
