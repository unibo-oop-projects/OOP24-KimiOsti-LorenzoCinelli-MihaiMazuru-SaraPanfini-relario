package it.unibo.oop.relario.Model.InventoryItems;

public abstract class InventoryItemImpl implements InventoryItem {

    private final String name;
    private final String description;
    private final EffectType effect;

    public InventoryItemImpl(final String name, final String description, final EffectType effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public EffectType getEffect() {
        return this.effect;
    }
    
}
