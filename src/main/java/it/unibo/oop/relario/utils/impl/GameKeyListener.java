package it.unibo.oop.relario.utils.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import it.unibo.oop.relario.controller.api.Observer;

/**
 * Implemention for managing input keys.
 */
public final class GameKeyListener implements KeyListener {

    private final Observer observer;
    private final Map<Integer, Event> keys = new HashMap<>();

    /**
     * Initializes the game game listener.
     * @param observer is the controller that 
     */
    public GameKeyListener(final Observer observer) {
        this.observer = observer;
        this.initializeKeys();
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (this.isValidKey(e.getKeyCode())) {
            this.observer.notify(convertKey(e.getKeyCode()));
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    } 

    /**
     * Checks if the key pressed is a valid key.
     * @param keyCode is the key pressed.
     * @return true if the key pressed is a valid key, false otherwise.
     */
    private boolean isValidKey(final int keyCode) {
        return this.keys.keySet().contains(keyCode);
    }

    /**
     * Maps the valid keys with the corresponding event.
     */
    private void initializeKeys() {
        this.keys.put(KeyEvent.VK_W, Event.MOVE_UP);
        this.keys.put(KeyEvent.VK_S, Event.MOVE_DOWN);
        this.keys.put(KeyEvent.VK_A, Event.MOVE_LEFT);
        this.keys.put(KeyEvent.VK_D, Event.MOVE_RIGHT);
        this.keys.put(KeyEvent.VK_I, Event.INVENTORY);
        this.keys.put(KeyEvent.VK_E, Event.INTERACT);
        this.keys.put(KeyEvent.VK_ESCAPE, Event.ESCAPE);
        this.keys.put(KeyEvent.VK_ENTER, Event.USE_ITEM);
        this.keys.put(KeyEvent.VK_BACK_SPACE, Event.DISCARD_ITEM);
        this.keys.put(KeyEvent.VK_DOWN, Event.NEXT_ITEM);
        this.keys.put(KeyEvent.VK_UP, Event.PREVIOUS_ITEM);
    }

    /**
     * Converts the the key pressed into an event.
     * @param keyCode is the key pressed.
     * @return the event corresponding to the key pressed.
     */
    private Event convertKey(final int keyCode) {
        return this.keys.get(keyCode);
    }

}
