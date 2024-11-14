package it.unibo.oop.relario.utils;

/**
 * Interface to handle positions throughout the game.
 * @param T The type of the single coordinates.
 */
public interface Position<T> {
    public T getX();
    public T getY();
}
