package it.unibo.oop.relario.utils.api;

import java.io.Serializable;

/**
 * Interface to handle positions throughout the game.
 */
public interface Position extends Serializable {
    /**
     * @return the x coordinate of the current position.
     */
    int getX();

    /**
     * @return the y coordinate of the current position.
     */
    int getY();

    /**
     * Sets the new x coordinate of the position.
     * @param newX the destination value for the x coordinate.
     */
    void setX(int newX);

    /**
     * Sets the new y coordinate of the position.
     * @param newY the destination value for the y coordinate.
     */
    void setY(int newY);

    @Override
    boolean equals(Object pos);

    @Override
    int hashCode();
}
