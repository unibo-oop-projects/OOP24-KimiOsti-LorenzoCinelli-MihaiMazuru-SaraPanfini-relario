package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.living.MainCharacter;

public interface QuestFactory {

    Quest createCollectItemQuest(MainCharacter player);

    Quest createDefeatEnemyQuest();

    Quest createSolvePuzzleQuest();

}
