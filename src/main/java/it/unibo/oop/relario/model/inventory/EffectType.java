package it.unibo.oop.relario.model.inventoryitems;

/**
 * Enum representing the effects that an inventory item can have.
 * Each type corresponds to a specific advantage that the item provides.
 */

public enum EffectType {

    /** An effect that increases the player's damage. */
    DAMAGE, 

    /** An effect that provides protection. */
    PROTECTION,

    /** An effect that restores player's life. */
    HEALING,

    /** No effect, for collectable items. */
    NONE;

}
