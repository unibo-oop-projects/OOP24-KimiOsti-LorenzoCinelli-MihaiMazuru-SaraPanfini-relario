package it.unibo.oop.relario.model.entities.furniture.impl;

/**
 * Utility class that containts all the proprities of a furniture. 
 */
public final class FurniturePropriety {
    
    /** A furniture that the player can walk above. */
    public final static String WALKABLE = "Walkable";

    /** A furniture that cannot be crossed.  */
    public final static String OBSTRUCTING = "Obstructing";

    /** A furniture that the player can interact. */
    public final static String INTERACTIVE = "Interactive";

    private FurniturePropriety() {
        throw new UnsupportedOperationException();
    }
    
}
