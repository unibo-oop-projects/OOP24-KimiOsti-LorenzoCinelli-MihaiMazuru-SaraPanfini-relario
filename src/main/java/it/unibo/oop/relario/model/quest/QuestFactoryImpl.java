package it.unibo.oop.relario.model.quest;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.map.Room;

/**
 * Implementation of {@link QuestFactory}.
 */
public final class QuestFactoryImpl implements QuestFactory {

    private final Map<QuestType, BiFunction<Room, Optional<GameEntityType>, Quest>> questCreator;

    /**
     * Instantiates a {@link Quest} factory.
     */
    public QuestFactoryImpl() {
        this.questCreator = new EnumMap<>(QuestType.class);
        questCreator.put(QuestType.COLLECTION_QUEST, (room, keyEntity) -> 
        createQuest("", room, new CollectItemObjective(keyEntity)));
        questCreator.put(QuestType.DEFEAT_ENEMY_QUEST, (room, keyEntity) -> 
        createQuest("", room, new DefeatEnemyObjective(keyEntity)));
        questCreator.put(QuestType.NPC_INTERACT_QUEST, (room, keyEntity) ->
        createQuest("", room, new NpcInteractObjective(keyEntity)));
    }

    @Override
    public Quest createQuestByType(final Room room, final QuestType questType, final Optional<GameEntityType> keyEntity) {
        return questCreator.get(questType).apply(room, keyEntity);
    }

    private QuestImpl createQuest(final String description, final Room room, 
    final ObjectiveStrategy objective) {
        return new QuestImpl(description, objective);
    }

}
