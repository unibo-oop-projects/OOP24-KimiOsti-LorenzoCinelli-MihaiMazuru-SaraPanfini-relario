package it.unibo.oop.relario.model.entities.npc;

import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Factory for creating NPCs.
 */
public interface NpcFactory {

    /**
     * Creates a random NPC, which can be interactive or non-interactive.
     * @param position of the NPC.
     * @return a new random NPC.
     */
    Npc createRandomNpc(Position position);

    /**
     * Creates a default NPC, which is non-interactive.
     * @param position of the NPC.
     * @return a new default NPC.
     */
    Npc createDefaultNpc(Position position);

    /**
     * Creates an interactive NPC, that gives a random loot to the player.
     * @param position of the NPC.
     * @return a new interactive NPC.
     */
    Npc createInteractiveNpc(Position position);

    /**
     * Creates an interactive NPC, that gives the player the specified loot.
     * @param position of the NPC.
     * @param itemType the loot's type of the item.
     * @return a new interactive NPC with the specified loot.
     */
    Npc createNpcWithLoot(Position position, InventoryItemType itemType);

    /**
     * Creates a quest NPC, that gives the player the specified quest.
     * @param position of the NPC.
     * @param questDescription the quest's description.
     * @return a new quest NPC with specified quest.
     */
    Npc createQuestNpc(Position position, String questDescription);

}
