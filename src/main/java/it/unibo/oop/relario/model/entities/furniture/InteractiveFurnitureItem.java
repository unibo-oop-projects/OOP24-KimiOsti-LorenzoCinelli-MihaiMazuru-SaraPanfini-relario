package it.unibo.oop.relario.model.entities.furniture;

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
     * Initialises a new empty interactive furniture item.
     * @param pos is the position of the furniture item in the map.
     * @param name is the name of the furniture item.
     */
    public InteractiveFurnitureItem(final Position pos) {
        super(pos);
        this.loot = Optional.empty();
    }

    /**
     * Retrieves the equippable item inside the furniture item.
     * @return an equippable item if there is any inside the furniture item, an optional empty otherwise.
     */
    public InventoryItem dropLoot() {
        final InventoryItem lootCopy = new InventoryItemImpl(this.loot.get().getName(),
        this.loot.get().getDescription(), this.loot.get().getEffect());
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
     */
    public void addLoot(final InventoryItem loot) {
        this.loot = Optional.of(loot);
    }
}
