package it.unibo.oop.relario.model.entities.furniture.api;

import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureType;

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
    void addEnemy(Enemy enemy);

    /**
     * Removes the enemy from inside the walkable furniture item.
     */
    void removeEnemy();

    /**
     * 
     */
    FurnitureType getType();
}
