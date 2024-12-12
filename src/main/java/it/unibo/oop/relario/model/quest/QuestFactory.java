package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.living.MainCharacter;

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
    Quest createCollectItemQuest(MainCharacter player, Entity keyEntity);

    /**
     * 
     * @return TODO
     */
    Quest createDefeatEnemyQuest();

    /**
     * 
     * @return TODO
     */
    Quest createSolvePuzzleQuest();

}
