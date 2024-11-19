package it.unibo.oop.relario.model.entities;

import it.unibo.oop.relario.utils.api.Position;

/**
 * Interface to interact with every entity.
 */
public interface Entity {

    /**
     * Reveals the current entity position.
     * @return the current position of the entity. 
     */
    public Position getPosition();

}
