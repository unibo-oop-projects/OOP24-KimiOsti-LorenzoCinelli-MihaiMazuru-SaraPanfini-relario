package it.unibo.oop.relario.model.inventory;

/**
 * Enum representing the effects that an inventory item can have.
 * Each type corresponds to a specific advantage that the item provides.
 */

public enum EffectType {

    /** An effect that increases the player's damage. */
    DAMAGE("Danno"), 

    /** An effect that provides protection. */
    PROTECTION("Protezione"),

    /** An effect that restores player's life. */
    HEALING("Cura"),

    /** No effect, for collectable items. */
    NONE("Nessuno"),

    /** An effect that allows the player to pass the quest. */
    QUEST("Quest");

    private final String effect;

    EffectType(final String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return this.effect;
    }

}
