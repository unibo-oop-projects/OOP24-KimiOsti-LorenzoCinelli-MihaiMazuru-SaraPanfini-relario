package it.unibo.oop.relario.controller.api;

import it.unibo.oop.relario.utils.impl.Event;

/**
 * Observer for managing key events.
 */
public interface Observer {
   /**
    * Handles event.
    * @param event is the event to handle.
    */
    void notify(Event event);

}
