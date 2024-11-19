package it.unibo.oop.relario.model.entities.living;

import it.unibo.oop.relario.model.inventoryitems.Inventory;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;

/**
 * Interface to interact with main character.
 */
public interface MainCharacter {
    /**
     * Reveals the current player position.
     * @return the current position of the player.
     */
    Position getPosition();

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
     * Updates the player's state, to be called at each unit of time.
     */
    void update();

    /**
     * Handles the player's inventory.
     * @return the player's inventory.
     */
    Inventory handleInventory();

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
}
