package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.inventory.InventoryItem;

public interface QuestFactory {
    
    Quest createCollectItemQuest(InventoryItem item);

    Quest createDefeatEnemyQuest();

    Quest createSolvePuzzleQuest();

}
