package it.unibo.oop.relario.controller.api;

import it.unibo.oop.relario.model.entities.enemies.DifficultyLevel;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.living.MainCharacter;

/**
 * Controller for managing cambat options.
 */
public interface CombatController {
    /**
     * Initializes combat. 
     * @param player is the first fighter.
     * @param enemy is the second fighter.
     */
    void initializeCombat(MainCharacter player, Enemy enemy);

    /**
     * Retrieves combat state.
     * @return the combat state.
     */
    String getGombatState();
    
    /**
     * Retrieves the enemy's name.
     * @return enemy's name.
     */
    String getEnemyName();

    /**
     * Retrieves enemy's health.
     * @return enemy's health.
     */
    int getEnemyLife();

    /**
     * Retrieves player's health.
     * @return player's health.
     */
    int getPlayerLife();

    /**
     * Retrieves difficulty level of the enemy.
     * @return enemy's difficulty level.
     */
    DifficultyLevel getDifficultyLevel();

    /**
     * Handle attack and mercy request made by the player.
     * @param askingMercy is true if the player's asking for mercy, false otherwise.
     */
    void handleCombatAction(boolean attackAction);

}
