package it.unibo.oop.relario.controller.api;

import java.awt.Image;

import it.unibo.oop.relario.controller.impl.CombatAction;
import it.unibo.oop.relario.model.entities.enemies.DifficultyLevel;

/**
 * Controller for managing cambat options.
 */
public interface CombatController {
    /**
     * Initializes combat. 
     */
    void initializeCombat();

    /**
     * Retrieves combat state.
     * @return the combat state.
     */
    String getCombatState();

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
     * Retrieves the name of the item equipped by the player.
     * @return the item's name.
     */
    String getItem(); 

    /**
     * Retrieves the name of the armor equipped by the player.
     * @return the armor's name.
     */
    String getArmor();

    /**
     * Retrieves the enemy's texture.
     * @return the enemy's texture.
     */
    Image getEnemyTexture();

    /**
     * Handle attack, mercy and open inventory.
     * @param action perdormed by the player.
     */
    void handleAction(CombatAction action);

    /**
     * Shows the combat view.
     */
    void resumeCombat();

}
