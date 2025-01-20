package it.unibo.oop.relario.model.entities.furniture.impl;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Implemention for handling furniture item. 
 * This abstract class implements the essentials getter methods and lets to the subclasses 
 * the implementation of the following abstract methods: isWalkable and isInteractive.
 */
public abstract class FurnitureImpl implements Furniture {

    private final Position pos;
    private final String name;
    private final String description;
    private final FurnitureType type;

    /**
     * Initializes a new furniture item.
     * @param pos the position of the furniture item.
     * @param name the name of the furniture item.
     * @param description the description of the furniture item.
     * @param type the type of the furniture item.
     */
    public FurnitureImpl(final Position pos, final String name, final String description,
    final FurnitureType type) {
        this.pos = new PositionImpl(pos.getX(), pos.getY());
        this.name = name;
        this.description = description;
        this.type = type;
    }

    @Override
    public final Optional<Position> getPosition() {
        return Optional.ofNullable(new PositionImpl(this.pos.getX(), this.pos.getY()));
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final FurnitureType getType() {
        return this.type;
    }

    @Override
    public abstract boolean isWalkable();

    @Override
    public abstract boolean isInteractive();

}
