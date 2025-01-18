package it.unibo.oop.relario.model.inventory;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Implementation of the item factory. 
 * This class provides the logic to create different types of inventory items.
 * It has a Random object to generate random items and a map that associates each inventory item type
 * with its corresponding creation logic.
 */

public final class InventoryItemFactoryImpl implements InventoryItemFactory {

    private final Random random = new Random();
    private final Map<InventoryItemType, Supplier<InventoryItem>> itemCreator = new EnumMap<>(InventoryItemType.class);

    /**
     * Constructor for inventory item factory, 
     * that populates the map with inventory items types associated with a supplier of the corresponding inventory item.
     */
    public InventoryItemFactoryImpl() {
        this.itemCreator.put(InventoryItemType.SWORD, 
        () -> new EquippableItem("Spada", "Spada affilata, perfetta per attacchi rapidi e precisi", 
        InventoryItemType.SWORD, ItemAttributes.SWORD_INTENSITY, ItemAttributes.SWORD_DURABILITY));
        this.itemCreator.put(InventoryItemType.BOW, 
        () -> new EquippableItem("Arco", "Arco leggero e preciso, ideale per attacchi a lunga distanza", 
        InventoryItemType.BOW, ItemAttributes.BOW_INTENSITY, ItemAttributes.BOW_DURABILITY));
        this.itemCreator.put(InventoryItemType.DAGGER, 
        () -> new EquippableItem("Pugnale", "Un'arma leggera e affilata, perfetta per attacchi rapidi e furtivi", 
        InventoryItemType.DAGGER, ItemAttributes.DAGGER_INTENSITY, ItemAttributes.DAGGER_DURABILITY));
        this.itemCreator.put(InventoryItemType.HAMMER, 
        () -> new EquippableItem("Martello", "Un'arma pesante e devastante, progettata per infliggere danni enormi", 
        InventoryItemType.HAMMER, ItemAttributes.HAMMER_INTENSITY, ItemAttributes.HAMMER_DURABILITY));
        this.itemCreator.put(InventoryItemType.SHIELD, 
        () -> new EquippableItem("Scudo", "Uno scudo robusto e affidabile, capace di bloccare colpi potenti", 
        InventoryItemType.SHIELD, ItemAttributes.SHIELD_INTENSITY, ItemAttributes.SHIELD_DURABILITY));
        this.itemCreator.put(InventoryItemType.BASICARMOR, 
        () -> new EquippableItem("Armatura semplice", "Un'armatura leggera che offre protezione di base", 
        InventoryItemType.BASICARMOR, ItemAttributes.BASICARMOR_INTENSITY, ItemAttributes.BASICARMOR_DURABILITY));
        this.itemCreator.put(InventoryItemType.POTION, 
        () -> new InventoryItemImpl("Pozione", "Un liquido rosso che ripristina rapidamente la salute", 
        InventoryItemType.POTION, ItemAttributes.POTION_INTENSITY));
        this.itemCreator.put(InventoryItemType.APPLE, 
        () -> new InventoryItemImpl("Mela", "Una mela fresca e succosa, perfetta per recuperare un po' di energia", 
        InventoryItemType.APPLE, ItemAttributes.APPLE_INTENSITY));
        this.itemCreator.put(InventoryItemType.AMULET, 
        () -> new InventoryItemImpl("Amuleto", "Un ciondolo antico e luminoso che emana un'aura di guarigione", 
        InventoryItemType.AMULET, ItemAttributes.AMULET_INTENSITY));
        this.itemCreator.put(InventoryItemType.COIN, 
        () -> new InventoryItemImpl("Moneta", "Una moneta luccicante", 
        InventoryItemType.COIN, ItemAttributes.COIN_INTENSITY));
        this.itemCreator.put(InventoryItemType.GEMSTONE, 
        () -> new InventoryItemImpl("Pietra preziosa", "Una gemma scintillante di rara bellezza", 
        InventoryItemType.GEMSTONE, ItemAttributes.GEMSTONE_INTENSITY));
        this.itemCreator.put(InventoryItemType.KEY,
        () -> new InventoryItemImpl("Chiave", "Chiave antica per aprire un passaggio", 
        InventoryItemType.KEY, ItemAttributes.KEY_INTENSITY));
    }

    @Override
    public InventoryItem createItem(final InventoryItemType type) {
        return itemCreator.get(type).get();
    }

    @Override
    public EquippableItem createEquippableItem(final InventoryItemType type) {
        final InventoryItem item = createItem(type);
        if (!(item instanceof EquippableItem)) {
            throw new IllegalArgumentException();
        }
        return (EquippableItem) item;
    }

    @Override
    public InventoryItem createRandomItem() {
        return itemCreator.get(InventoryItemType.values()[random.nextInt(InventoryItemType.values().length)]).get();
    }

    @Override
    public InventoryItem createRandomItemByEffect(final EffectType effect) {
        final List<InventoryItemType> matchingTypes = itemCreator.keySet().stream()
            .filter(type -> type.getEffect().equals(effect)).toList();

        return itemCreator.get(matchingTypes.get(random.nextInt(matchingTypes.size()))).get();
    }

}
