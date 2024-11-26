package it.unibo.oop.relario.model.inventory;

import java.util.List;

/**
 * Interface for handling the player's inventory.
 */
public interface Inventory {
    /**
     * Removes an item from the player's inventory.
     * @param item the item to be removed.
     * @return true if it's removed correcly, false otherwise.
     */
    boolean removeItem(InventoryItem item);

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
