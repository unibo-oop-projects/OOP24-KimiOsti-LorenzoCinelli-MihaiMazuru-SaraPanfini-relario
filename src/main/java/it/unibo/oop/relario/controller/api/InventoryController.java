package it.unibo.oop.relario.controller.api;

import java.util.List;

/**
 * Interface for inventory controller, used when the user is interacting with the inventory.
 */
public interface InventoryController extends Observer {

    /**
     * Returns the names of all items into the player inventory.
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
     * Returns the name, description, effect and durability of the equipped armor if equipped. 
     * If no armor is equipped it returns an empty string.
     * @return the full description of equipped armor if present, otherwise an empty string.
     */
    String getEquippedArmor();

    /**
     * Returns the name, description, effect and durability of the equipped weapon if equipped. 
     * If no weapon is equipped it returns an empty string.
     * @return the full description of equipped weapon if present, otherwise an empty string.
     */
    String getEquippedWeapon();

    /**
     * Returns the index of the selected item in the inventory.
     * @return the index of the selected item in the inventory.
     */
    int getSelectedItemIndex();

    /**
     * Sets the index of the new selected item in the inventory.
     * @param index is the value of the new selected item.
     */
    void setSelectedItemIndex(int index);
}
