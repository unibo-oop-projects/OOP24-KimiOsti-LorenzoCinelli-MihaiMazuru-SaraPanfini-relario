package it.unibo.oop.relario.model.entities.npc;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;

public interface NpcFactory {
    
    Npc createRandomNpc(Position position);

    Npc createNotInteractiveNpc(Position position);

    Npc createInteractiveNpc(Position position);

    Npc createNpcWithLoot(Position position, InventoryItem loot);

}
