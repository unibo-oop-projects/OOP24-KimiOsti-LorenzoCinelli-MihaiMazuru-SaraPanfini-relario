package it.unibo.oop.relario.model.inventory;

/** 
 * This class represents an equippable item, extending the InventoryItemImpl class.
 * It adds fields that are specific to items that can be equipped.
 */

public class EquippableItem extends InventoryItemImpl {

    private int durability;

    /**
     * Constructs an equippable item with the specified name, description, type, intensitity and durability.
     * @param description of the item
     * @param type of the item
     * @param intensity of the item's effect
     * @param durability of the item, that states how long it can be used
     */
    public EquippableItem(final String description, final InventoryItemType type, 
    final int intensity, final int durability) {
        super(description, type, intensity);
        this.durability = durability;
    }

    /**
     * Retrieves the durability of the equippable item.
     * @return the durability of the item
     */
    public int getDurability() {
        return this.durability;
    }

    /**
     * Deacreses equippable item's durability when it's used.
     * @return true when the item is still usable, false otherwise
     */
    public boolean decreaseDurability() {
        this.durability--;
        return this.durability > 0;
    }

}
