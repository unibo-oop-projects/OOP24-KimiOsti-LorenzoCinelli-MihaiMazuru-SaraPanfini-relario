package it.unibo.oop.relario.controller.api;

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

}
