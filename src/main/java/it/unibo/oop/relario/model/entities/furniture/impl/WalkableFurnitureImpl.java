package it.unibo.oop.relario.model.entities.furniture.impl;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.furniture.api.WalkableFurniture;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Implementation of the Walkable furniture items.
 */
public class WalkableFurnitureImpl extends FurnitureImpl implements WalkableFurniture {

    private Optional<Enemy> enemy;

    /**
     * Initializes a new  empty walkable furniture item.
     * @param pos the position of the walkable furniture item.
     * @param name the name of the walkable furniture item.
     * @param description the description of the walkable furniture item.
     * @param type the type of the walkable furniture item.
     */
    public WalkableFurnitureImpl(final Position pos, final String name, final String description,
    final FurnitureType type) {
        super(pos, name, description, type);
        this.enemy = Optional.empty();
    }

    /**
     * Initializes a new  walkable furniture item with an enemy hidden inside.
     * @param pos the position of the walkable furniture item.
     * @param name the name of the walkable furniture item.
     * @param description the description of the walkable furniture item.
     * @param type the type of the walkable furniture item.
     * @param enemy is the enemy inside the furniture item.
     */
    public WalkableFurnitureImpl(final Position pos, final String name, final String description,
    final FurnitureType type, final Enemy enemy) {
        this(pos, name, description, type);
        this.enemy = Optional.of(enemy);
    }

    @Override
    public final boolean isWalkable() {
        return true;
    }

    @Override
    public final boolean isInteractive() {
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

}
