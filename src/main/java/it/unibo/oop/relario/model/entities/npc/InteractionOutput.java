package it.unibo.oop.relario.model.entities.npc;

import java.util.Optional;

import it.unibo.oop.relario.model.inventory.InventoryItem;

/**
 * This class represents the outcome of an interaction with NPC.
 */
public final class InteractionOutput {
    private final String dialogue;
    private final Optional<InventoryItem> loot;

    /**
     * Constructs a new InteractionOutput instance.
     * @param dialogue that the NPC has to say
     * @param loot that the NPC should give to the player, if present
     */
    public InteractionOutput(final String dialogue, final Optional<InventoryItem> loot) {
        this.dialogue = dialogue;
        this.loot = loot;
    }

    /**
     * Retrieves the dialogue that the NPC will say.
     * @return the dialogue of the NPC
     */
    public String getDialogue() {
        return this.dialogue;
    }

    /**
     * Retrieves the inventory item that the NPC will give to the player.
     * @return an optional containing the loot, or an empty optional if the NPC provides no item
     */
    public Optional<InventoryItem> getLoot() {
        return this.loot;
    }

}
