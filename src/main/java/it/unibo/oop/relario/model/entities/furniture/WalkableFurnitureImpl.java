package it.unibo.oop.relario.model.entities.furniture;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.utils.api.Position;

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
        this.pos = pos;
        this.name = name;
        this.enemy = Optional.of(enemy);
    }

    @Override
    public Position getPosition() {
        return this.pos;
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean hasEnemy() {
        return (!enemy.isEmpty());
    }

    @Override
    public void addEnemy(final Entity enemy) {
        this.enemy = Optional.of(enemy);
    }
}
