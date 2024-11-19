package it.unibo.oop.relario.model.entities.furniture;

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
     */
    void addEnemy();
}
