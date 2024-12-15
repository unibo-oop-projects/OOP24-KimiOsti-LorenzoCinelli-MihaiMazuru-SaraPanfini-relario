package it.unibo.oop.relario.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.relario.model.quest.Quest;
import it.unibo.oop.relario.model.quest.QuestFactory;
import it.unibo.oop.relario.model.quest.QuestFactoryImpl;
import it.unibo.oop.relario.model.quest.QuestType;

public class QuestManager {

    public static final int COLLECT_QUEST_INDEX = 2;
    public static final int SOLVE_PUZZLE_QUEST_INDEX = 3;
    public static final int DEFEAT_ENEMY_QUEST_INDEX = 4;

    private final QuestFactory questFactory = new QuestFactoryImpl();
    private final Map<QuestType, Optional<Quest>> questMap = new HashMap<>();

    public QuestManager() {
        this.questMap.put(QuestType.COLLECTION_QUEST, Optional.of(null)); // createCollectQuest()
        this.questMap.put(QuestType.SOLVE_PUZZLE_QUEST, Optional.of(this.questFactory.createSolvePuzzleQuest()));
        this.questMap.put(QuestType.DEFEAT_ENEMY_QUEST, Optional.of(this.questFactory.createDefeatEnemyQuest()));
    }

    private void handleQuest(final Room room, final QuestType questType) {
        final Optional<Quest> quest = this.questMap.get(questType);
        if (quest.isPresent()) {
            room.addEntity(null, quest.get().getKeyEntity());
        }
        room.setQuest(quest);
    }

    public void assigneQuest(final Room newRoom, final int indexRoom) {
        switch (indexRoom) {
            case COLLECT_QUEST_INDEX:
                handleQuest(newRoom, QuestType.COLLECTION_QUEST);
                break;
            case SOLVE_PUZZLE_QUEST_INDEX:
                handleQuest(newRoom, QuestType.SOLVE_PUZZLE_QUEST);
                break;
            case DEFEAT_ENEMY_QUEST_INDEX:
                handleQuest(newRoom, QuestType.DEFEAT_ENEMY_QUEST);
                break;
            default:
                return;
        }
    }
    
}
