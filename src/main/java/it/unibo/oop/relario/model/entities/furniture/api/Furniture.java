package it.unibo.oop.relario.model.entities.furniture.api;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureType;

/**
 * Interface for handling furniture items.
 */
public interface Furniture extends Entity {

    /**
     * Retrieves the furniture item's name.
     * @return the name of the furniture item.
     */
    String getName();

    /**
     * Retrieves the description of the furniuture item.
     * @return the description of the furniture item.
     */
    String getDescription();

    /**
     * Retrieves furniture's type.
     * @return the furniture's type. 
     */
    FurnitureType getType();

    /**
     * Checks if a certain furniture is walkable.
     * @return true if the furniture is walkable, false otherwise.
     */
    boolean isWalkable();

    /**
     * Checks if the furniture item is interactive.
     * @return true if the furniture is interactive, false otherwise.
     */
    boolean isInteractive();

}
