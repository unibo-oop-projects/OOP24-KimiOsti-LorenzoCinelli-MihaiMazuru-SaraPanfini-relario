package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.model.map.Room;

/**
 * 
 */

public final class CollectItemObjective implements ObjectiveStrategy {

    private final InventoryItemType keyItemType;

    /**
     * 
     * @param player
     */
    public CollectItemObjective(final GameEntityType keyItemType) {
        if (keyItemType instanceof InventoryItemType) {
            this.keyItemType = (InventoryItemType) keyItemType;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean check(final Room room) {
        return room.getPlayer().getItems().stream().map(i -> i.getType()).anyMatch(t -> t.equals(this.keyItemType));
    }

    @Override
    public Optional<GameEntityType> getKeyEntityType() {
        return Optional.of(this.keyItemType);
    }

}
