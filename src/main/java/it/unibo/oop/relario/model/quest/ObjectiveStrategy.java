package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;

/**
 * 
 */

public interface ObjectiveStrategy {

    /**
     * 
     * @param keyEntity
     * @return TODO
     */
    boolean check(Entity keyEntity);

}
