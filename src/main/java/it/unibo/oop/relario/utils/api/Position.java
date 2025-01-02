package it.unibo.oop.relario.utils.api;

/**
 * Interface to handle positions throughout the game.
 */
public interface Position {
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

    /**
     * Indicates whether some other object is "equal to" this one. 
     * The objects are equals if their fields are equals.
     * @param pos is the object to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    boolean equals(Position pos);
}
