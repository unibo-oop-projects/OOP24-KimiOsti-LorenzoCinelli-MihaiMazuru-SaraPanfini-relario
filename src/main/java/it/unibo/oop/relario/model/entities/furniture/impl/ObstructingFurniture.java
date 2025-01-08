package it.unibo.oop.relario.model.entities.furniture.impl;

import it.unibo.oop.relario.utils.api.Position;

/**
 * Implementation of obstructing furniture items.
 */
public class ObstructingFurniture extends FurnitureImpl {

    /**
     * Initializes a new obstructing furniture item. It's purely decorative.
     * @param pos the position of the obstructing furniture item.
     * @param name the name of the obstructing furniture item.
     * @param description the description of the obstructing furniture item.
     * @param type the type of the obstructing furniture item.
     */
    public ObstructingFurniture(final Position pos, final String name, final String description,
    final FurnitureType type) {
        super(pos, name, description, type);
    }

    @Override
    public final boolean isWalkable() {
        return false;
    }

    @Override
    public final boolean isInteractive() {
        return false;
    }

}
