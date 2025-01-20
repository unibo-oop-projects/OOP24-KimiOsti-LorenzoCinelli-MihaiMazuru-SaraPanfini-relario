package it.unibo.oop.relario.controller.api;

import java.util.List;

/**
 * Interface for inventory controller, used when the user is interacting with the inventory.
 */
public interface InventoryController extends Observer {

    /**
     * Initializes the inventory controller. 
     */
    void init();

    /**
     * Returns the names of all items into the player inventory.
     * @return the list of items' names.
     */
    List<String> getItemsNames();

    /**
     * Retrives the description and effects of an item into the inventory.
     * If the item is equippable returns even the durability.
     * @return the full description of the item, or an empty string in case the inventory is empty.
     */
    String getItemFullDescription();

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
     * Returns the life of the player.
     * @return a String containing the life of the player.
     */
    String getLife();

    /**
     * Sets the index of the new selected item in the inventory.
     * @param index is the value of the new selected item.
     */
    void setSelectedItemIndex(int index);
}
