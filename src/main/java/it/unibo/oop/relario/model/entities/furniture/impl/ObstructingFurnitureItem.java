package it.unibo.oop.relario.model.entities.furniture.impl;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.furniture.api.FurnitureItem;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Implementation of obstructing furniture items.
 */
public class ObstructingFurnitureItem implements FurnitureItem {

    private final Position pos;
    private final FurnitureType type;

    /**
     * Initializes a new obstructing furniture item.
     * @param pos is the position of the furniture item in the map.
     * @param type is the type of the furniture.
     */
    public ObstructingFurnitureItem(final Position pos, final FurnitureType type) {
        this.type = type;
        this.pos = new PositionImpl(pos.getX(), pos.getY());
    }

    @Override
    public final Optional<Position> getPosition() {
        return Optional.ofNullable(new PositionImpl(this.pos.getX(), this.pos.getY()));
    }

    @Override
    public final boolean isWalkable() {
        return false;
    }

    /**
     * Says if the furniture item is interactive. In this case retrives always false.
     * @return false.
     */
    @Override
    public boolean isInteractive() {
        return false;
    }

    @Override
    public FurnitureType getType() {
        return this.type;
    }
}
