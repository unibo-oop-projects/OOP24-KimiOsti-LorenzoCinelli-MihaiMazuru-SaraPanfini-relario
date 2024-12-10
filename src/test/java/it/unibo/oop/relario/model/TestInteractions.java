package it.unibo.oop.relario.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.Transient;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.unibo.oop.relario.model.Interactions;
import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureItem;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;


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
    private Position pos;
    private Direction dir;
    private Map<Position, Optional<LivingBeing>> entityMap;
    private Map<Position, Optional<FurnitureItem>> furnitureMap;

    /**
     * Sets up the testing.
     */
    @BeforeEach
    void setUp() {
        depth = 50;
        width = 50;
    }

    /**
     * Tests if entities can move.
     */
    @Test
    void testCanMove() {

    }

    /**
     * Tests if entity can interact.
     */
    @Test
    void testCanInteract() {

    }

}
