package it.unibo.oop.relario.controller.api;

import it.unibo.oop.relario.utils.impl.Event;

/**
 * Observer for managing key events.
 */
public interface Observer {
   /**
    * Handles event.
    */
    void notify(Event event);

}
