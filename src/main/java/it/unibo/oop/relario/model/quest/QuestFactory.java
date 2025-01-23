package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.map.Room;

/**
 * 
 */
public interface QuestFactory {

    Quest createQuestByType(Room room, QuestType questType, Optional<GameEntityType> keyEntity);

}
