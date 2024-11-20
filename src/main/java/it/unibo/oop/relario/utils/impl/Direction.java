package it.unibo.oop.relario.utils.impl;

import it.unibo.oop.relario.utils.api.Position;

/**
 * Enumeration to handle direction in game movements.
 */
public enum Direction {
    /**
     * Towards north.
     */
    UP(0, -1),

    /**
     * Towards south.
     */
    DOWN(0, 1),

    /**
     * Towards west.
     */
    LEFT(-1, 0),

    /**
     * Towards east.
     */
    RIGHT(1, 0);

    private final int deltaX;
    private final int deltaY;

    Direction(final int x, final int y) {
        this.deltaX = x;
        this.deltaY = y; 
    }

    /**
     * Moves the position in the current direction.
     * @param position the initial position
     * @return the new position after the movement
     */
    public Position move(final Position position) {
        return new PositionImpl(position.getX() + deltaX, position.getY() + deltaY);
    }
}
