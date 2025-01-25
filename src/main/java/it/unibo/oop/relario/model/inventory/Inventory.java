package it.unibo.oop.relario.model.inventory;

import java.util.List;
import java.util.Optional;

/**
 * Interface for handling the player's inventory.
 */
public interface Inventory {
    /**
     * Inspects the health state of the player.
     * @return the current life of the player.
     */
    int getLife();

    /**
     * Represents the attacking action of the player.
     * @return the damage inflicted by the player's attack.
     */
    int attack();

    /**
     * Inflicts some damage to the player.
     * @param damage the amount of damage to be inflicted.
     */
    void attacked(int damage);

    /**
     * Gets the full inventory.
     * @return a list containing all items currently stored in the inventory.
     */
    List<InventoryItem> getItemsList();

    /**
     * Getter for the equipped armor.
     * @return an optional containing the armor, or empty if it's not equipped.
     */
    Optional<EquippableItem> getEquippedArmor();

    /**
     * Getter for the equipped weapon.
     * @return an optional containing the weapon, or empty if it's not equipped.
     */
    Optional<EquippableItem> getEquippedWeapon();

    /**
     * Uses an item from the player's inventory.
     * @param item the item to be used.
     * @return true if it's used correcly, false otherwise.
     */
    boolean useItem(InventoryItem item);

    /**
     * Discards an item from the player's ivnentory.
     * @param item the item to be discarded.
     * @return true if it's discarded correctly, false otherwise.
     */
    boolean discardItem(InventoryItem item);

    /**
     * Adds an item to the player's inventory.
     * @param item the item to be added.
     * @return true if it's added correctly, false otherwise.
     */
    boolean addItem(InventoryItem item);
}
