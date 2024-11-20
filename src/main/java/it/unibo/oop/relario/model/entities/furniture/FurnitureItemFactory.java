package it.unibo.oop.relario.model.entities.furniture;

import it.unibo.oop.relario.utils.api.Position;

/**
 * Interface for handling the creation of new funiture items.
 */
public interface FurnitureItemFactory {
    /**
     * Creates a new empty walkable furniture item.
     * @param pos is the position of the furniture item in the map.
     * @return a furniture item.
     */
    FurnitureItem createWalkableFurnitureItemEmpty(Position pos);

    /**
     * Creates a new walkable furniture item.
     * @param pos is the position of the furniture item in the map.
     * @return a furniture item.
     */
    FurnitureItem createWalkableFurnitureItem(Position pos);

    /**
     * Creates a new empty interactive furniture item.
     * @param pos is the position of the furniture item in the map.
     * @return a new furniture item.
     */
    FurnitureItem createInteractiveFurnitureItemEmpty(Position pos);

    /**
     * Creates a new interactive furniture item.
     * @param pos is the position of the furniture item in the map.
     * @return a new furniture item.
     */
    FurnitureItem createInteractiveFurnitureItem(Position pos);

    /**
     * Creates a new obstructing furniture item.
     * @param pos is the position of the furniture item in the map.
     * @return a furnture item.
     */
    FurnitureItem createObstructingFurnitureItem(Position pos);
}
