package it.unibo.oop.relario.model.entities.furniture;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Implementation of the Walkable furniture items.
 */
public class WalkableFurnitureImpl implements WalkableFurniture {

    private final Position pos;
    private final String name;
    private Optional<Entity> enemy;

    /**
     * Initialises a new walkable furniture item.
     * @param pos is the position of the furniture item in the map.
     * @param name is the name of the furniture item.
     * @param enemy is the enemy that can be inside the furniture item.
     */
    public WalkableFurnitureImpl(final Position pos, final String name, final Entity enemy) {
        this.pos = new PositionImpl(pos.getX(), pos.getY());
        this.name = name;
        this.enemy = Optional.of(enemy);
    }

    @Override
    public final Position getPosition() {
        return new PositionImpl(this.pos.getX(), this.pos.getY());
    }

    @Override
    public final boolean isWalkable() {
        return true;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final boolean hasEnemy() {
        return !enemy.isEmpty();
    }

    @Override
    public final void addEnemy(final Entity enemy) {
        this.enemy = Optional.of(enemy);
    }
}
