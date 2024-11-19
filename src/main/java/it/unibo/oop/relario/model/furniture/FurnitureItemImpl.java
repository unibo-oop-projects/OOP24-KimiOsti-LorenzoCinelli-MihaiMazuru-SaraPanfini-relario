package it.unibo.oop.relario.model.furniture;

import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Implementation of the Furniture items.
 */
public final class FurnitureItemImpl implements FurnitureItem {

    private final Position pos;
    private final boolean walkable;

    FurnitureItemImpl(final Position pos, final boolean walkable) {
        this.pos = pos;
        this.walkable = walkable;
    }

    @Override
    public Position getPosition() {
        return new PositionImpl(this.pos.getX(), this.pos.getY());
    }

    @Override
    public boolean isWalkable() {
        return this.walkable;
    }
}
