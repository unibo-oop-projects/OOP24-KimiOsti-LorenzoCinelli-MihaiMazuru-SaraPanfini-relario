package it.unibo.oop.relario.model.entities.living;
import java.util.List;

import it.unibo.oop.relario.model.entities.LivingBeing;
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
     * Reveals the current player direction.
     * @return the current direction the player is facing.
     */
    Direction getDirection();

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
     * @return true if the player survives the attack, false otherwise.
     */
    boolean attacked(int damage);

    /**
     * Handles the player's attack power.
     * @return the damage inflicted each attack by the player.
     */
    int getAttack();

    /**
     * Returns the content of the player's inventory.
     * @return a list containing the player's ivnentory items.
     */
    List<InventoryItem> getItems();

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
