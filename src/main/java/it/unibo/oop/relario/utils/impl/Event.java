package it.unibo.oop.relario.utils.impl;

/**
 * Enumeration representing the different types of events 
 * generated when a key is pressed.
 */
public enum Event {
    /** Represents the action for moving up. */
    MOVE_UP,

    /** Represents the action for moving down. */
    MOVE_DOWN, 

    /** Represents the action for moving right. */
    MOVE_RIGHT,

    /** Represents the action for moving left. */
    MOVE_LEFT,

    /** Represents the action for opening the inventory. */
    INVENTORY,

    /** Represents the action for closing a window. */
    ESCAPE, 

    /** Represents the action for interacting with an entity. */
    INTERACT;

}
