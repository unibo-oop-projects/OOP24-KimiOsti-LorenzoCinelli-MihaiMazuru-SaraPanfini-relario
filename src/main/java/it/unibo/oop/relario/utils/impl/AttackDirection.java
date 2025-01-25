package it.unibo.oop.relario.utils.impl;

/**
 * Enumeration that tracks the direction of attacks in combat enviroment.
 */
public enum AttackDirection {

    /** There is no attack. */
    NONE,

    /** The attack is from the enemy to the player. */
    FROM_ENEMY_TO_PLAYER,

    /** The attack is from the player to the enemy. */
    FROM_PLAYER_TO_ENEMY,
}
