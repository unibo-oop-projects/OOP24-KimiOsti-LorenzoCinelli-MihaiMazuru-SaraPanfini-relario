package it.unibo.oop.relario.model.inventory;

/**
 * Enum representing different types of inventory items.
 * Each type is associated with a specific effect.
 */

public enum InventoryItemType {
    /** A sword with damage effect. */
    SWORD(EffectType.DAMAGE),

    /** A bow with damage effect. */
    BOW(EffectType.DAMAGE),

    /** A dagger with damage effect. */
    DAGGER(EffectType.DAMAGE),

    /** A hammer with damage effect. */
    HAMMER(EffectType.DAMAGE),

    /** A shield with protection effect. */
    SHIELD(EffectType.PROTECTION),

    /** A basic armor with protection effect. */
    BASICARMOR(EffectType.PROTECTION),

    /** A potion with healing effect. */
    POTION(EffectType.HEALING),

    /** An apple with healing effect. */
    APPLE(EffectType.HEALING),

    /** An amulet with healing effect. */
    AMULET(EffectType.HEALING),

    /** A coin with no effect. */
    COIN(EffectType.NONE),

    /** A gemstone with no effect. */
    GEMSTONE(EffectType.NONE);

    private final EffectType effect;

    /**
     * Constructor for initializing the inventory item type with the corresponding effect.
     * @param effect of the item
     */
    InventoryItemType(final EffectType effect) {
        this.effect = effect;
    }

    /**
     * Retrieves the effect associated with the inventory item type.
     * @return the effect of the item
     */
    public EffectType getEffect() {
        return this.effect;
    }
}
