package it.unibo.oop.relario.utils.impl;

import java.util.Objects;

import it.unibo.oop.relario.utils.api.Position;

/**
 * Implementation of the Position interface, to handle positioning on the map.
 */
public final class PositionImpl implements Position {

    private static final long serialVersionUID = 1L;

    private Pair<Integer, Integer> position;

    /**
     * Creates a position, given a pair of coordinates.
     * @param x the initial x coordinate.
     * @param y the initial y coordinate.
     */
    public PositionImpl(final int x, final int y) {
        this.position = new Pair<>(x, y);
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
        this.position = new Pair<>(newX, this.position.getY());
    }

    @Override
    public void setY(final int newY) {
        this.position = new Pair<>(this.position.getX(), newY);
    }

    @Override
    public boolean equals(final Object pos) {
        return pos instanceof Position
            && ((Position) pos).getX() == this.position.getX()
            && ((Position) pos).getY() == this.position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.position.getX(), this.position.getY());
    }
}
