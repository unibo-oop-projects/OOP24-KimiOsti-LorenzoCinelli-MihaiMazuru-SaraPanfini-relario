package it.unibo.oop.relario.model.entities;

import java.util.Optional;

import it.unibo.oop.relario.utils.api.Position;

/**
 * Interface to interact with every entity.
 */
public interface Entity {

    /**
     * Retrieves the name of the entity.
     * @return the name of the entity
     */
    String getName();

    /**
     * Reveals the current entity position.
     * @return the current position of the entity. 
     */
    Optional<Position> getPosition();

}
