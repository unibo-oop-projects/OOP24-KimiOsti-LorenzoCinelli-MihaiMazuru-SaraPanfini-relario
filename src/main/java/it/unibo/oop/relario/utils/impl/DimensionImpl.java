package it.unibo.oop.relario.utils.impl;

import it.unibo.oop.relario.utils.api.Dimension;

/**
 * Implementation of Dimension interface.
 */

public class DimensionImpl implements Dimension {

    private final int height;
    private final int width;

    /**
     * Constructor for room's dimension, given height and width.
     * @param height of the room
     * @param width of the room
     */
    public DimensionImpl(final int height, final int width) {
        this.height = height;
        this.width = width;
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
