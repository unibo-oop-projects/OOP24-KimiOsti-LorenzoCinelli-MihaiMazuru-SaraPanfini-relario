package it.unibo.oop.relario.model.inventoryitems;

import java.util.List;

/**
 * Interface for handling the player's inventory.
 */
public interface Inventory {
    /**
     * Uses the selected item.
     * @param item the item to use.
     * @return true if the item is consumed correctly, false otherwise.
     */
    boolean useItem(InventoryItem item);

    /**
     * Drops an item, removing it from the inventory.
     * @param item the item to drop.
     * @return true if it's dropped correctly, false otherwise.
     */
    boolean dropItem(InventoryItem item);

    /**
     * Adds an item to the player's inventory.
     * @param item the item to be added.
     * @return true if it's added correctly, false otherwise.
     */
    boolean addItem(InventoryItem item);

    /**
     * Gets the full inventory.
     * @return a list containing all items currently stored in the inventory.
     */
    List<InventoryItem> getItemsList();
}
