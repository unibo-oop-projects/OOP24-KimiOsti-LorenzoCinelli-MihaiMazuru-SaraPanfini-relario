package it.unibo.oop.relario.model.entities.furniture.impl;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.furniture.api.WalkableFurniture;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Implementation of the Walkable furniture items.
 */
public class WalkableFurnitureImpl implements WalkableFurniture {

    private final Position pos;
    private Optional<Enemy> enemy;
    private final FurnitureType type;

    /**
     * Initializes a new empty walkable furniture item.
     * @param pos is the position of the furniture item in the map.
     * @param type is the type of the furniture.
     */
    public WalkableFurnitureImpl(final Position pos, final FurnitureType type) {
        this.type = type;
        this.pos = new PositionImpl(pos.getX(), pos.getY());
        this.enemy = Optional.empty();
    }

    /**
     * Initializes a new walkable furniture item.
     * @param pos is the position of the furniture item in the map.
     * @param enemy is the enemy inside the furniture item.
     * @param type is the type of the furniture.
     */
    public WalkableFurnitureImpl(final Position pos, final Enemy enemy, 
    final FurnitureType type) {
        this(pos, type);
        this.enemy = Optional.of(enemy);
    }

    @Override
    public final Optional<Position> getPosition() {
        return Optional.ofNullable(new PositionImpl(this.pos.getX(), this.pos.getY()));
    }

    @Override
    public final boolean isWalkable() {
        return true;
    }

    @Override
    public final boolean hasEnemy() {
        return !enemy.isEmpty();
    }

    @Override
    public final void addEnemy(final Enemy enemy) {
        this.enemy = Optional.of(enemy);
    }

    @Override
    public final void removeEnemy() {
        this.enemy = Optional.empty();
    }

    @Override
    public final boolean isInteractive() {
        return true;
    }

    @Override
    public FurnitureType getType() {
        return this.type;
    }
}
