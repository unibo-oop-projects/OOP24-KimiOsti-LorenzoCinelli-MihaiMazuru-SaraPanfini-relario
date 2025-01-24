package it.unibo.oop.relario.utils.impl;

import it.unibo.oop.relario.utils.api.Position;

/**
 * Implementation of the Position interface, to handle positioning on the map.
 */
public final class PositionImpl implements Position {

    private Pair<Integer, Integer> position;

    /**
     * Creates a position, given a pair of coordinates.
     * @param x the initial x coordinate.
     * @param y the initial y coordinate.
     */
    public PositionImpl(final int x, final int y) {
        this.position = new Pair<Integer,Integer>(x, y);
    }

    @Override
    public int getX() {
        return this.position.getX();
    }

    @Override
    public int getY() {
        return this.position.getY();
    }

    @Override
    public void setX(final int newX) {
        this.position = new Pair<Integer,Integer>(newX, this.position.getY());
    }

    @Override
    public void setY(final int newY) {
        this.position = new Pair<Integer,Integer>(this.position.getX(), newY);
    }

    @Override
    public boolean equals(final Object pos) {
        return this.position.equals(pos);
    }

    @Override
    public int hashCode() {
        return this.position.hashCode();
    }
}
