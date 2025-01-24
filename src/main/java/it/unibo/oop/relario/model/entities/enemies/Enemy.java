package it.unibo.oop.relario.model.entities.enemies;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.inventory.InventoryItem;

/**
 * Interface that represents an enemy.
 */

public interface Enemy extends LivingBeing {

    /**
     * Retrieves the description of the enemy.
     * @return the description of the enemy
     */
    String getDescription();

    /**
     * Retrieves the current life of the enemy.
     * @return the life of the enemy
     */
    int getLife();


    /**
     * Retrieves the damage inflicted by the enemy.
     * @return the damage of the enemy
     */
    int getDamage();

    /**
     * Retrieves the difficulty level of the enemy.
     * @return the difficulty of the enemy
     */
    DifficultyLevel getDifficulty();


    /**
     * Retrieves the reward dropped by the enemy when defeated.
     * @return an inventory item as a reward
     */
    Optional<InventoryItem> getReward();

    /**
     * Retrieves the type of the enemy.
     * @return the type of the enemy
     */
    EnemyType getType();

    /**
     * States whether the enemy is merciful.
     * @return true if the enemy is merciful, false otherwise
     */
    boolean isMerciful();

    /**
     * Reduces the enemy's life when it's attacked by the player and checks if it is still alive.
     * @param playerDamage the damage of the player
     */
    void attacked(int playerDamage);

}
