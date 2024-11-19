package it.unibo.oop.relario.model.entities.furniture;

import it.unibo.oop.relario.model.entities.Entity;

/**
 * Interface for handling walkable furniture items.
 */
public interface WalkableFurniture extends FurnitureItem {
    /**
     * Says if the furniture contains an enemy.
     * @return true if the furniture contains an enemt, false otherwise.
     */
    boolean hasEnemy();

    /**
     * Adds an enemy into the furniture.
     * @param enemy is the enemy to add to the furniture.
     */
    void addEnemy(Entity enemy);
}
