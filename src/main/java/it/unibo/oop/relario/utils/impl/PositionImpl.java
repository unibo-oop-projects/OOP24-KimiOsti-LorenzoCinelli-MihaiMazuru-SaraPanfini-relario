package it.unibo.oop.relario.utils.impl;

import it.unibo.oop.relario.utils.api.Position;

/**
 * Implementation of the Position interface, to handle positioning on the map.
 */
public final class PositionImpl implements Position {

    private int x;
    private int y;

    /**
     * Creates a position, given a pair of coordinates.
     * @param x the initial x coordinate.
     * @param y the initial y coordinate.
     */
    PositionImpl(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(final int newX) {
        this.x = newX;
    }

    @Override
    public void setY(final int newY) {
        this.y = newY;
    }
}
