package it.unibo.oop.relario.model.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryItemFactoryTest {
    
    private InventoryItemFactory factory;

    @BeforeEach
    public void setUp() {
        this.factory = new InventoryItemFactoryImpl();
    }

    @Test
    public void testCreateItem() {
        for (InventoryItemType type : InventoryItemType.values()) {
            InventoryItem item = this.factory.createItem(type);
            assertNotNull(item);
            assertEquals(type, item.getType());
        }
        assertThrows(IllegalArgumentException.class, () -> this.factory.createItem(null));
    }

    @Test
    public void testCreateEquippableItem() {
        for (InventoryItemType type : InventoryItemType.values()) {
            if (type.getEffect().equals(EffectType.DAMAGE) || type.getEffect().equals(EffectType.PROTECTION)) {
                EquippableItem item = this.factory.createEquippableItem(type);
                assertNotNull(item);
                assertEquals(type, item.getType());
            } else {
                assertThrows(IllegalArgumentException.class, () -> this.factory.createEquippableItem(type));
            }
        }
    }

    @Test
    public void testCreateRandomItem() {
        InventoryItem item = this.factory.createRandomItem();
        assertNotNull(item);
        assertTrue(List.of(InventoryItemType.values()).contains(item.getType()));
    }

    @Test
    public void testCreateRandomItemByEffect() {
        for (EffectType effect : EffectType.values()) {
            InventoryItem item = this.factory.createRandomItemByEffect(effect);
            assertNotNull(item);
            assertEquals(effect, item.getEffect());
        }
        assertThrows(IllegalArgumentException.class, () -> this.factory.createRandomItemByEffect(null));
    }

}
