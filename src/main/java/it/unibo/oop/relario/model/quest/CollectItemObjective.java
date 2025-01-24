package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.model.map.Room;

/**
 * Implementation for an item collection quest's objective.
 */
public final class CollectItemObjective implements ObjectiveStrategy {

    private final Optional<InventoryItemType> keyItemType;

    /**
     * Instantiates an item collection objective.
     * @param keyItemType the type of the item to be collected.
     */
    public CollectItemObjective(final Optional<GameEntityType> keyItemType) {
        if (keyItemType.isPresent() && keyItemType.get() instanceof InventoryItemType) {
            this.keyItemType = Optional.of((InventoryItemType) keyItemType.get());
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean check(final Room room) {
        return room
        .getPlayer()
        .getItems()
        .stream()
        .map(InventoryItem::getType)
        .anyMatch(t -> t.equals(this.keyItemType.get()));
    }

    @Override
    public Optional<InventoryItemType> getKeyEntityType() {
        return this.keyItemType;
    }

}
