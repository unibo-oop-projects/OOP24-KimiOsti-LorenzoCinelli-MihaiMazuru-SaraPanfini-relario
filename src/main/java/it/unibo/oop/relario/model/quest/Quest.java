package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;

/**
 * 
 */

public interface Quest {

    /**
     * 
     * @return TODO
     */
    String getName();

    /**
     * 
     * @return TODO
     */
    String getDescription();

    /**
     * 
     * @return TODO
     */
    boolean isCompleted();

    /**
     * 
     * @return TODO
     */
    Entity getKeyEntity();

}
