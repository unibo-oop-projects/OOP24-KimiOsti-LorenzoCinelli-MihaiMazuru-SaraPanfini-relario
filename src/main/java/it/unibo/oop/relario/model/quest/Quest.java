package it.unibo.oop.relario.model.quest;

import java.util.Optional;

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
    Optional<? extends GameEntityType> getKeyEntityType();

    Room getRoom();

}
