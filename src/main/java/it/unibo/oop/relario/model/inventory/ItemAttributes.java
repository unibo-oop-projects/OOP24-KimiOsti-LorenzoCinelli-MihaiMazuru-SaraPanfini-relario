package it.unibo.oop.relario.model.inventory;

/**
 * Utility class that contains constant attributes of the inventory items.
 */

public final class ItemAttributes {

    /** Constant intensity of a sword. */
    public static final int SWORD_INTENSITY = 10;
    /** Constant durability of a sword. */
    public static final int SWORD_DURABILITY = 10;

    /** Constant intensity of a bow. */
    public static final int BOW_INTENSITY = 8;
    /** Constant durability of a bow. */
    public static final int BOW_DURABILITY = 5;

    /** Constant intensity of a dagger. */
    public static final int DAGGER_INTENSITY = 5;
    /** Constant durability of a dagger. */
    public static final int DAGGER_DURABILITY = 3;

    /** Constant intensity of a hammer. */
    public static final int HAMMER_INTENSITY = 15;
    /** Constant durability of a hammer. */
    public static final int HAMMER_DURABILITY = 8;

    /** Constant intensity of a shield. */
    public static final int SHIELD_INTENSITY = 10;
    /** Constant durability of a shield. */
    public static final int SHIELD_DURABILITY = 5;

    /** Constant intensity of a basic armor. */
    public static final int BASICARMOR_INTENSITY = 5;
    /** Constant durability of a basic armor. */
    public static final int BASICARMOR_DURABILITY = 3;

    /** Constant intensity of a potion. */
    public static final int POTION_INTENSITY = 10;

    /** Constant intensity of an apple. */
    public static final int APPLE_INTENSITY = 3;

    /** Constant intensity of an amulet. */
    public static final int AMULET_INTENSITY = 15;

    /** Constant intensity of a coin. */
    public static final int COIN_INTENSITY = 1;

    /** Constant intensity of a gemstone. */
    public static final int GEMSTONE_INTENSITY = 1;

    /**
     * Private constructor that prevents the instantiation of the class.
     */
    private ItemAttributes() {
        throw new UnsupportedOperationException();
    }

}
