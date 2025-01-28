package it.unibo.oop.relario.model.entities.furniture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureFactory;
import it.unibo.oop.relario.model.entities.furniture.api.InteractiveFurniture;
import it.unibo.oop.relario.model.entities.furniture.api.WalkableFurniture;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureFactoryImpl;
import it.unibo.oop.relario.model.entities.furniture.impl.FurniturePropriety;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureType;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Test class for the furniture factory class.
 */
class FurnitureFactoryTest {

    private FurnitureFactory factory;
    private Position pos;

    /**
     * Sets up the generic position and the factory.
     */
    @BeforeEach
    void setUp() {
        this.pos = new PositionImpl(0, 0);
        this.factory = new FurnitureFactoryImpl();
    }

    /**
     * Tests the methods that create a furniture item by a given type.
     */
    @Test
    void testCreationByItem() {
        Furniture furniture;
        for (final var type: FurnitureType.values()) {
            if (type.getFurniturePropriety().equals(FurniturePropriety.WALKABLE)) {
                furniture = factory.createWalkableFurnitureByItem(pos, type);
                assertNotNull(furniture);
            } else {
                assertThrows(
                    IllegalArgumentException.class, 
                    () -> this.factory.createWalkableFurnitureByItem(pos, type)
                );
            }
        }

        for (final var type: FurnitureType.values()) {
            if (type.getFurniturePropriety().equals(FurniturePropriety.OBSTRUCTING)) {
                furniture = factory.createObstructingFurnitureByItem(pos, type);
                assertNotNull(furniture);
            } else {
                assertThrows(
                    IllegalArgumentException.class, 
                    () -> this.factory.createObstructingFurnitureByItem(pos, type)
                );
            }
        }

        for (final var type: FurnitureType.values()) {
            if (type.getFurniturePropriety().equals(FurniturePropriety.INTERACTIVE)) {
                furniture = factory.createInteractiveFurnitureByItem(pos, type);
                assertNotNull(furniture);
            } else {
                assertThrows(
                    IllegalArgumentException.class, 
                    () -> this.factory.createInteractiveFurnitureByItem(pos, type)
                );
            }
        }
    }

    /**
     * Tests the methods that create a random furniture item.
     */
    @Test
    void testCreationRandom() {
        var furniture = this.factory.createRandomInteractiveFurniture(pos);
        assertNotNull(furniture);
        assertEquals(furniture.getType().getFurniturePropriety(), FurniturePropriety.INTERACTIVE);

        furniture = this.factory.createRandomObstructingFurniture(pos);
        assertNotNull(furniture);
        assertEquals(furniture.getType().getFurniturePropriety(), FurniturePropriety.OBSTRUCTING);

        WalkableFurniture furn = (WalkableFurniture) this.factory.createRandomWalkableFurniture(pos);
        assertNotNull(furn);
        assertTrue(furn.hasEnemy());
        assertEquals(furn.getType().getFurniturePropriety(), FurniturePropriety.WALKABLE);

        furn = (WalkableFurniture) this.factory.createRandomWalkableFurnitureEmpty(pos);
        assertNotNull(furn);
        assertFalse(furn.hasEnemy());
        assertEquals(furn.getType().getFurniturePropriety(), FurniturePropriety.WALKABLE);
    }

    /**
     * Tests the methods that create a furniture item 
     * with a specific enemy or item inside.
     */
    @Test
    void testCreationSpecific() {
        final Enemy enemy = new EnemyFactoryImpl().createRandomEnemy(pos);
        final WalkableFurniture furn = (WalkableFurniture) 
            this.factory.createWalkableFurnitureEnemy(pos, enemy);
        furn.removeEnemy();
        assertFalse(furn.hasEnemy());

        final InteractiveFurniture furniture = (InteractiveFurniture) 
            this.factory.createInteractiveFurnitureLoot(pos, InventoryItemType.APPLE);
        assertEquals(furniture.dropLoot().getType(), InventoryItemType.APPLE);
    }
}
