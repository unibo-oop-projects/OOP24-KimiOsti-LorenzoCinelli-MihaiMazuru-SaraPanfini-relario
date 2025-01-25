package it.unibo.oop.relario.model.entities.enemies;

import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.utils.api.Position;

/**
 * An interface modelling a factory of enemies.
 */
public interface EnemyFactory {

    /**
     * Creates a random enemy.
     * @param position of the enemy
     * @return a new random enemy with random features
     */
    Enemy createRandomEnemy(Position position);

    /**
     * Creates an enemy with a specific reward.
     * @param position of the enemy
     * @param reward dropped by the enemy
     * @return a new enemy with the specified reward
     */
    Enemy createEnemyWithReward(Position position, InventoryItemType reward);

    /**
     * Creates an enemy of a specific type.
     * @param type of the enemy
     * @param position of the enemy
     * @return a new enemy of the specified type
     */
    Enemy createEnemyByType(EnemyType type, Position position);

    /**
     * Creates an enemy carrying no loot.
     * @param type of the enemy.
     * @param position of the enemy.
     * @return a new enemy of the specified type, carrying no loot.
     */
    Enemy createEnemyByTypeEmpty(EnemyType type, Position position);

}
