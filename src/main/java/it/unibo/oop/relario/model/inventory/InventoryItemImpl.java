package it.unibo.oop.relario.model.inventory;

/**
 * This class implements the InventoryItem interface and represents an inventory item,
 * providing details about it.
 */

public class InventoryItemImpl implements InventoryItem {

    private final String name;
    private final String description;
    private final EffectType effect;
    private final int intensity;

    /**
     * Constructs an inventory item, with the specified name, description, effect and intensity.
     * @param name of the inventory item
     * @param description of the inventory item
     * @param effect of the inventory item
     * @param intensity of the inventory item
     */
    public InventoryItemImpl(final String name, final String description, final EffectType effect, final int intensity) {
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.intensity = intensity;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final EffectType getEffect() {
        return this.effect;
    }

    @Override
    public final int getIntensity() {
        return this.intensity;
    }

}
