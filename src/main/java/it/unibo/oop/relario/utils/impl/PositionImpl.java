package it.unibo.oop.relario.utils.impl;

import java.util.Objects;

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
    public PositionImpl(final int x, final int y) {
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

    @Override
    public boolean equals(Object pos) {
        if (this == pos) return true;
        if (pos == null || getClass() != pos.getClass()) return false;
        Position p = (Position) pos;
        return Objects.equals(this.getX(), p.getX()) && Objects.equals(this.getY(), p.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
