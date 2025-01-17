package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.controller.impl.InventoryControllerImpl;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.InventoryItemFactory;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.model.inventory.InventoryItemImpl;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.utils.impl.Event;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test class for the {@link InventoryControllerImpl} class.
 */
final class InventoryControllerTest {
    
    private InventoryController inventoryController;
    private MainCharacter player;

    /**
     * Sets up the testing.
     */
    @BeforeEach
    void setUp() {
        final var mainController = new MainControllerImpl();
        this.inventoryController = mainController.getInventoryController();

        this.player = mainController.getCurRoom().get().getPlayer();
        final InventoryItemFactory itemFactory = new InventoryItemFactoryImpl();

        final var dagger = itemFactory.createItem(InventoryItemType.DAGGER);
        final var shield = itemFactory.createItem(InventoryItemType.SHIELD);
        assertTrue(this.player.addToInventory(itemFactory.createItem(InventoryItemType.AMULET)));
        assertTrue(this.player.addToInventory(itemFactory.createItem(InventoryItemType.GEMSTONE)));
        assertTrue(this.player.addToInventory(itemFactory.createItem(InventoryItemType.BASICARMOR)));
        assertTrue(this.player.addToInventory(dagger));
        assertTrue(this.player.useItem(dagger));
        assertTrue(this.player.addToInventory(shield));
        assertTrue(this.player.useItem(shield));
    }

    /**
     * Tests items' informations returned by the controller.
     */
    @Test
    void testGetItemsInfo() {
        assertEquals(List.of("Amuleto", "Pietra preziosa", "Armatura semplice"),
        this.inventoryController.getItemsNames());
        assertEquals("""
            Scudo\nUno scudo robusto e affidabile, capace di bloccare colpi potenti,\nEffetto: Protezione 10\nDurabilità: 5""",
        inventoryController.getEquippedArmor());
        assertEquals("""
            Pugnale\nUn'arma leggera e affilata, perfetta per attacchi rapidi e furtivi,\nEffetto: Danno 5\nDurabilità: 3""",
        inventoryController.getEquippedWeapon());

        assertEquals(0, inventoryController.getSelectedItemIndex());
        assertEquals("Un ciondolo antico e luminoso che emana un'aura di guarigione,\nEffetto: Cura 15",
        inventoryController.getItemFullDescription(inventoryController.getSelectedItemIndex()));
        
        inventoryController.notify(Event.NEXT_ITEM);
        assertEquals(1, inventoryController.getSelectedItemIndex());
        assertEquals("Una gemma scintillante di rara bellezza,\nEffetto: Nessuno",
        inventoryController.getItemFullDescription(inventoryController.getSelectedItemIndex()));

        inventoryController.notify(Event.NEXT_ITEM);
        assertEquals(2, inventoryController.getSelectedItemIndex());
        assertEquals("Un'armatura leggera che offre protezione di base,\nEffetto: Protezione 5",
        inventoryController.getItemFullDescription(inventoryController.getSelectedItemIndex()));

        inventoryController.notify(Event.PREVIOUS_ITEM);
        assertEquals(1, inventoryController.getSelectedItemIndex());
        assertEquals("Una gemma scintillante di rara bellezza,\nEffetto: Nessuno",
        inventoryController.getItemFullDescription(inventoryController.getSelectedItemIndex()));

        assertEquals(List.of("Amuleto", "Pietra preziosa", "Armatura semplice"),
        this.inventoryController.getItemsNames());
        assertEquals("""
            Scudo\nUno scudo robusto e affidabile, capace di bloccare colpi potenti,\nEffetto: Protezione 10\nDurabilità: 5""",
        inventoryController.getEquippedArmor());
        assertEquals("""
            Pugnale\nUn'arma leggera e affilata, perfetta per attacchi rapidi e furtivi,\nEffetto: Danno 5\nDurabilità: 3""",
        inventoryController.getEquippedWeapon());
    }

    /**
     * Tests usage of items.
     */
    @Test
    void testUseItem() {

    }

    /**
     * Tests discard of items.
     */
    @Test
    void testDiscardItem() {

    }

    /**
     * Tests the regress to the previous controller and view.
     */
    @Test
    void testRegress() {

    }

}
