package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.map.Room;

/**
 * Defines the interface for a quest's objective.
 */

public interface ObjectiveStrategy {

    /**
     * Checks whether the quest is completed.
     * @param room the room in which the quest is located.
     * @return a boolean representing whether the quest is completed.
     */
    boolean check(Room room);

    /**
     * Getter for the type of the entity to which the quest is related.
     * @return the type of the entity related to the quest.
     */
    Optional<? extends GameEntityType> getKeyEntityType();

}
