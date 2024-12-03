package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.map.Room;

public class QuestFactoryImpl implements QuestFactory {

    private final Room room; //??

    public QuestFactoryImpl(final Room room) {
        this.room = room;
    }

    private Quest createQuest(String name, String description, ObjectiveStrategy... objectives) {
        Quest quest = new Quest(name, description);
        for (ObjectiveStrategy objective : objectives) {
            quest.addObjective(objective);
        }
        return quest;
    }

    @Override
    public Quest createCollectItemQuest(InventoryItem item) {
        return createQuest("", "", new CollectItemObjective(this.room.getPlayer(), item));
    }

    @Override
    public Quest createDefeatEnemyQuest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createDefeatEnemyQuest'");
    }

    @Override
    public Quest createSolvePuzzleQuest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSolvePuzzleQuest'");
    }
    
}
