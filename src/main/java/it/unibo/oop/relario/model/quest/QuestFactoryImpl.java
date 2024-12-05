package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;

public final class QuestFactoryImpl implements QuestFactory {

    private QuestImpl createQuest(final String name, final String description, final ObjectiveStrategy objective) {
        return new QuestImpl(name, description, objective);
    }

    @Override
    public Quest createCollectItemQuest(final MainCharacter player) {
        return createQuest("", "", new CollectItemObjective(player, new InventoryItemFactoryImpl().createItem(null)));
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
