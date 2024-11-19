package it.unibo.oop.relario.model.entities.enemies;

/**
 * Enum representing the difficulty levels of enemies.
 * Each level has different values for life and damage.
 */

public enum DifficultyLevel {

    /** Easy Level. */
    EASY(30, 5),

    /** Medium Level. */
    MEDIUM(60, 10),

    /** Hard Level. */
    HARD(90, 20);

    private final int life;
    private final int damage;

    /**
     * Constructor that initializes the difficulty level with its specific values.
     * @param life of the enemy
     * @param damage of the enemy
     */
    DifficultyLevel(final int life, final int damage) {
        this.life = life;
        this.damage = damage;
    }

    /**
     * Retrieves the life associated with the difficulty level.
     * @return the life of the enemy
     */
    public int getLife() {
        return this.life;
    }

    /**
     * Retrieves the damage inflicted by the enemy, associated with the difficulty level.
     * @return the damage of the enemy
     */
    public int getDamage() {
        return this.damage;
    }

}
