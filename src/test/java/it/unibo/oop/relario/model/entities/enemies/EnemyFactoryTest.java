package it.unibo.oop.relario.model.entities.enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

final class EnemyFactoryTest {

    private EnemyFactory factory;
    private Position testPosition;

    @BeforeEach
    void setUp() {
        this.factory = new EnemyFactoryImpl();
        this.testPosition = new PositionImpl(0, 0);
    }

    @Test
    void testCreateRandomEnemy() {
        final Enemy enemy = this.factory.createRandomEnemy(testPosition);
        assertNotNull(enemy);
        assertTrue(List.of(EnemyType.values()).contains(enemy.getType()));
    }

    @Test
    void testCreateEnemyWithReward() {
        final Enemy enemy = this.factory.createEnemyWithReward(testPosition, InventoryItemType.APPLE);
        assertNotNull(enemy);
        assertEquals(InventoryItemType.APPLE, enemy.getReward().getType());
    }

    @Test
    void testCreateEnemyByType() {
        for (final EnemyType type : EnemyType.values()) {
            final Enemy enemy = this.factory.createEnemyByType(type, testPosition);
            assertNotNull(enemy);
            assertEquals(type, enemy.getType());
        }
        assertThrows(IllegalArgumentException.class, () -> this.factory.createEnemyByType(null, testPosition));
    }
}
