package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.map.Room;

/**
 * 
 */

public interface Quest {

    /**
     * 
     * @return TODO
     */
    String getName();

    /**
     * 
     * @return TODO
     */
    String getDescription();

    /**
     * 
     * @return TODO
     */
    boolean isCompleted();

    /**
     * 
     * @return TODO
     */
    GameEntityType getKeyEntityType();

    Room getRoom();

}
