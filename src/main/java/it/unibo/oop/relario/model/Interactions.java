package it.unibo.oop.relario.model;

import it.unibo.oop.relario.utils.api.Position;

/**
 * Static class for handling interactions and collision between player and map's elements. 
 */
public final class Interactions {

    private Interactions() {
        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the next position is available.
     * @param pos the initial position of the entity.
     * @param dir the direction the entity is moving.
     * @return true if the position is available, false otherwise.
     */
    public static boolean canMove(final Position pos, final Direction dir) {
        return false;
    }

}
