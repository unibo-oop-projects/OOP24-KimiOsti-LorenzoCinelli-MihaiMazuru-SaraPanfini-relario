package it.unibo.oop.relario.model.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test class for the {@link InventoryItems} class.
 */
final class InventoryItemsTest {

    private final List<InventoryItem> list = new ArrayList<>();
    private final List<String> desc = new ArrayList<>();

    /**
     * Sets up the testing.
     */
    @BeforeEach
    void setUp() {
        final InventoryItemFactory itemFactory = new InventoryItemFactoryImpl();
        list.add(itemFactory.createItem(InventoryItemType.AMULET));
        desc.add("Un ciondolo antico e luminoso che emana un'aura di guarigione\nEffetto: Cura 15");

        list.add(itemFactory.createItem(InventoryItemType.APPLE));
        desc.add("Una mela fresca e succosa, perfetta per recuperare un po' di energia\nEffetto: Cura 3");

        list.add(itemFactory.createItem(InventoryItemType.BASICARMOR));
        desc.add("Un'armatura leggera che offre protezione di base\nEffetto: Protezione 5\nDurabilità: 3");

        list.add(itemFactory.createItem(InventoryItemType.BOW));
        desc.add("Arco leggero e preciso, ideale per attacchi a lunga distanza\nEffetto: Danno 8\nDurabilità: 5");

        list.add(itemFactory.createItem(InventoryItemType.COIN));
        desc.add("Una moneta luccicante\nEffetto: Nessuno");

        list.add(itemFactory.createItem(InventoryItemType.DAGGER));
        desc.add("Un'arma leggera e affilata, perfetta per attacchi rapidi e furtivi\nEffetto: Danno 5\nDurabilità: 3");

        list.add(itemFactory.createItem(InventoryItemType.GEMSTONE));
        desc.add("Una gemma scintillante di rara bellezza\nEffetto: Nessuno");

        list.add(itemFactory.createItem(InventoryItemType.HAMMER));
        desc.add("Un'arma pesante e devastante, progettata per infliggere danni enormi\nEffetto: Danno 15\nDurabilità: 8");

        list.add(itemFactory.createItem(InventoryItemType.KEY));
        desc.add("Chiave antica per aprire un passaggio\nEffetto: Risolve una quest");

        list.add(itemFactory.createItem(InventoryItemType.POTION));
        desc.add("Un liquido rosso che ripristina rapidamente la salute\nEffetto: Cura 10");

        list.add(itemFactory.createItem(InventoryItemType.SHIELD));
        desc.add("Uno scudo robusto e affidabile, capace di bloccare colpi potenti\nEffetto: Protezione 10\nDurabilità: 5");

        list.add(itemFactory.createItem(InventoryItemType.SWORD));
        desc.add("Spada affilata, perfetta per attacchi rapidi e precisi\nEffetto: Danno 10\nDurabilità: 10");
    }

    /**
     * Tests the full description of items.
     */
    @Test
    void testGetFullDescription() {
        for (int i = 0; i < list.size(); i++) {
            assertEquals(desc.get(i), InventoryItems.getFullDescription(list.get(i)));
        }
    }

    /**
     * Tests the description of equipped items.
     */
    @Test
    void testGetEquippedItem() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof EquippableItem) {
                final String descrizione = list.get(i).getName() + "\n" + desc.get(i);
                assertEquals(descrizione, InventoryItems.getEquippedItem(Optional.of((EquippableItem) list.get(i))));
            }
        }
    }

}
