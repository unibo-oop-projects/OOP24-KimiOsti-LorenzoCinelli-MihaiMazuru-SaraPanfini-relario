package it.unibo.oop.relario.utils.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.oop.relario.controller.api.Observer;

/**
 * Implemention for managing input keys.
 */
public final class GameKeyListener implements KeyListener {

    private final Observer observer;

    /**
     * Initializes the game game listener.
     * @param observer is the controller that 
     */
    public GameKeyListener(final Observer observer) {
        this.observer = observer;
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (this.isValidKey(e)) {
            this.observer.notify(convertKey(e));
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    } 

    /**
     * Checks if the key pressed is a valid key for movement.
     * @param e is the key pressed.
     * @return true if the the key pressed is a valid key for movement, false otherwise.
     */
    private boolean isMovementKey(final KeyEvent e) {
        final int keyCode = e.getKeyCode();
        return keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S ||
        keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A;
    }

    /**
     * Checks if the key pressed is a valid key.
     * @param e is the key pressed.
     * @return true if the key pressed is a valid key, false otherwise.
     */
    private boolean isValidKey(final KeyEvent e) {
        final int keyCode = e.getKeyCode();
        return  isMovementKey(e) || keyCode == KeyEvent.VK_ESCAPE || 
        keyCode == KeyEvent.VK_I ||keyCode == KeyEvent.VK_E;
    }

    /**
     * Converts the the key pressed into a direction.
     * @param e is the key pressed.
     * @return the direction corresponding to the key pressed.
     */
    private static Event convertKey(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: 
                return Event.MOVE_UP;
            case KeyEvent.VK_DOWN: 
                return Event.MOVE_DOWN;
            case KeyEvent.VK_RIGHT: 
                return Event.MOVE_RIGHT;
            case KeyEvent.VK_LEFT: 
                return Event.MOVE_LEFT;
            case KeyEvent.VK_ESCAPE:
                return Event.ESCAPE;
            case KeyEvent.VK_I:
                return Event.INVENTORY;
            case KeyEvent.VK_E:
                return Event.INTERACT;
            default: throw new IllegalArgumentException("Illegal pressed key");
        }
    }

}
