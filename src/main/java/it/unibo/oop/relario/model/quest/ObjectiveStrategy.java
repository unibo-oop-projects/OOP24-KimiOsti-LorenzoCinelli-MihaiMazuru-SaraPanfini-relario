package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.map.Room;

/**
 * 
 */

public interface ObjectiveStrategy {

    /**
     * 
     * @param room
     * @return TODO
     */
    boolean check(Room room);

    Optional<GameEntityType> getKeyEntityType();

}
