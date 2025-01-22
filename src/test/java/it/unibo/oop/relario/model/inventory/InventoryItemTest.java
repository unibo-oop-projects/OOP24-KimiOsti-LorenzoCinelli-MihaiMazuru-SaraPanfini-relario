package it.unibo.oop.relario.model.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * CHECKSTYLE: MagicNumber OFF
 * Used to avoid CheckStyle violations for magic numbers, here used for test scenarios. 
 */

 /**
  * The test class for inventory items.
  */

final class InventoryItemTest {

    private final List<InventoryItemImpl> inventoryItems = new LinkedList<>();

    /**
     * Sets up a collection of test items.
     */
    @BeforeEach
    void setUp() {
        this.inventoryItems.add(new InventoryItemImpl("Mela", "Cibo curativo", InventoryItemType.APPLE, 1));
        this.inventoryItems.add(new InventoryItemImpl("Moneta", "Moneta antica", InventoryItemType.COIN, 0));
        this.inventoryItems.add(new EquippableItem("Spada", "Arma affilata", InventoryItemType.SWORD, 5, 3));
        this.inventoryItems.add(new EquippableItem("Armatura", "Armatura di ferro", InventoryItemType.BASICARMOR, 4, 2));
    }

    /**
     * Tests the getter methods of the inventory items.
     */
    @Test
    void testGetters() {
        InventoryItem inventoryItem = this.inventoryItems.get(0);
        assertEquals("Mela", inventoryItem.getName());
        assertEquals("Cibo curativo", inventoryItem.getDescription());
        assertEquals(InventoryItemType.APPLE, inventoryItem.getType());
        assertEquals(1, inventoryItem.getIntensity());
        assertEquals(EffectType.HEALING, inventoryItem.getEffect());

        inventoryItem = this.inventoryItems.get(1);
        assertEquals("Moneta", inventoryItem.getName());
        assertEquals("Moneta antica", inventoryItem.getDescription());
        assertEquals(InventoryItemType.COIN, inventoryItem.getType());
        assertEquals(0, inventoryItem.getIntensity());
        assertEquals(EffectType.NONE, inventoryItem.getEffect());

        EquippableItem equippableItem = (EquippableItem) this.inventoryItems.get(2);
        assertEquals("Spada", equippableItem.getName());
        assertEquals("Arma affilata", equippableItem.getDescription());
        assertEquals(InventoryItemType.SWORD, equippableItem.getType());
        assertEquals(5, equippableItem.getIntensity());
        assertEquals(3, equippableItem.getDurability());
        assertEquals(EffectType.DAMAGE, equippableItem.getEffect());

        equippableItem = (EquippableItem) this.inventoryItems.get(3);
        assertEquals("Armatura", equippableItem.getName());
        assertEquals("Armatura di ferro", equippableItem.getDescription());
        assertEquals(InventoryItemType.BASICARMOR, equippableItem.getType());
        assertEquals(4, equippableItem.getIntensity());
        assertEquals(2, equippableItem.getDurability());
        assertEquals(EffectType.PROTECTION, equippableItem.getEffect());
    }

}
