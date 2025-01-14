package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.InventoryItemType;

/**
 * 
 */
public interface QuestFactory {

    /**
     * 
     * @param player
     * @param keyEntity
     * @return TODO
     */
    Quest createCollectItemQuest(MainCharacter player, InventoryItemType keyItem);

    /**
     * 
     * @return TODO
     */
    Quest createDefeatEnemyQuest(EnemyType keyEnemy);

    /**
     * 
     * @return TODO
     */
    Quest createPressButtonQuest();

}
