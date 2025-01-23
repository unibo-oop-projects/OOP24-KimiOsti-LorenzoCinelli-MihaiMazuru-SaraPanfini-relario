package it.unibo.oop.relario.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.model.quest.QuestFactory;
import it.unibo.oop.relario.model.quest.QuestFactoryImpl;
import it.unibo.oop.relario.model.quest.QuestType;
import it.unibo.oop.relario.utils.impl.Pair;

/**
 * 
 */

public class QuestManager {

    /** Index of the first room. */
    public static final int FIRST_ROOM = 1;

    /** Index of the second room. */
    public static final int SECOND_ROOM = 2;

    /** Index of the third room. */
    public static final int THIRD_ROOM = 3;

    /** Index of the fourth room. */
    public static final int FOURTH_ROOM = 4;

    /** Index of the fifth room. */
    public static final int FIFTH_ROOM = 5;

    private final QuestFactory questFactory = new QuestFactoryImpl();
    private final Map<Integer, Pair<QuestType, Optional<GameEntityType>>> roomQuests = new HashMap<>();

    public QuestManager() {
        this.roomQuests.put(FIRST_ROOM, new Pair<>(QuestType.NO_QUEST, Optional.empty()));
        this.roomQuests.put(SECOND_ROOM, new Pair<>(QuestType.COLLECTION_QUEST, Optional.of(InventoryItemType.KEY)));
        this.roomQuests.put(THIRD_ROOM, null);
        this.roomQuests.put(FOURTH_ROOM, new Pair<>(QuestType.DEFEAT_ENEMY_QUEST, Optional.of(EnemyType.BOSS)));
        this.roomQuests.put(FIFTH_ROOM, new Pair<>(QuestType.NO_QUEST, Optional.empty()));
    }

    /**
     * 
     * @param room
     * @param indexRoom
     */
    public void assignQuest(final Room room, final int indexRoom) {
        Pair<QuestType, Optional<GameEntityType>> quest = this.roomQuests.get(indexRoom);
        room.setQuest(quest.getX().equals(QuestType.NO_QUEST) ? Optional.empty() 
        : Optional.of(this.questFactory.createQuestByType(room, quest.getX(), quest.getY())));
    }

}
