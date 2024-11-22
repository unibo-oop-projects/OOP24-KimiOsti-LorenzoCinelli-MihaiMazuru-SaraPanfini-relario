package it.unibo.oop.relario.model.entities.furniture.api;

import it.unibo.oop.relario.model.entities.Entity;

/**
 * Interface for handling Furniture items.
 */
public interface FurnitureItem extends Entity {
    /**
     * Says if a certain furniture is walkable or not.
     * @return true if the furniture is walkable, false otherwise.
     */
    boolean isWalkable();

    /**
     * Says if the furniture item is interactive.
     * @return true if the main character can interact with the furniture item.
     */
    boolean isInteractive();
}
