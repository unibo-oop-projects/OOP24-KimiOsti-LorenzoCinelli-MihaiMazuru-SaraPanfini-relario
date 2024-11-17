package it.unibo.oop.relario.Model.InventoryItems;

public class CollectableItem extends InventoryItemImpl {

    public CollectableItem(final String name, final String description) {
        super(name, description, EffectType.NONE);
    }
    
}
