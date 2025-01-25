package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;

/**
 * Interface for a quest factory class.
 */
public interface QuestFactory {

    /**
     * Creates a quest instance from a given type.
     * @param description the description of the quest.
     * @param questType the type of the quest.
     * @param keyEntity the type of the entity related to the quest.
     * @return an instance of a quest with all the given specifications.
     */
    Quest createQuestByType(String description, QuestType questType, Optional<GameEntityType> keyEntity);

}
