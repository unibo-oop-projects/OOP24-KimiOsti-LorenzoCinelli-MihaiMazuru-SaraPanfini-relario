package it.unibo.oop.relario.model.quest;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import it.unibo.oop.relario.model.GameEntityType;

/**
 * Implementation of {@link QuestFactory}.
 */
public final class QuestFactoryImpl implements QuestFactory {

    private final Map<QuestType, BiFunction<String, Optional<GameEntityType>, Quest>> questCreator;

    /**
     * Instantiates a {@link Quest} factory.
     */
    public QuestFactoryImpl() {
        this.questCreator = new EnumMap<>(QuestType.class);
        questCreator.put(QuestType.COLLECTION_QUEST, (description, keyEntity) -> 
        createQuest(description, new CollectItemObjective(keyEntity)));
        questCreator.put(QuestType.DEFEAT_ENEMY_QUEST, (description, keyEntity) -> 
        createQuest(description, new DefeatEnemyObjective(keyEntity)));
        questCreator.put(QuestType.NPC_INTERACT_QUEST, (description, keyEntity) ->
        createQuest(description, new NpcInteractObjective(keyEntity)));
    }

    @Override
    public Quest createQuestByType(final String description, final QuestType questType,
        final Optional<GameEntityType> keyEntity) {
            return questCreator.get(questType).apply(description, keyEntity);
    }

    private QuestImpl createQuest(final String description, final ObjectiveStrategy objective) {
        return new QuestImpl(description, objective);
    }

}
