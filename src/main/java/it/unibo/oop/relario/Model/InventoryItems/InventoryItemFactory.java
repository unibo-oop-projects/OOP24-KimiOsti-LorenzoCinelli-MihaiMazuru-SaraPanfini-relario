package it.unibo.oop.relario.Model.InventoryItems;

public interface InventoryItemFactory {
    
    EquippableItem createEquippableItem(InventoryItemType type);

    CollectableItem createCollectableItem(InventoryItemType type);

    InventoryItem createRandomItem();

    InventoryItem createRandomItemByEffect(EffectType effect);

}
