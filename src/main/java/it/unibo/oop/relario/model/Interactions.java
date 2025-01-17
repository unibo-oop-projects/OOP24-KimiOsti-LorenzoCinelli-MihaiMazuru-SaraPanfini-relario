package it.unibo.oop.relario.model;

import java.util.Map;
import java.util.Optional;

import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;

/**
 * Static class for handling interactions and collision between moving entities and map's elements. 
 */
public final class Interactions {

    private Interactions() {
        throw new UnsupportedOperationException();
    }

    private static boolean isPositionIntoRoomBorder(final Position pos, final int depth, final int width) {
        return !(pos.getX() < 0 || pos.getY() < 0 || pos.getX() >= width || pos.getY() >= depth);
    }

    private static boolean isPositionWithEntity(final Position pos, final Map<Position, LivingBeing> entityMap) {
        return entityMap.containsKey(pos);
    }

    private static boolean isPositionWithObstructingForniture(final Position pos, 
    final Map<Position, Furniture> furnitureMap) {
        return furnitureMap.containsKey(pos) && !furnitureMap.get(pos).isWalkable();
    }

    private static boolean isPositionWithInteractiveFurniture(final Position pos, 
    final Map<Position, Furniture> furnitureMap) {
        return furnitureMap.containsKey(pos) && furnitureMap.get(pos).isInteractive();
    }

    /**
     * Checks if the next position is available.
     * @param pos the initial position of the entity.
     * @param dir the direction the entity is moving.
     * @param dimension the dimension of the room.
     * @param entityMap position-entity map.
     * @param furnitureMap position-furniture map.
     * @return true if the position in front is available, false otherwise.
     */
    public static boolean canMove(final Position pos, final Direction dir, final Dimension dimension, 
    final Map<Position, LivingBeing> entityMap, final Map<Position, Furniture> furnitureMap) {
        final Position nextPos = dir.move(pos);
        return isPositionIntoRoomBorder(nextPos, dimension.getHeight(), dimension.getWidth())
        && !isPositionWithEntity(nextPos, entityMap)
        && !isPositionWithObstructingForniture(nextPos, furnitureMap);
    }

    /**
     * Checks if the next position is interactive.
     * @param pos the position of the entity.
     * @param dir the direction the entity is facing.
     * @param entityMap position-entity map.
     * @param furnitureMap position-furniture map.
     * @return true if the position in front is interactive, false otherwise.
     */
    public static boolean canInteract(final Position pos, final Direction dir, 
    final Map<Position, LivingBeing> entityMap, final Map<Position, Furniture> furnitureMap) {
        final Position nextPos = dir.move(pos);
        return isPositionWithEntity(nextPos, entityMap)
        || isPositionWithInteractiveFurniture(nextPos, furnitureMap);
    }

}
