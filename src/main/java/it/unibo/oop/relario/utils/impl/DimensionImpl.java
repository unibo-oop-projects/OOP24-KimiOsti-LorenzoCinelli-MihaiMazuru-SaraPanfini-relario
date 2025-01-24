package it.unibo.oop.relario.utils.impl;

import it.unibo.oop.relario.utils.api.Dimension;

/**
 * Implementation of Dimension interface.
 */

public final class DimensionImpl implements Dimension {

    private Pair<Integer, Integer> dimension;

    /**
     * Creates a position, given a pair of coordinates.
     * @param x the initial x coordinate.
     * @param y the initial y coordinate.
     */
    public DimensionImpl(final int x, final int y) {
        this.dimension = new Pair<Integer,Integer>(x, y);
    }

    @Override
    public int getWidth() {
        return this.dimension.getX();
    }

    @Override
    public int getHeight() {
        return this.dimension.getY();
    }

}
