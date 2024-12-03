package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.map.Room;

public final class CollectItemObjective implements ObjectiveStrategy {

    private final MainCharacter player;
    private final InventoryItem item; // ??

    public CollectItemObjective(final Room room, final InventoryItem item) {
        this.player = room.getPlayer();
        this.item = item;
    }

    @Override
    public boolean check() {
        return this.player.getItems().contains(item);
    }

}
