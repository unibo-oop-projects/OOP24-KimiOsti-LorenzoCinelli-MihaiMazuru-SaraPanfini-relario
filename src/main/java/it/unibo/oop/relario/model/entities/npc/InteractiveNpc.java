package it.unibo.oop.relario.model.entities.npc;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.LivingBeingImpl;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Class representing an interactive NPC.
 * An interactive NPC can interact with the player and give him a loot.
 */
public final class InteractiveNpc extends LivingBeingImpl implements Npc {

    private final DialoguesGenerator dialoguesGenerator;
    private final InventoryItem loot;
    private boolean hasLoot;

    /**
     * Constructs a new interactive NPC.
     * @param name of the NPC
     * @param position of the NPC
     * @param loot that the NPC gives to the player
     * @param dialoguesGenerator used to generate a random dialogue for the NPC
     */
    public InteractiveNpc(final String name, final Position position, final InventoryItem loot, 
    final DialoguesGenerator dialoguesGenerator) {
        super(name, position);
        this.loot = loot;
        this.hasLoot = true;
        this.dialoguesGenerator = dialoguesGenerator;
    }

    @Override
    public InteractionOutput interact() {
        if (hasLoot) {
            return new InteractionOutput(this.dialoguesGenerator.getLootDialogue(), Optional.of(loot));
        } else {
            return new InteractionOutput(this.dialoguesGenerator.getNoLootDialogue(), Optional.empty());
        }
    }

    public void confirmLootTaken() {
        this.hasLoot = false;
    }

}
