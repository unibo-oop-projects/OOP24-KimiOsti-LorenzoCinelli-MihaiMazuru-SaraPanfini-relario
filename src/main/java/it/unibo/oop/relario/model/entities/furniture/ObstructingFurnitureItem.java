package it.unibo.oop.relario.model.entities.furniture;

import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Implementation of obstructing furniture items.
 */
public class ObstructingFurnitureItem implements FurnitureItem {

    private final Position pos;
    private final String name;

    /**
     * Initialises a new obstructing furniture item.
     * @param pos is the position of the furniture item in the map.
     * @param name is the name of the furniture item.
     */
    public ObstructingFurnitureItem(final Position pos, final String name) {
        this.pos = new PositionImpl(pos.getX(), pos.getY());
        this.name = name;
    }

    @Override
    public final Position getPosition() {
        return new PositionImpl(this.pos.getX(), this.pos.getY());
    }

    @Override
    public final boolean isWalkable() {
        return false;
    }

    @Override
    public final String getName() {
        return this.name;
    }
}
