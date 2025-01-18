package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.model.inventory.InventoryItemType;

/**
 * 
 */
public final class QuestFactoryImpl implements QuestFactory {

    private QuestImpl createQuest(final String name, final String description, 
    final ObjectiveStrategy objective, final Entity keyEntity) {
        return new QuestImpl(name, description, objective, keyEntity);
    }

    @Override
    public Quest createCollectItemQuest(final MainCharacter player, final InventoryItemType keyItem) {
        return createQuest("", "", new CollectItemObjective(player), new InventoryItemFactoryImpl().createItem(keyItem));
    }

    @Override
    public Quest createDefeatEnemyQuest(final EnemyType keyEnemy) {
        return createQuest("", "", new DefeatEnemyObjective(), 
        new EnemyFactoryImpl().createEnemyByType(keyEnemy, null));
    }

    @Override
    public Quest createPressButtonQuest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPressButtonQuest'");
    }

}
