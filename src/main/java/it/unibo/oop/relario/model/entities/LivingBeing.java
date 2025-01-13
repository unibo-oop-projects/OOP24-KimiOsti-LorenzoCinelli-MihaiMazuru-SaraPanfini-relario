package it.unibo.oop.relario.model.entities;

import it.unibo.oop.relario.utils.impl.Direction;

/**
 * Interface for living beings.
 */

public interface LivingBeing extends Entity {

    /**
     * Retrieves the name of the living being.
     * @return the name of the living being
     */
    String getName();

    /**
     * Updates the state of a living being.
     * This method is called periodically to reflect changes in the living being's behaviour.
     */
    void update();

    /**
     * Checks the direction of a living being.
     * @return the direction towards which the living being is moving.
     */
    Direction getDirection();

}
