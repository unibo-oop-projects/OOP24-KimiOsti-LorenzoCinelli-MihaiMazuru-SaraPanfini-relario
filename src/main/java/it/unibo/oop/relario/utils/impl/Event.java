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

    /** Represents the realising of the key pressed before. */
    RELEASED,

    /** Represents the action for opening or closing the inventory. */
    INVENTORY,

    /** Represents the action for closing a window. */
    ESCAPE, 

    /** Represents the action for interacting with an entity. */
    INTERACT,

    /** Represents the action for using the focused item in the inventory. */
    USE_ITEM,

    /** Represents the action for discarding the focused item in the inventory. */
    DISCARD_ITEM,

    /** Represents focusing on the next item in the inventory. */
    NEXT_ITEM,

    /** Represents focusing on the previous item in the inventory. */
    PREVIOUS_ITEM;

}
