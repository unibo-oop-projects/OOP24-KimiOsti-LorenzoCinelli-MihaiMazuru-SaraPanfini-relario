package it.unibo.oop.relario.model.entities.furniture.impl;

import java.util.Optional;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemImpl;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Implementation of interactive furniture items.
 */
public class InteractiveFurnitureItem extends ObstructingFurnitureItem {

    private Optional<InventoryItem> loot;

    /**
     * Initializes a new empty interactive furniture item.
     * @param pos is the position of the furniture item in the map.
     * @param type is the type of the furniture.
     */
    public InteractiveFurnitureItem(final Position pos, final FurnitureType type) {
        super(pos, type);
        this.loot = Optional.empty();
    }

    @Override
    public final boolean isInteractive() {
        return true;
    }

    /**
     * Initializes a new interactive furniture item.
     * @param pos is the position of the furniture item in the map.
     * @param loot is the loot inside the furniture item.
     * @param type is the furniture type.
     */
    public InteractiveFurnitureItem(final Position pos, final InventoryItem loot, 
    final FurnitureType type) {
        this(pos, type);
        this.loot = Optional.of(loot);
    }

    /**
     * Retrieves the equippable item inside the furniture item.
     * @return an equippable item if there is any inside the furniture item, an optional empty otherwise.
     */
    public InventoryItem dropLoot() {
        final InventoryItem lootCopy = new InventoryItemImpl(this.loot.get().getName(),
        this.loot.get().getDescription(), this.loot.get().getType(), this.loot.get().getIntensity());
        this.loot = Optional.empty();
        return lootCopy;
    }

    /**
     * Says if the furniture item has any loot inside.
     * @return true if the furniture item contains any loot, false otherwise.
     */
    public boolean hasLoot() {
        return !loot.isEmpty();
    } 

    /**
     * Adds a new loot to the furniture item.
     * @param loot is the inventory item to add to the furnite.
     */
    public void addLoot(final InventoryItem loot) {
        this.loot = Optional.of(loot);
    }
}
