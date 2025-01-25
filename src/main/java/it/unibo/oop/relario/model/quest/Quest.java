package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.map.Room;

/**
 * Interface for a game's quest.
 */
public interface Quest {

    /**
     * Getter for the quest description.
     * @return the description of the quest.
     */
    String getDescription();

    /**
     * Checks if the quest is completed.
     * @param room the room hosting the quest.
     * @return a boolean representing if the quest is completed.
     */
    boolean isCompleted(Room room);

    /**
     * Getter for the type of the entity concerned by the quest.
     * @return the type of the entity concerned by the quest.
     */
    Optional<? extends GameEntityType> getKeyEntityType();

}
