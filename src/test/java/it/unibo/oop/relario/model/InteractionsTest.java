package it.unibo.oop.relario.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
final class InteractionsTest {

    private int depth;
    private int width;
    private Map<Position, Optional<LivingBeing>> entityMap;
    private Map<Position, Optional<Furniture>> furnitureMap;
    private List<Position> obstructingFurniture;
    private List<Position> interactiveFurniture;
    private List<Position> obstructingEntity;
    private List<Position> interactiveEntity;

    private void furnitureSetup() {
        final FurnitureFactory ff = new FurnitureFactoryImpl();
        Position p;

        //armorstand
        p = new PositionImpl(0, 0);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.ARMORSTAND)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);
        p = new PositionImpl(4, 0);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.ARMORSTAND)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);
        p = new PositionImpl(8, 0);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.ARMORSTAND)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);
        p = new PositionImpl(9, 7);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.ARMORSTAND)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);
        p = new PositionImpl(0, 8);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.ARMORSTAND)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);
        p = new PositionImpl(3, 9);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.ARMORSTAND)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);
        p = new PositionImpl(7, 9);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.ARMORSTAND)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);

        //statue
        p = new PositionImpl(2, 0);
        furnitureMap.put(p, Optional.of(ff.createObstructingFurnitureByItem(p, FurnitureType.STATUE)));
        obstructingFurniture.add(p);
        p = new PositionImpl(6, 0);
        furnitureMap.put(p, Optional.of(ff.createObstructingFurnitureByItem(p, FurnitureType.STATUE)));
        obstructingFurniture.add(p);
        p = new PositionImpl(9, 1);
        furnitureMap.put(p, Optional.of(ff.createObstructingFurnitureByItem(p, FurnitureType.STATUE)));
        obstructingFurniture.add(p);
        p = new PositionImpl(9, 9);
        furnitureMap.put(p, Optional.of(ff.createObstructingFurnitureByItem(p, FurnitureType.STATUE)));
        obstructingFurniture.add(p);
        p = new PositionImpl(5, 9);
        furnitureMap.put(p, Optional.of(ff.createObstructingFurnitureByItem(p, FurnitureType.STATUE)));
        obstructingFurniture.add(p);
        p = new PositionImpl(0, 2);
        furnitureMap.put(p, Optional.of(ff.createObstructingFurnitureByItem(p, FurnitureType.STATUE)));
        obstructingFurniture.add(p);
        p = new PositionImpl(0, 6);
        furnitureMap.put(p, Optional.of(ff.createObstructingFurnitureByItem(p, FurnitureType.STATUE)));
        obstructingFurniture.add(p);

        //vase
        p = new PositionImpl(0, 3);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.VASE)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);
        p = new PositionImpl(0, 5);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.VASE)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);
        p = new PositionImpl(9, 3);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.VASE)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);
        p = new PositionImpl(9, 5);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.VASE)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);

        //carpet
        p = new PositionImpl(2, 3);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(2, 4);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(2, 5);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);

        p = new PositionImpl(3, 3);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(3, 4);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(3, 5);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);

        p = new PositionImpl(4, 3);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(4, 4);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(4, 5);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);

        p = new PositionImpl(5, 3);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(5, 4);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(5, 5);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);

        p = new PositionImpl(6, 3);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(6, 4);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(6, 5);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);

        p = new PositionImpl(7, 3);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(7, 4);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);
        p = new PositionImpl(7, 5);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.CARPET)));
        interactiveFurniture.add(p);

        //chest
        p = new PositionImpl(3, 1);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.CHEST)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);
        p = new PositionImpl(5, 1);
        furnitureMap.put(p, Optional.of(ff.createInteractiveFurnitureByItem(p, FurnitureType.CHEST)));
        obstructingFurniture.add(p);
        interactiveFurniture.add(p);

        //wardrobe
        p = new PositionImpl(3, 8);
        furnitureMap.put(p, Optional.of(ff.createObstructingFurnitureByItem(p, FurnitureType.WARDROBE)));
        obstructingFurniture.add(p);
        p = new PositionImpl(5, 8);
        furnitureMap.put(p, Optional.of(ff.createObstructingFurnitureByItem(p, FurnitureType.WARDROBE)));
        obstructingFurniture.add(p);

        //trapdor
        p = new PositionImpl(4, 7);
        furnitureMap.put(p, Optional.of(ff.createWalkableFurnitureByItem(p, FurnitureType.TRAPDOOR)));
        interactiveFurniture.add(p);

    }

    private void entitySetup() {
        final EnemyFactory ef = new EnemyFactoryImpl();
        final NpcFactory nf = new NpcFactoryImpl();
        Position p;

        //non interactive npc
        p = new PositionImpl(1, 1);
        entityMap.put(p, Optional.of(nf.createNotInteractiveNpc(p)));
        obstructingEntity.add(p);
        interactiveEntity.add(p);
        p = new PositionImpl(5, 3);
        entityMap.put(p, Optional.of(nf.createNotInteractiveNpc(p)));
        obstructingEntity.add(p);
        interactiveEntity.add(p);

        //interactive npc
        p = new PositionImpl(3, 4);
        entityMap.put(p, Optional.of(nf.createInteractiveNpc(p)));
        obstructingEntity.add(p);
        interactiveEntity.add(p);
        p = new PositionImpl(0, 9);
        entityMap.put(p, Optional.of(nf.createInteractiveNpc(p)));
        obstructingEntity.add(p);
        interactiveEntity.add(p);

        //enemies
        p = new PositionImpl(4, 2);
        entityMap.put(p, Optional.of(ef.createEnemyByType(EnemyType.THIEF, p)));
        obstructingEntity.add(p);
        interactiveEntity.add(p);
        p = new PositionImpl(7, 2);
        entityMap.put(p, Optional.of(ef.createEnemyByType(EnemyType.KNIGHT, p)));
        obstructingEntity.add(p);
        interactiveEntity.add(p);
        p = new PositionImpl(4, 8);
        entityMap.put(p, Optional.of(ef.createEnemyByType(EnemyType.SOLDIER, p)));
        obstructingEntity.add(p);
        interactiveEntity.add(p);
        p = new PositionImpl(7, 7);
        entityMap.put(p, Optional.of(ef.createEnemyByType(EnemyType.WIZARD, p)));
        obstructingEntity.add(p);
        interactiveEntity.add(p);

        //character
        p = new PositionImpl(0, 4);
        entityMap.put(p, Optional.of(new MainCharacterImpl()));
        obstructingEntity.add(p);
        interactiveEntity.add(p);
    }

    private void clearMap() {
        for (int j = 0; j < depth; j++) {
            for (int i = 0; i < width; i++) {
                final Position p = new PositionImpl(i, j);
                furnitureMap.put(p, Optional.empty());
                entityMap.put(p, Optional.empty());
            }
        }
        obstructingFurniture.clear();
        interactiveFurniture.clear();
        obstructingEntity.clear();
        interactiveEntity.clear();
    }

    /**
     * Sets up the testing.
     */
    @BeforeEach
    void setUp() {
        depth = 10;
        width = 15;
        entityMap = new HashMap<>();
        furnitureMap = new HashMap<>();
        obstructingFurniture = new ArrayList<>();
        interactiveFurniture = new ArrayList<>();
        obstructingEntity = new ArrayList<>();
        interactiveEntity = new ArrayList<>();
        clearMap();
    }

    /**
     * Sub-testing for the movement into the borders of the room.
     */
    @Test
    void testBorderMovement() {
        Position pos;
        Direction dir;

        for (int j = 0; j < depth; j++) {
            for (int i = 0; i < width; i++) {
                pos = new PositionImpl(i, j);

                dir = Direction.UP;
                if (pos.getY() > 0) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }

                dir = Direction.RIGHT;
                if (pos.getX() < width - 1) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }

                dir = Direction.LEFT;
                if (pos.getX() > 0) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }

                dir = Direction.DOWN;
                if (pos.getY() < depth - 1) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }
            }
        }
    }

    /**
     * Sub-testing for the movement with furniture.
     */
    @Test
    void testFurnitureMovement() {
        furnitureSetup();

        Position pos;
        Direction dir;
        Position nextPos;

        for (int j = 0; j < depth; j++) {
            for (int i = 0; i < width; i++) {
                pos = new PositionImpl(i, j);

                dir = Direction.UP;
                nextPos = dir.move(pos);
                if (!obstructingFurniture.contains(nextPos)
                && furnitureMap.get(nextPos) != null
                && nextPos.getX() >= 0
                && nextPos.getX() < width
                && nextPos.getY() >= 0
                && nextPos.getY() < depth) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }

                dir = Direction.LEFT;
                nextPos = dir.move(pos);
                if (!obstructingFurniture.contains(nextPos)
                && furnitureMap.get(nextPos) != null
                && nextPos.getX() >= 0
                && nextPos.getX() < width
                && nextPos.getY() >= 0
                && nextPos.getY() < depth) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }

                dir = Direction.RIGHT;
                nextPos = dir.move(pos);
                if (!obstructingFurniture.contains(nextPos)
                && furnitureMap.get(nextPos) != null
                && nextPos.getX() >= 0
                && nextPos.getX() < width
                && nextPos.getY() >= 0
                && nextPos.getY() < depth) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }

                dir = Direction.DOWN;
                nextPos = dir.move(pos);
                if (!obstructingFurniture.contains(nextPos)
                && furnitureMap.get(nextPos) != null
                && nextPos.getX() >= 0
                && nextPos.getX() < width
                && nextPos.getY() >= 0
                && nextPos.getY() < depth) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }
            }
        }
    }

    /**
     * Sub-testing for the movement with entities.
     */
    @Test
    void testEntityMovement() {
        entitySetup();

        Position pos;
        Direction dir;
        Position nextPos;

        for (int j = 0; j < depth; j++) {
            for (int i = 0; i < width; i++) {
                pos = new PositionImpl(i, j);

                dir = Direction.UP;
                nextPos = dir.move(pos);
                if (!obstructingEntity.contains(nextPos)
                && entityMap.get(nextPos) != null
                && nextPos.getX() >= 0
                && nextPos.getX() < width
                && nextPos.getY() >= 0
                && nextPos.getY() < depth) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }

                dir = Direction.LEFT;
                nextPos = dir.move(pos);
                if (!obstructingEntity.contains(nextPos)
                && entityMap.get(nextPos) != null
                && nextPos.getX() >= 0
                && nextPos.getX() < width
                && nextPos.getY() >= 0
                && nextPos.getY() < depth) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }

                dir = Direction.RIGHT;
                nextPos = dir.move(pos);
                if (!obstructingEntity.contains(nextPos)
                && entityMap.get(nextPos) != null
                && nextPos.getX() >= 0
                && nextPos.getX() < width
                && nextPos.getY() >= 0
                && nextPos.getY() < depth) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }

                dir = Direction.DOWN;
                nextPos = dir.move(pos);
                if (!obstructingEntity.contains(nextPos)
                && entityMap.get(nextPos) != null
                && nextPos.getX() >= 0
                && nextPos.getX() < width
                && nextPos.getY() >= 0
                && nextPos.getY() < depth) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }
            }
        }
    }

    /**
     * Sub-testing for the general movement.
     */
    @Test
    void testMovement() {
        furnitureSetup();
        entitySetup();

        Position pos;
        Direction dir;
        Position nextPos;

        for (int j = 0; j < depth; j++) {
            for (int i = 0; i < width; i++) {
                pos = new PositionImpl(i, j);

                dir = Direction.UP;
                nextPos = dir.move(pos);
                if (furnitureMap.get(nextPos) != null
                && entityMap.get(nextPos) != null
                && nextPos.getX() >= 0
                && nextPos.getX() < width
                && nextPos.getY() >= 0
                && nextPos.getY() < depth
                && !obstructingFurniture.contains(nextPos)
                && !obstructingEntity.contains(nextPos)) {
                    assertTrue(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canMove(pos, dir, depth, width, entityMap, furnitureMap));
                }
            }
        }
    }

    /**
     * Tests if entities can move.
     */
    @Test
    void testCanMove() {
        testBorderMovement();
        clearMap();
        testFurnitureMovement();
        clearMap();
        testEntityMovement();
        clearMap();
        testMovement();
    }

    /**
     * Sub-testing for the interaction with furniture.
     */
    @Test
    void testFurnitureInteraction() {
        furnitureSetup();

        Position pos;
        Direction dir;
        Position nextPos;

        for (int j = 0; j < depth; j++) {
            for (int i = 0; i < width; i++) {
                pos = new PositionImpl(i, j);

                dir = Direction.UP;
                nextPos = dir.move(pos);
                if (furnitureMap.get(nextPos) != null
                && interactiveFurniture.contains(nextPos)) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }

                dir = Direction.LEFT;
                nextPos = dir.move(pos);
                if (furnitureMap.get(nextPos) != null
                && interactiveFurniture.contains(nextPos)) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }

                dir = Direction.RIGHT;
                nextPos = dir.move(pos);
                if (furnitureMap.get(nextPos) != null
                && interactiveFurniture.contains(nextPos)) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }

                dir = Direction.DOWN;
                nextPos = dir.move(pos);
                if (furnitureMap.get(nextPos) != null
                && interactiveFurniture.contains(nextPos)) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }
            }
        }
    }

    /**
     * Sub-testing for the interaction with entities.
     */
    @Test
    void testEntityInteraction() {
        entitySetup();

        Position pos;
        Direction dir;
        Position nextPos;

        for (int j = 0; j < depth; j++) {
            for (int i = 0; i < width; i++) {
                pos = new PositionImpl(i, j);

                dir = Direction.UP;
                nextPos = dir.move(pos);
                if (entityMap.get(nextPos) != null
                && interactiveEntity.contains(nextPos)) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }

                dir = Direction.LEFT;
                nextPos = dir.move(pos);
                if (entityMap.get(nextPos) != null
                && interactiveEntity.contains(nextPos)) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }

                dir = Direction.RIGHT;
                nextPos = dir.move(pos);
                if (entityMap.get(nextPos) != null
                && interactiveEntity.contains(nextPos)) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }

                dir = Direction.DOWN;
                nextPos = dir.move(pos);
                if (entityMap.get(nextPos) != null
                && interactiveEntity.contains(nextPos)) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }
            }
        }
    }

    /**
     * Sub-testing for the general interaction.
     */
    @Test
    void testInteraction() {
        furnitureSetup();
        entitySetup();

        Position pos;
        Direction dir;
        Position nextPos;

        for (int j = 0; j < depth; j++) {
            for (int i = 0; i < width; i++) {
                pos = new PositionImpl(i, j);

                dir = Direction.UP;
                nextPos = dir.move(pos);
                if (furnitureMap.get(nextPos) != null
                && entityMap.get(nextPos) != null
                && (interactiveEntity.contains(nextPos)
                    || interactiveFurniture.contains(nextPos))) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }

                dir = Direction.LEFT;
                nextPos = dir.move(pos);
                if (furnitureMap.get(nextPos) != null
                && entityMap.get(nextPos) != null
                && (interactiveEntity.contains(nextPos)
                    || interactiveFurniture.contains(nextPos))) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }

                dir = Direction.RIGHT;
                nextPos = dir.move(pos);
                if (furnitureMap.get(nextPos) != null
                && entityMap.get(nextPos) != null
                && (interactiveEntity.contains(nextPos)
                    || interactiveFurniture.contains(nextPos))) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }

                dir = Direction.DOWN;
                nextPos = dir.move(pos);
                if (furnitureMap.get(nextPos) != null
                && entityMap.get(nextPos) != null
                && (interactiveEntity.contains(nextPos)
                    || interactiveFurniture.contains(nextPos))) {
                    assertTrue(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                } else {
                    assertFalse(Interactions.canInteract(pos, dir, entityMap, furnitureMap));
                }
            }
        }
    }

    /**
     * Tests if entity can interact.
     */
    @Test
    void testCanInteract() {
        testFurnitureInteraction();
        clearMap();
        testEntityInteraction();
        clearMap();
        testInteraction();
    }

}
