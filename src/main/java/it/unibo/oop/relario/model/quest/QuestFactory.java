package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.map.Room;

/**
 * Interface for a quest factory class.
 */
public interface QuestFactory {

    /**
     * Creates a quest instance from a given type.
     * @param room the room hosting the quest.
     * @param questType the type of the quest.
     * @param keyEntity the type of the entity related to the quest.
     * @return an instance of a quest with all the given specifications.
     */
    Quest createQuestByType(Room room, QuestType questType, Optional<GameEntityType> keyEntity);

}
