package it.unibo.oop.relario.model.entities.furniture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.model.entities.furniture.api.WalkableFurniture;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.enemies.EnemyImpl;
import it.unibo.oop.relario.model.entities.furniture.api.InteractiveFurniture;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureType;
import it.unibo.oop.relario.model.entities.furniture.impl.InteractiveFurnitureImpl;
import it.unibo.oop.relario.model.entities.furniture.impl.ObstructingFurniture;
import it.unibo.oop.relario.model.entities.furniture.impl.WalkableFurnitureImpl;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Test class for the different kinds of furniture.
 */
class FurnitureTest {

    private static final String NAME = "furniture";
    private static final String DESC = "descrizione della furniture";

    private Position pos;

    /**
     * Sets up the generic position.
     */
    @BeforeEach
    void setUp() {
        pos = new PositionImpl(0, 0);
    }

    /**
     * Tests getter methos for a walkable furniture item.
     */
    @Test
    void testWalkableFurniture() {
        final Enemy enem = new EnemyFactoryImpl().createRandomEnemy(pos);
        final WalkableFurniture furn = new WalkableFurnitureImpl(pos, NAME, DESC, 
            FurnitureType.CARPET);
        final WalkableFurniture furnEnemy = new WalkableFurnitureImpl(pos, NAME, DESC, 
            FurnitureType.TRAPDOOR, enem);

        assertEquals(furn.getDescription(), DESC);
        assertEquals(furn.getName(), NAME);
        assertNotNull(furn.getPosition().get());
        assertEquals(furn.getPosition().get().getX(), 0);
        assertEquals(furn.getPosition().get().getY(), 0);
        assertNotEquals(furn.getPosition(), pos);
        assertEquals(furn.getType(), FurnitureType.CARPET);
        assertFalse(furn.hasEnemy());
        assertTrue(furn.isInteractive());
        assertTrue(furn.isWalkable());

        assertTrue(furnEnemy.hasEnemy());
        assertEquals(furnEnemy.removeEnemy().getClass(), EnemyImpl.class);
        assertFalse(furnEnemy.hasEnemy());
    }

    /**
     * Tests getter methods for an interactive furniture item.
     */
    @Test 
    void testInteractiveFurniture() {
        final InventoryItem item = new InventoryItemFactoryImpl().createRandomItem();
        final InteractiveFurniture furn = new InteractiveFurnitureImpl(pos, NAME, DESC, 
            FurnitureType.CHEST, item);

        assertEquals(furn.getDescription(), DESC);
        assertEquals(furn.getName(), NAME);
        assertNotNull(furn.getPosition().get());
        assertEquals(furn.getPosition().get().getX(), 0);
        assertEquals(furn.getPosition().get().getY(), 0);
        assertNotEquals(furn.getPosition(), pos);
        assertEquals(furn.getType(), FurnitureType.CHEST);
        assertTrue(furn.hasLoot());
        furn.dropLoot();
        //assertNotEquals(furn.dropLoot(), item);
        assertFalse(furn.hasLoot());
        furn.addLoot(item);
        assertTrue(furn.hasLoot());
        assertTrue(furn.isInteractive());
        assertFalse(furn.isWalkable());
    }

    /**
     * Tests getter methods for an obstructing furniture item.
     */
    @Test
    void testObstructingFurniture() {
        final ObstructingFurniture furn = new ObstructingFurniture(pos, NAME, DESC, 
            FurnitureType.STATUE);

        assertEquals(furn.getDescription(), DESC);
        assertEquals(furn.getName(), NAME);
        assertNotNull(furn.getPosition().get());
        assertEquals(furn.getPosition().get().getX(), 0);
        assertEquals(furn.getPosition().get().getY(), 0);
        assertNotEquals(furn.getPosition(), pos);
        assertEquals(furn.getType(), FurnitureType.STATUE);
        assertFalse(furn.isInteractive());
        assertFalse(furn.isWalkable());
    }
}
