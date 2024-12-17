package it.unibo.oop.relario.model.entities.furniture.impl;

public enum FurnitureType {
    
    /**  */
    CARPET("Carpet"),

    /** */
    TRAPDOOR("Trapdoor"),

    /** */
    WARDROBE("Wardrobe"),

    /** */
    STATUE("Statue"),

    /** */
    VASE("Vase"),

    /** */
    ARMORSTAND("ArmorStand"),

    /** */
    CHEST("Chest");

    private final String type;

    /**
     * 
     * @param type
     */
    FurnitureType(final String type) {
        this.type = type;
    }

    /**
     * 
     */
    public String getName() {
        return this.type;
    }
}
