package it.unibo.oop.relario.model.entities.furniture.api;

import it.unibo.oop.relario.model.inventory.InventoryItem;

/**
 * 
 */
public interface InteractiveFurniture extends Furniture {
    /**
     * Adds a new loot to the furniture item.
     * @param loot is the inventory item to add to the furnite.
     */
    void addLoot(final InventoryItem loot);

    /**
     * Checks if the furniture item has any loot inside.
     * @return true if the furniture item contains any loot, false otherwise.
     */
    boolean hasLoot();

    /**
     * Retrieves the loot from inside the furniture item. The furniture is empty after.
     * @return an inventory item if there is any inside the furniture item, an optional empty otherwise.
     */
    InventoryItem dropLoot();
}
