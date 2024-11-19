package it.unibo.oop.relario.model.entities.furniture;

import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Implementation of the Furniture items.
 */
public final class FurnitureItemImpl implements FurnitureItem {

    private final Position pos;
    private final boolean walkable;
    private final String name;

    FurnitureItemImpl(final Position pos, final boolean walkable, final String name) {
        this.pos = pos;
        this.walkable = walkable;
        this.name = name;
    }

    @Override
    public Position getPosition() {
        return new PositionImpl(this.pos.getX(), this.pos.getY());
    }

    @Override
    public boolean isWalkable() {
        return this.walkable;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
