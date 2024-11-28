package it.unibo.oop.relario.controller.api;

import java.util.List;

/**
 * Interface for inventory controller, used when the user is interacting with the inventory.
 */
public interface InventoryController {

    /**
     * Retrives the names of all items into the player inventory.
     * @return the list of items' names.
     */
    List<String> getItemsNames();

    /**
     * Retrives the description and effects of an item into the inventory.
     * @param index identifies what item the description is required.
     * @return the full description of the item.
     */
    String getItemFullDescription(int index);

    /**
     * Uses an item which is into the inventory.
     * @param index identifies the item to use. 
     */
    void useItem(int index);

    /**
     * Discards an item from the inventory.
     * @param index identifies the item to discard.
     */
    void discardItem(int index);

    /**
     * 
     */
    void regress();
}