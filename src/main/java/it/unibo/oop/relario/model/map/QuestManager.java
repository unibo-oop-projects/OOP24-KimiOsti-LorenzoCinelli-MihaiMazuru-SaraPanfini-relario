package it.unibo.oop.relario.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.entities.npc.NpcFactoryImpl;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.model.quest.Quest;
import it.unibo.oop.relario.model.quest.QuestFactory;
import it.unibo.oop.relario.model.quest.QuestFactoryImpl;
import it.unibo.oop.relario.utils.impl.PositionImpl;

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
        this.roomQuests.put(THIRD_ROOM, Optional.of(this.questFactory.createSolvePuzzleQuest()));
        this.roomQuests.put(FOURTH_ROOM, Optional.of(this.questFactory.createDefeatEnemyQuest()));
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
        final Entity keyEntity = quest.get().getKeyEntity();
        if (keyEntity instanceof InventoryItem) {
            // keyEntity = new FurnitureItemFactoryImpl().createInteractiveFurnitureItem(null, keyEntity);
        }
        
        room.addEntity(null, keyEntity); //room.getAvailablePosition()??
        room.addEntity(new PositionImpl(room.getExit().getX() - 1, room.getExit().getY() - 1), 
        new NpcFactoryImpl().createQuestNpc(new PositionImpl(room.getExit().getX() - 1, room.getExit().getY() - 1), 
        quest.get().getDescription()));

        room.setQuest(quest);

    }

}
