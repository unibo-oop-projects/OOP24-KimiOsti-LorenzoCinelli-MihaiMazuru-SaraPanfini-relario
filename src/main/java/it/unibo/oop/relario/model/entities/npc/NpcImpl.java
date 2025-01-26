package it.unibo.oop.relario.model.entities.npc;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.LivingBeingImpl;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Implementation for the {@link Npc} interface.
 */
public final class NpcImpl extends LivingBeingImpl implements Npc {

    private final NpcBehavior behavior;
    private boolean hasInteracted;
    private final Optional<InventoryItem> loot;
    private boolean hasLoot;

    /**
     * Instantiates an NPC.
     * @param name the NPC's name.
     * @param position the NPC's position.
     * @param loot the loot that the NPC carries with himself.
     * @param behavior the behavior that the NPC implements.
     */
    public NpcImpl(final String name, final Position position, final Optional<InventoryItem> loot, final NpcBehavior behavior) {
        super(name, position);
        this.behavior = behavior;
        this.loot = loot;
        this.hasInteracted = false;
        this.hasLoot = this.loot.isPresent();
    }

    @Override
    public InteractionOutput interact() {
        this.hasInteracted = true;
        return new InteractionOutput(
            this.behavior.getDialogue(hasLoot),
            hasLoot ? loot : Optional.empty()
        );
    }

    @Override
    public boolean hasInteracted() {
        return this.hasInteracted;
    }

    @Override
    public void confirmLootTaken() {
        this.hasLoot = false;
    }

}
