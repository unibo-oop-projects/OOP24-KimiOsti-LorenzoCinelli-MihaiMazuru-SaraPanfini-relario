package it.unibo.oop.relario.model.entities.living;
import java.util.List;
import java.util.Optional;

import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.inventory.EquippableItem;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;

/**
 * Interface to interact with main character.
 */
public interface MainCharacter extends LivingBeing {
    /**
     * Sets the player's position. Used when initialising the map.
     * @param position the player's initial position on map.
     */
    void setPosition(Position position);

    /**
     * Asks whether the player is moving.
     * @return true if the player is moving, false otherwise.
     */
    boolean isMoving();

    /**
     * Sets the player's movement.
     * @param direction the direction where the player is moving.
     */
    void setMovement(Direction direction);

    /**
     * Stops the player movement.
     */
    void stop();

    /**
     * Inflicts some damage to the player.
     * @param damage the damage inflicted to the player.
     */
    void attacked(int damage);

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
     * Returns the content of the player's inventory.
     * @return a list containing the player's ivnentory items.
     */
    List<InventoryItem> getItems();

    /**
     * Returns the current weapon, if equipped.
     * @return the equipped weapon, or an empty object.
     */
    Optional<EquippableItem> getEquippedWeapon();

    /**
     * Returns the current armor, if equipped.
     * @return the equipped armor, or an empty object.
     */
    Optional<EquippableItem> getEquippedArmor();

    /**
     * Uses an item from the inventory.
     * @param item the item to be used.
     * @return true if it's used correctly, false otherwise.
     */
    boolean useItem(InventoryItem item);

    /**
     * Discards an item from the player's inventory.
     * @param item the item to be discarded.
     * @return true if it's removed correctly, false otherwise.
     */
    boolean discardItem(InventoryItem item);

    /**
     * Adds an item to the player's inventory.
     * @param item the item to be added.
     * @return true if it's added correctly, false otherwise.
     */
    boolean addToInventory(InventoryItem item);
}
