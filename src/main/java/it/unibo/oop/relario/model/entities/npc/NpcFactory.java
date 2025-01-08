package it.unibo.oop.relario.model.entities.npc;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Factory for creating NPCs.
 */
public interface NpcFactory {

    /**
     * Creates a random NPC, which can be interactive or non-interactive.
     * @param position of the NPC
     * @return a new random NPC
     */
    Npc createRandomNpc(Position position);

    /**
     * Creates a non-interactive NPC.
     * @param position of the NPC
     * @return a new non-interactive NPC
     */
    Npc createNotInteractiveNpc(Position position);

    /**
     * Creates an interactive NPC, that gives a random loot to the player.
     * @param position of the NPC
     * @return a new interactive NPC
     */
    Npc createInteractiveNpc(Position position);

    /**
     * Creates an interactive NPC, that gives the player the specified loot.
     * @param position of the NPC
     * @param loot the NPC will give to the player
     * @return a new interactive NPC with the specified loot
     */
    Npc createNpcWithLoot(Position position, InventoryItem loot);

    Npc createQuestNpc(Position position, String dialogue);

}
