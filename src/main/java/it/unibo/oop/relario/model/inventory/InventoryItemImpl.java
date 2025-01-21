package it.unibo.oop.relario.model.inventory;

import java.util.Optional;

import it.unibo.oop.relario.utils.api.Position;

/**
 * This class implements the InventoryItem interface and represents an inventory item,
 * providing details about it.
 */

public class InventoryItemImpl implements InventoryItem {

    private final String name;
    private final String description;
    private final int intensity;
    private final InventoryItemType type;

    /**
     * Constructs an inventory item, with the specified name, description, type and intensity.
     * @param name of the inventory item
     * @param description of the inventory item
     * @param type of the inventory item
     * @param intensity of the inventory item
     */
    public InventoryItemImpl(final String name, final String description, final InventoryItemType type, final int intensity) {
        this.name = name;
        this.description = description;
        this.type = type;
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
        return this.type.getEffect();
    }

    @Override
    public final int getIntensity() {
        return this.intensity;
    }

    @Override
    public final Optional<Position> getPosition() {
        return Optional.empty();
    }

    @Override
    public final InventoryItemType getType() {
        return this.type;
    }

}
