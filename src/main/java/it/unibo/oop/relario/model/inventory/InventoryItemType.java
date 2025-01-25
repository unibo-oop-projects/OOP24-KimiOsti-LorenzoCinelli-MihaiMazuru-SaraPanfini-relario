package it.unibo.oop.relario.model.inventory;

import it.unibo.oop.relario.model.GameEntityType;

/**
 * Enum representing different types of inventory items.
 * Each type is associated with a specific effect.
 */

public enum InventoryItemType implements GameEntityType {

    /** A sword with damage effect. */
    SWORD("Spada", EffectType.DAMAGE),

    /** A bow with damage effect. */
    BOW("Arco", EffectType.DAMAGE),

    /** A dagger with damage effect. */
    DAGGER("Pugnale", EffectType.DAMAGE),

    /** A hammer with damage effect. */
    HAMMER("Martello", EffectType.DAMAGE),

    /** A shield with protection effect. */
    SHIELD("Scudo", EffectType.PROTECTION),

    /** A basic armor with protection effect. */
    BASICARMOR("Armatura semplice", EffectType.PROTECTION),

    /** A potion with healing effect. */
    POTION("Pozione", EffectType.HEALING),

    /** An apple with healing effect. */
    APPLE("Mela", EffectType.HEALING),

    /** An amulet with healing effect. */
    AMULET("Amuleto", EffectType.HEALING),

    /** A coin with no effect. */
    COIN("Moneta", EffectType.NONE),

    /** A gemstone with no effect. */
    GEMSTONE("Pietra preziosa", EffectType.NONE),

    /** A key that allows to pass a quest. */
    KEY("Chiave", EffectType.QUEST);

    private final String name;
    private final EffectType effect;

    /**
     * Constructor for initializing the inventory item type with the corresponding effect.
     * @param name of the item.
     * @param effect of the item
     */
    InventoryItemType(final String name, final EffectType effect) {
        this.name = name;
        this.effect = effect;
    }

    /**
     * Retrieves the name of the inventory item.
     * @return the name of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the effect associated with the inventory item.
     * @return the effect of the item
     */
    public EffectType getEffect() {
        return this.effect;
    }
}
