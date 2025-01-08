package it.unibo.oop.relario.model.entities.furniture.impl;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.furniture.api.InteractiveFurniture;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemImpl;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Implementation of interactive furniture items.
 */
public class InteractiveFurnitureImpl extends FurnitureImpl implements InteractiveFurniture {

    private Optional<InventoryItem> loot;

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
    }

    @Override
    public final boolean isInteractive() {
        return true;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    public final InventoryItem dropLoot() {
        final InventoryItem lootCopy = new InventoryItemImpl(this.loot.get().getName(),
        this.loot.get().getDescription(), this.loot.get().getType(), this.loot.get().getIntensity());
        this.loot = Optional.empty();
        return lootCopy;
    }

    public final boolean hasLoot() {
        return !loot.isEmpty();
    } 

    public final void addLoot(final InventoryItem loot) {
        this.loot = Optional.of(loot);
    }

}
