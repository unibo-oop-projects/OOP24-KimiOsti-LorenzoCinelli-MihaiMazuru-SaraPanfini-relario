package it.unibo.oop.relario.model.entities.enemies;

import it.unibo.oop.relario.model.inventory.EffectType;

/**
 * Enumeration representing the different types of enemies.
 * Each type is associated with a specific effect and every enemy 
 * can drop only a reward with an effect that corresponds to it.
 */

public enum EnemyType {

    /** Represents a thief enemy, who can drop only inventory items with no effect. */
    THIEF(EffectType.NONE),

    /** Represents a soldier enemy, who can drop only inventory items with damage effect. */
    SOLDIER(EffectType.DAMAGE),

    /** Represents a knight enemy, who can drop only inventory items with protection effect. */
    KNIGHT(EffectType.PROTECTION),

    /** Represents a wizard enemy, who can drop only inventory items with healing effect. */
    WIZARD(EffectType.HEALING);

    private final EffectType effect;

    EnemyType(final EffectType effect) {
        this.effect = effect;
    }

    /**
     * Retrieves the effect associated with the enemy type.
     * @return the effect type of the enemy
     */
    public EffectType getEffect() {
        return this.effect;
    }
}
