package it.unibo.oop.relario.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.model.quest.Quest;
import it.unibo.oop.relario.model.quest.QuestFactory;
import it.unibo.oop.relario.model.quest.QuestFactoryImpl;

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
    private final Map<Integer, Optional<Quest>> roomQuests = new HashMap<>();

    /**
     * 
     * @param player
     */
    public QuestManager(final MainCharacter player) {
        this.roomQuests.put(FIRST_ROOM, Optional.empty());
        this.roomQuests.put(SECOND_ROOM, Optional.of(this.questFactory.createCollectItemQuest(player, InventoryItemType.KEY)));
        this.roomQuests.put(THIRD_ROOM, Optional.of(null)); //this.questFactory.createSolveRiddleQuest()
        this.roomQuests.put(FOURTH_ROOM, Optional.of(this.questFactory.createDefeatEnemyQuest(EnemyType.KNIGHT))); //EnemyType.BOSS??
        this.roomQuests.put(FIFTH_ROOM, Optional.empty());      
    }

    /**
     * 
     * @param room
     * @param indexRoom
     */
    public void assignQuest(final Room room, final int indexRoom) {
        final Optional<Quest> quest = this.roomQuests.get(indexRoom);
        if (quest.isEmpty()) {
            return;
        }
        room.setQuest(quest);
    }

}
