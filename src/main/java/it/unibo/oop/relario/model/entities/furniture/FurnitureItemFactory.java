package it.unibo.oop.relario.model.entities.furniture;

import it.unibo.oop.relario.utils.api.Position;

/**
 * Interface for handling the creation of new funiture items.
 */
public interface FurnitureItemFactory {
    
    /**
     * Creates a new walkable furniture item.
     * @return a furniture item.
     */
    FurnitureItem createWalkableFurnitureItem(Position pos);

    /**
     * Creates a new obstructing furniture item
     * @return a furnture item.
     */
    FurnitureItem createObstructingFurnitureItem(Position pos);

    /**
     * Creates a new interactive furniture item.
     * @return a new furniture item.
     */
    FurnitureItem createInteractiveFurnitureItem(Position pos);
}
