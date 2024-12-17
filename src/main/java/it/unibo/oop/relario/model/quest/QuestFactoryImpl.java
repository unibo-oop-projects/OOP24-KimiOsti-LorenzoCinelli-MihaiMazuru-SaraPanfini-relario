package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.living.MainCharacter;

/**
 * 
 */
public final class QuestFactoryImpl implements QuestFactory {

    private QuestImpl createQuest(final String name, final String description, 
    final ObjectiveStrategy objective, final Entity keyEntity) {
        return new QuestImpl(name, description, objective, keyEntity);
    }

    @Override
    public Quest createCollectItemQuest(final MainCharacter player, final Entity keyEntity) {
        return createQuest("", "", new CollectItemObjective(player), keyEntity);
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
