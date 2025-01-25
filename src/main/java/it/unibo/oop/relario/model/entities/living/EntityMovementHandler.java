package it.unibo.oop.relario.model.entities.living;

import java.util.Optional;

import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;

/**
 * Interface for an entity's movement handler.
 */
public interface EntityMovementHandler {
    /**
     * Getter for the entity's position.
     * @return the entity's current position.
     */
    Optional<Position> getPosition();

    /**
     * Getter for the entity's direction.
     * @return the entity's current direction.
     */
    Direction getDirection();

    /**
     * Sets a new position for the entity.
     * @param newPos the new position to which the entity is placed.
     */
    void setPosition(Position newPos);

    /**
     * Sets a new direction for the entity, starting a movement.
     * @param newDir the new direction towards which the entity is moving.
     */
    void setMovement(Direction newDir);

    /**
     * Moves the entity of one step, if it's not stopped.
     */
    void move();

    /**
     * Stops the entity's movement.
     */
    void stop();
}
