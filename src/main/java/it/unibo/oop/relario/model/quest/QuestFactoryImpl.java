package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.map.Room;

public final class QuestFactoryImpl implements QuestFactory {

    private final Room room; //??

    public QuestFactoryImpl(final Room room) {
        this.room = room.getRoom();
    }

    private QuestImpl createQuest(final String name, final String description, final ObjectiveStrategy... objectives) {
        final QuestImpl quest = new QuestImpl(name, description);
        for (final ObjectiveStrategy objective : objectives) {
            quest.addObjective(objective);
        }
        return quest;
    }

    @Override
    public QuestImpl createCollectItemQuest(final InventoryItem item) {
        return createQuest("", "", new CollectItemObjective(this.room, item));
    }

    @Override
    public QuestImpl createDefeatEnemyQuest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createDefeatEnemyQuest'");
    }

    @Override
    public QuestImpl createSolvePuzzleQuest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSolvePuzzleQuest'");
    }

}
