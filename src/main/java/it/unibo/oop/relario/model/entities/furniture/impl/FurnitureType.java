package it.unibo.oop.relario.model.entities.furniture.impl;

/**
 * Enumeration representing the different types of furniture items.
 * Each furniture item has a propriety.
 */
public enum FurnitureType {

    /** Represents a carpet, a walkable furniture item that may hide an enemy inside. */
    CARPET("Carpet", FurniturePropriety.WALKABLE),

    /** Represents a trapdoor, a walkable furniture item that may hide an enemy inside. */
    TRAPDOOR("Trapdoor", FurniturePropriety.WALKABLE),

    /** Represents a wardrode, a purely decorative furniture item. */
    WARDROBE("Wardrobe", FurniturePropriety.OBSTRUCTING),

    /** Represents a wardrode, a purely decorative furniture item. */
    STATUE("Statue", FurniturePropriety.OBSTRUCTING),

    /** Represents a vase, an interactive furniture item that may hide valuable loot inside. */
    VASE("Vase", FurniturePropriety.INTERACTIVE),

    /** Represents an armorstand, an interactive furniture item that may hide valuable loot inside. */
    ARMORSTAND("ArmorStand", FurniturePropriety.INTERACTIVE),

    /** Represents a chest, an interactive furniture item that may hide valuable loot inside. */
    CHEST("Chest", FurniturePropriety.INTERACTIVE);

    private final String furnitureType;
    private final FurniturePropriety furniturePropriety;

    FurnitureType(final String type, final FurniturePropriety furniturePropriety) {
        this.furnitureType = type;
        this.furniturePropriety = furniturePropriety;
    }

    /**
     * Retrieves the name of the item.
     * @return the item's name.
     */
    public String getName() {
        return this.furnitureType;
    }

    /**
     * Retrieves the furniutre item's propriety.
     * @return the furniture item's propriety.
     */
    public FurniturePropriety getFurniturePropriety() {
        return this.furniturePropriety;
    }
}
