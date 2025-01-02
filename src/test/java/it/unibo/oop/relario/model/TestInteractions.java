package it.unibo.oop.relario.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.Transient;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.unibo.oop.relario.model.Interactions;
import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.LivingBeingImpl;
import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.model.entities.living.MainCharacterImpl;
import it.unibo.oop.relario.model.map.LivingBeingsGenerator;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;


/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test class for the {@link Interactions} class.
 */
final class TestInteractions {
    
    private int depth;
    private int width;
    // private Position pos;
    // private Direction dir;
    private Map<Position, Optional<LivingBeing>> entityMap;
    private Map<Position, Optional<Furniture>> furnitureMap;

    /**
     * Sets up the testing.
     */
    @BeforeEach
    void setUp() {
        depth = 5;
        width = 5;
        entityMap = new HashMap<>();
        furnitureMap = new HashMap<>();
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                Position p = new PositionImpl(i, j);
                entityMap.put(p, Optional.empty());
                furnitureMap.put(p, Optional.empty());
            }
        }
        // entityMap.put(new PositionImpl(0, 0), Optional.of(new MainCharacterImpl()));
    }

    @Test
    void testMaps() {
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                Position p1 = new PositionImpl(i, j);
                assertNotNull(entityMap.get(p1));
                assertNotNull(furnitureMap.get(p1));
            }
        }
    }

    /**
     * Tests if entities can move.
     */
    @Test
    void testCanMove() {
        final Position pos = new PositionImpl(0,0);
        Direction dir;

        dir = Direction.UP;
        System.out.println("UP");
        System.out.println(entityMap.get(dir.move(pos)));
        assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
        
        dir = Direction.RIGHT;
        System.out.println("RIGHT");
        System.out.println(entityMap.get(dir.move(pos)));
        assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));

        dir = Direction.LEFT;
        System.out.println("LEFT");
        System.out.println(entityMap.get(dir.move(pos)));
        assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));

        dir = Direction.DOWN;
        System.out.println("DOWN");
        System.out.println(entityMap.get(dir.move(pos)));
        assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
    }

    /**
     * Tests if entity can interact.
     */
    @Test
    void testCanInteract() {

    }

}
