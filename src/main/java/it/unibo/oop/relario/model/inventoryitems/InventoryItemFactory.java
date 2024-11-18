package it.unibo.oop.relario.model.inventoryitems;

/**
 * An interface modelling a factory of inventory items.
 */

public interface InventoryItemFactory {

    /**
     * Creates an inventory item of the specified type.
     * @param type of the item that has to be created
     * @return an inventory item of the specified type
     */
    InventoryItem createItem(InventoryItemType type);

    /**
     * Creates an equippable inventory item of the specified type.
     * @param type of the item that has to be created
     * @return an equippable item of the specified type
     */
    EquippableItem createEquippableItem(InventoryItemType type);

    /**
     * Creates a random inventory item.
     * The item is chosen randomly from the inventory item types.
     * @return a random inventory item
     */
    InventoryItem createRandomItem();

    /**
     * Creates a random inventory item that has the specified effect.
     * The item is chosen randomly from the inventory item types that has the same effect as the specified one.
     * @param effect that the random inventory item should have
     * @return a random inventory item with the specified effect
     */
    InventoryItem createRandomItemByEffect(EffectType effect);

}
