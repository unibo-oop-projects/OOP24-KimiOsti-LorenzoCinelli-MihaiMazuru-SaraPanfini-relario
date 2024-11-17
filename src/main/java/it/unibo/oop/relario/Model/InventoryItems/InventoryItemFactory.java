package it.unibo.oop.relario.Model.InventoryItems;

public interface InventoryItemFactory {

    InventoryItem createItem(InventoryItemType type);
    
    EquippableItem createEquippableItem(InventoryItemType type);

    InventoryItem createRandomItem();

    InventoryItem createRandomItemByEffect(EffectType effect);

}
