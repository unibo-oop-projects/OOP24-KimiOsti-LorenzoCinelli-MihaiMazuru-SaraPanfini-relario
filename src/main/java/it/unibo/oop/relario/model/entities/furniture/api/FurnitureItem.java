package it.unibo.oop.relario.model.entities.furniture.api;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Interface for handling Furniture items.
 */
public interface FurnitureItem extends Entity {
    /**
     * Retrives the position of the furniture.
     * @return the position of the furniture in the map.
     */
    Position getPosition();

    /**
     * Says if a certain furniture is walkable or not.
     * @return true if the furniture is walkable, false otherwise.
     */
    boolean isWalkable();
}
