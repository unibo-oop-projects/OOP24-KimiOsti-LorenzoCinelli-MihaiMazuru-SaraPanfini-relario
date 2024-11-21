package it.unibo.oop.relario.model;

import java.util.Map;
import java.util.Optional;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.furniture.FurnitureItem;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;

/**
 * Static class for handling interactions and collision between player and map's elements. 
 */
public final class Interactions {

    private Interactions() {
        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the next position is available.
     * @param pos the initial position of the entity.
     * @param dir the direction the entity is moving.
     * @param depth the depth of the room
     * @param width the width of the room
     * @param entityMap position-entity map
     * @param furnitureMap position-furniture map
     * @return true if the position is available, false otherwise.
     */
    public static boolean canMove(final Position pos, final Direction dir, final int depth, final int width, final Map<Position, Optional<Entity>> entityMap, final Map<Position, Optional<FurnitureItem>> fornitureMap) {
        return false;
    }

    /**
     * Checks if the next position is interactive.
     * @param pos the position of the entity.
     * @param dir the direction the entity is facing.
     * @param entityMap position-entity map
     * @param furnitureMap position-furniture map
     * @return true if the position is interactive, false otherwise.
     */
    public static boolean canInteract(final Position pos, final Direction dir, final Map<Position, Optional<Entity>> entityMap, final Map<Position, Optional<FurnitureItem>> fornitureMap) {
        return false;
    }

}
