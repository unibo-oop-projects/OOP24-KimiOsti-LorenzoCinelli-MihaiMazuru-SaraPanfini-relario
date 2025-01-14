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
    HEALING("Guarigione"),

    /** No effect, for collectable items. */
    NONE("Nessuno");

    private final String effect;

    EffectType(final String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return this.effect;
    }

}
