package it.unibo.oop.relario.utils.impl;

import it.unibo.oop.relario.utils.api.Dimension;

/**
 * Implementation of Dimension interface.
 */

public final class DimensionImpl implements Dimension {

    private final int width;
    private final int height;

    /**
     * Constructor for room's dimension, given height and width.
     * @param height of the room
     * @param width of the room
     */
    public DimensionImpl(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

}
