package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.controller.impl.InventoryControllerImpl;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.model.inventory.InventoryItemFactory;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.utils.impl.Event;
import it.unibo.oop.relario.utils.impl.GameState;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test class for the {@link InventoryControllerImpl} class.
 */
final class InventoryControllerTest {
    private static final String AMULETO = "Amuleto";
    private static final String PIETRA = "Pietra preziosa";
    private static final String ARMATURA = "Armatura semplice";
    private static final String SCUDO = "Scudo";
    private static final Map<InventoryItemType, String> FULL_DESCRIPTION = Map.of(
        InventoryItemType.AMULET,
        "Un ciondolo antico e luminoso che emana un'aura di guarigione\nEffetto: Cura 15",
        InventoryItemType.GEMSTONE,
        "Una gemma scintillante di rara bellezza\nEffetto: Nessuno",
        InventoryItemType.BASICARMOR,
        "Un'armatura leggera che offre protezione di base\nEffetto: Protezione 5\nDurabilita': 3",
        InventoryItemType.DAGGER,
        "Un'arma leggera e affilata, perfetta per attacchi rapidi e furtivi\nEffetto: Danno 5\nDurabilita': 3",
        InventoryItemType.SHIELD,
        "Uno scudo robusto e affidabile, capace di bloccare colpi potenti\nEffetto: Protezione 10\nDurabilita': 5"
    );

    private InventoryController inventoryController;

    /**
     * Sets up the testing.
     */
    @BeforeEach
    void setUp() {
        final var mainController = new MainControllerImpl();
        this.inventoryController = mainController.getInventoryController();
        mainController.moveToNextRoom();

        mainController.getInventoryController().init(GameState.MENU);

        final var player = mainController.getCurRoom().get().getPlayer();
        final InventoryItemFactory itemFactory = new InventoryItemFactoryImpl();
        player.attacked(30);

        final var dagger = itemFactory.createItem(InventoryItemType.DAGGER);
        final var shield = itemFactory.createItem(InventoryItemType.SHIELD);
        assertTrue(player.addToInventory(itemFactory.createItem(InventoryItemType.AMULET)));
        assertTrue(player.addToInventory(itemFactory.createItem(InventoryItemType.GEMSTONE)));
        assertTrue(player.addToInventory(itemFactory.createItem(InventoryItemType.BASICARMOR)));
        assertTrue(player.addToInventory(dagger));
        assertTrue(player.useItem(dagger));
        assertTrue(player.addToInventory(shield));
        assertTrue(player.useItem(shield));
    }

    /**
     * Tests items' informations returned by the controller.
     */
    @Test
    void testGetItemsInfo() {
        assertEquals("50", inventoryController.getLife());

        assertEquals(List.of(AMULETO, PIETRA, ARMATURA), this.inventoryController.getItemsNames());
        assertEquals("Scudo\n" + FULL_DESCRIPTION.get(InventoryItemType.SHIELD), inventoryController.getEquippedArmor());
        assertEquals("Pugnale\n" + FULL_DESCRIPTION.get(InventoryItemType.DAGGER), inventoryController.getEquippedWeapon());

        assertEquals(0, inventoryController.getSelectedItemIndex());
        assertEquals(FULL_DESCRIPTION.get(InventoryItemType.AMULET), inventoryController.getItemFullDescription());

        inventoryController.notify(Event.NEXT_ITEM);
        assertEquals(1, inventoryController.getSelectedItemIndex());
        assertEquals(FULL_DESCRIPTION.get(InventoryItemType.GEMSTONE), inventoryController.getItemFullDescription());

        inventoryController.notify(Event.NEXT_ITEM);
        assertEquals(2, inventoryController.getSelectedItemIndex());
        assertEquals(FULL_DESCRIPTION.get(InventoryItemType.BASICARMOR), inventoryController.getItemFullDescription());

        inventoryController.notify(Event.PREVIOUS_ITEM);
        assertEquals(1, inventoryController.getSelectedItemIndex());
        assertEquals(FULL_DESCRIPTION.get(InventoryItemType.GEMSTONE), inventoryController.getItemFullDescription());

        assertEquals(List.of(AMULETO, PIETRA, ARMATURA),
        this.inventoryController.getItemsNames());
        assertEquals("Scudo\n" + FULL_DESCRIPTION.get(InventoryItemType.SHIELD), inventoryController.getEquippedArmor());
        assertEquals("Pugnale\n" + FULL_DESCRIPTION.get(InventoryItemType.DAGGER), inventoryController.getEquippedWeapon());

        assertEquals("50", inventoryController.getLife());
    }

    /**
     * Tests usage of items.
     */
    @Test
    void testUseItem() {
        assertEquals("50", inventoryController.getLife());

        assertEquals(List.of(AMULETO, PIETRA, ARMATURA),
        this.inventoryController.getItemsNames());
        assertEquals(0, this.inventoryController.getSelectedItemIndex());
        this.inventoryController.notify(Event.NEXT_ITEM);
        this.inventoryController.notify(Event.NEXT_ITEM);
        assertEquals(2, this.inventoryController.getSelectedItemIndex());

        this.inventoryController.notify(Event.USE_ITEM);
        assertEquals(List.of(AMULETO, PIETRA, SCUDO),
        this.inventoryController.getItemsNames());
        assertEquals(2, this.inventoryController.getSelectedItemIndex());

        this.inventoryController.notify(Event.NEXT_ITEM);
        assertEquals(0, this.inventoryController.getSelectedItemIndex());
        this.inventoryController.notify(Event.USE_ITEM);
        assertEquals(List.of(PIETRA, SCUDO),
        this.inventoryController.getItemsNames());
        assertEquals("65", inventoryController.getLife());

        this.inventoryController.notify(Event.NEXT_ITEM);
        assertEquals(1, this.inventoryController.getSelectedItemIndex());
        this.inventoryController.notify(Event.USE_ITEM);
        assertEquals(List.of(PIETRA, ARMATURA),
        this.inventoryController.getItemsNames());
        assertEquals(1, this.inventoryController.getSelectedItemIndex());

        this.inventoryController.notify(Event.NEXT_ITEM);
        assertEquals(0, this.inventoryController.getSelectedItemIndex());
        this.inventoryController.notify(Event.USE_ITEM);
        assertEquals(List.of(ARMATURA),
        this.inventoryController.getItemsNames());
        assertEquals(0, this.inventoryController.getSelectedItemIndex());

        assertEquals("65", inventoryController.getLife());

    }

    /**
     * Tests discard of items.
     */
    @Test
    void testDiscardItem() {
        assertEquals("50", inventoryController.getLife());

        assertEquals(List.of(AMULETO, PIETRA, ARMATURA),
        this.inventoryController.getItemsNames());
        assertEquals(0, this.inventoryController.getSelectedItemIndex());
        this.inventoryController.notify(Event.NEXT_ITEM);
        this.inventoryController.notify(Event.NEXT_ITEM);
        assertEquals(2, this.inventoryController.getSelectedItemIndex());

        this.inventoryController.notify(Event.DISCARD_ITEM);
        assertEquals(List.of(AMULETO, PIETRA),
        this.inventoryController.getItemsNames());
        assertEquals(0, this.inventoryController.getSelectedItemIndex());

        this.inventoryController.notify(Event.PREVIOUS_ITEM);
        assertEquals(1, this.inventoryController.getSelectedItemIndex());
        this.inventoryController.notify(Event.DISCARD_ITEM);
        assertEquals(List.of(AMULETO),
        this.inventoryController.getItemsNames());

        assertEquals(0, this.inventoryController.getSelectedItemIndex());
        this.inventoryController.notify(Event.DISCARD_ITEM);
        assertEquals(List.of(),
        this.inventoryController.getItemsNames());

        assertEquals("50", inventoryController.getLife());
    }

}
