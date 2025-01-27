package it.unibo.oop.relario.model.entities.furniture.impl;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.furniture.api.InteractiveFurniture;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Implementation of interactive furniture items.
 */
public final class InteractiveFurnitureImpl extends FurnitureImpl implements InteractiveFurniture {

    private Optional<InventoryItem> loot;
    private boolean hasLoot;

    /**
     * Initializes a new interactive furniture item. It's purely decorative.
     * @param pos the position of the interactive furniture item.
     * @param name the name of the interactive furniture item.
     * @param description the description of the interactive furniture item.
     * @param type the type of the interactive furniture item.
     * @param loot is the loot inside the furniture item.
     */
    public InteractiveFurnitureImpl(final Position pos, final String name, final String description,
    final FurnitureType type, final InventoryItem loot) {
        super(pos, name, description, type);
        this.loot = Optional.of(loot);
        this.hasLoot = true;
    }

    @Override
    public InventoryItem dropLoot() {
        this.hasLoot = false;
        return this.loot.get();
    }

    @Override
    public boolean hasLoot() {
        return this.hasLoot;
    } 

    @Override
    public void addLoot(final InventoryItem loot) {
        this.hasLoot = true;
        this.loot = Optional.of(loot);
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

}
