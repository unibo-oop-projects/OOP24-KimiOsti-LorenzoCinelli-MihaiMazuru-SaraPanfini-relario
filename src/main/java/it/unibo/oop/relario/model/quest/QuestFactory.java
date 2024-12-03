package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.inventory.InventoryItem;

public interface QuestFactory {

    QuestImpl createCollectItemQuest(InventoryItem item);

    QuestImpl createDefeatEnemyQuest();

    QuestImpl createSolvePuzzleQuest();

}
