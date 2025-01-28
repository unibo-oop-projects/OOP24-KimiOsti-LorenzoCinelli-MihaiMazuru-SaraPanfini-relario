package it.unibo.oop.relario.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactory;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureFactory;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureFactoryImpl;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureType;
import it.unibo.oop.relario.model.entities.living.MainCharacterImpl;
import it.unibo.oop.relario.model.entities.npc.NpcFactory;
import it.unibo.oop.relario.model.entities.npc.NpcFactoryImpl;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;


/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test class for the {@link Interactions} class.
 */
final class InteractionsTest {
    private static final boolean MOVEMENT = true;
    private static final boolean INTERACTION = false;

    private final FurnitureFactory ff = new FurnitureFactoryImpl();
    private final EnemyFactory ef = new EnemyFactoryImpl();
    private final NpcFactory nf = new NpcFactoryImpl();
    private Dimension dim;
    private Map<Position, LivingBeing> entityMap;
    private Map<Position, Furniture> furnitureMap;
    private List<Position> obstructingFurniture;
    private List<Position> interactiveFurniture;
    private List<Position> obstructingEntity;
    private List<Position> interactiveEntity;

    /**
     * Sets up the testing.
     */
    @BeforeEach
    void setUp() {
        this.dim  = new DimensionImpl(10, 15);
        this.entityMap = new HashMap<>();
        this.furnitureMap = new HashMap<>();
        this.obstructingFurniture = new ArrayList<>();
        this.interactiveFurniture = new ArrayList<>();
        this.obstructingEntity = new ArrayList<>();
        this.interactiveEntity = new ArrayList<>();
    }

    /**
     * Tests if entities can move.
     */
    @Test
    void testCanMove() {
        // test border movement
        this.forEach(MOVEMENT);

        // test movement with furniture
        this.furnitureSetup();
        this.forEach(MOVEMENT);

        // test movement with entities
        this.clearMap();
        this.entitySetup();
        this.forEach(MOVEMENT);

        // test movement
        this.clearMap();
        this.furnitureSetup();
        this.entitySetup();
        this.forEach(MOVEMENT);
    }

    /**
     * Tests if entity can interact.
     */
    @Test
    void testCanInteract() {
        // test interactions with furniture
        this.furnitureSetup();
        this.forEach(INTERACTION);

        // test interactions with entities
        this.clearMap();
        this.entitySetup();
        this.forEach(INTERACTION);

        // test interactions
        this.clearMap();
        this.furnitureSetup();
        this.entitySetup();
        this.forEach(INTERACTION);
    }

    private void furnitureSetup() {
        //armorstand
        Set<Position> positions = Set.of(
            new PositionImpl(0, 0),
            new PositionImpl(4, 0),
            new PositionImpl(8, 0),
            new PositionImpl(9, 7),
            new PositionImpl(0, 8),
            new PositionImpl(3, 9),
            new PositionImpl(7, 9)
        );
        for (final var p : positions) {
            this.addInteractiveFurniture(p, FurnitureType.ARMORSTAND);
        }

        //statue
        positions = Set.of(
            new PositionImpl(2, 0),
            new PositionImpl(6, 0),
            new PositionImpl(9, 1),
            new PositionImpl(9, 9),
            new PositionImpl(5, 9),
            new PositionImpl(0, 2),
            new PositionImpl(0, 6)
        );
        for (final var p : positions) {
            this.addObstructingFurniture(p, FurnitureType.STATUE);
        }

        //vase
        positions = Set.of(
            new PositionImpl(0, 3),
            new PositionImpl(0, 5),
            new PositionImpl(9, 3),
            new PositionImpl(9, 5)
        );
        for (final var p : positions) {
            this.addInteractiveFurniture(p, FurnitureType.VASE);
        }

        //carpet
        positions = Set.of(
            new PositionImpl(2, 3), new PositionImpl(2, 4), new PositionImpl(2, 5),
            new PositionImpl(3, 3), new PositionImpl(3, 4), new PositionImpl(3, 5),
            new PositionImpl(4, 3), new PositionImpl(4, 4), new PositionImpl(4, 5),
            new PositionImpl(5, 3), new PositionImpl(5, 4), new PositionImpl(5, 5),
            new PositionImpl(6, 3), new PositionImpl(6, 4), new PositionImpl(6, 5),
            new PositionImpl(7, 3), new PositionImpl(7, 4), new PositionImpl(7, 5)
        );
        for (final var p : positions) {
            this.addWalkableFurniture(p, FurnitureType.CARPET);
        }

        //chest
        positions = Set.of(
            new PositionImpl(3, 1),
            new PositionImpl(5, 1)
        );
        for (final var p : positions) {
            this.addInteractiveFurniture(p, FurnitureType.CHEST);
        }

        //wardrobe
        positions = Set.of(
            new PositionImpl(3, 8),
            new PositionImpl(5, 8)
        );
        for (final var p : positions) {
            this.addObstructingFurniture(p, FurnitureType.WARDROBE);
        }

        //trapdor
        this.addWalkableFurniture(new PositionImpl(4, 7), FurnitureType.TRAPDOOR);
    }

    private void entitySetup() {
        //non interactive npc
        Set<Position> positions = Set.of(
            new PositionImpl(1, 1),
            new PositionImpl(5, 3)
        );
        for (final var p : positions) {
            this.addNpc(p, false);
        }

        //interactive npc
        positions = Set.of(
            new PositionImpl(3, 4),
            new PositionImpl(0, 9)
        );
        for (final var p : positions) {
            this.addNpc(p, true);
        }

        //enemies
        this.addEnemy(new PositionImpl(4, 2), EnemyType.THIEF);
        this.addEnemy(new PositionImpl(7, 2), EnemyType.KNIGHT);
        this.addEnemy(new PositionImpl(4, 8), EnemyType.SOLDIER);
        this.addEnemy(new PositionImpl(7, 7), EnemyType.WIZARD);
        this.addEnemy(new PositionImpl(5, 5), EnemyType.BOSS);

        //character
        final var p = new PositionImpl(0, 4);
        entityMap.put(p, new MainCharacterImpl());
        obstructingEntity.add(p);
        interactiveEntity.add(p);
    }

    private void clearMap() {
        furnitureMap.clear();
        entityMap.clear();
        obstructingFurniture.clear();
        interactiveFurniture.clear();
        obstructingEntity.clear();
        interactiveEntity.clear();
    }

    private void addInteractiveFurniture(final Position p, final FurnitureType type) {
        furnitureMap.put(p, ff.createInteractiveFurnitureByItem(p, type));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);
    }

    private void addObstructingFurniture(final Position p, final FurnitureType type) {
        furnitureMap.put(p, ff.createObstructingFurnitureByItem(p, type));
        obstructingFurniture.add(p);
    }

    private void addWalkableFurniture(final Position p, final FurnitureType type) {
        furnitureMap.put(p, ff.createWalkableFurnitureByItem(p, type));
        interactiveFurniture.add(p);
    }

    private void addNpc(final Position p, final boolean interactive) {
        entityMap.put(p, interactive ? nf.createInteractiveNpc(p) : nf.createDefaultNpc(p));
        obstructingEntity.add(p);
        interactiveEntity.add(p);
    }

    private void addEnemy(final Position p, final EnemyType type) {
        entityMap.put(p, ef.createEnemyByType(type, p));
        obstructingEntity.add(p);
        interactiveEntity.add(p);
    }

    private void forEach(final boolean testType) {
        for (int j = 0; j < dim.getHeight(); j++) {
            for (int i = 0; i < dim.getWidth(); i++) {
                final var pos = new PositionImpl(i, j);
                for (final var dir : Direction.values()) {
                    final Position nextPos = dir.move(pos);
                    if (testType) {
                        movementCheck(pos, dir, nextPos);
                    } else {
                        interactionCheck(pos, dir, nextPos);
                    }
                }
            }
        }
    }

    private void movementCheck(final Position pos, final Direction dir, final Position nextPos) {
        if (nextPos.getX() >= 0
        && nextPos.getX() < dim.getWidth()
        && nextPos.getY() >= 0
        && nextPos.getY() < dim.getHeight()
        && !obstructingFurniture.contains(nextPos)
        && !obstructingEntity.contains(nextPos)) {
            assertTrue(Interactions.canMove(pos, dir, dim, entityMap, furnitureMap));
        } else {
            assertFalse(Interactions.canMove(pos, dir, dim, entityMap, furnitureMap));
        }
    }

    private void interactionCheck(final Position pos, final Direction dir, final Position nextPos) {
        if (interactiveEntity.contains(nextPos)
            || interactiveFurniture.contains(nextPos)) {
            assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
        } else {
            assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
        }
    }
}
