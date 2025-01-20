package it.unibo.oop.relario.utils.impl;

import java.util.Objects;

/**
 * Implementation of Pair.
 */

 public final class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Constructor for room's dimension, given height and width.
     * @param height of the room
     * @param width of the room
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return this.x;
    }

    public Y getY() {
        return this.y;
    }

    @SuppressWarnings("rawtypes")
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        return Objects.equals(this.getX(), other.getX()) && Objects.equals(this.getY(), other.getY());
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

}
