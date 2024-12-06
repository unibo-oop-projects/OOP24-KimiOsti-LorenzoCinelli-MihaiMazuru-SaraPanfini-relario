package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.InventoryItem;

public interface QuestFactory {

    Quest createCollectItemQuest(InventoryItem item, MainCharacter player);

    Quest createDefeatEnemyQuest();

    Quest createSolvePuzzleQuest();

}
