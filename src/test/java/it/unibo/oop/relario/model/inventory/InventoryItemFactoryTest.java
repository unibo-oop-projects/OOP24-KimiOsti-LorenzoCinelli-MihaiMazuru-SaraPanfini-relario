package it.unibo.oop.relario.model.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class for the inventory item factory.
 */

final class InventoryItemFactoryTest {

    private InventoryItemFactory factory;

    /**
     * Sets up the factory before each test execution.
     */
    @BeforeEach
    void setUp() {
        this.factory = new InventoryItemFactoryImpl();
    }

    /**
     * Tests that an inventory item of the given type is created 
     * and that an exception is thrown if the given type doesn't exist.
     */
    @Test
    void testCreateItem() {
        for (final InventoryItemType type : InventoryItemType.values()) {
            final InventoryItem item = this.factory.createItem(type);
            assertNotNull(item);
            assertEquals(type, item.getType());
        }
        assertThrows(IllegalArgumentException.class, () -> this.factory.createItem(null));
    }

    /**
     * Tests that an equippable item of the given type is created 
     * and that an exception is thrown for non-equippable item types.
     */
    @Test
    void testCreateEquippableItem() {
        for (final InventoryItemType type : InventoryItemType.values()) {
            if (type.getEffect().equals(EffectType.DAMAGE) || type.getEffect().equals(EffectType.PROTECTION)) {
                final EquippableItem item = this.factory.createEquippableItem(type);
                assertNotNull(item);
                assertEquals(type, item.getType());
            } else {
                assertThrows(IllegalArgumentException.class, () -> this.factory.createEquippableItem(type));
            }
        }
    }

    /**
     * Tests that a random valid inventory item is created.
     */
    @Test
    void testCreateRandomItem() {
        final InventoryItem item = this.factory.createRandomItem();
        assertNotNull(item);
        assertTrue(List.of(InventoryItemType.values()).contains(item.getType()));
    }

    /**
     * Tests that a random inventory item with the specified effect is created
     * and that an exception is thrown if the effect doesn't exist.
     */
    @Test
    void testCreateRandomItemByEffect() {
        for (final EffectType effect : EffectType.values()) {
            final InventoryItem item = this.factory.createRandomItemByEffect(effect);
            assertNotNull(item);
            assertEquals(effect, item.getEffect());
        }
        assertThrows(IllegalArgumentException.class, () -> this.factory.createRandomItemByEffect(null));
    }

}
