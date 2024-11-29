package it.unibo.oop.relario.model.entities.npc;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.LivingBeingImpl;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;

public final class InteractiveNpc extends LivingBeingImpl implements Npc {

    private final DialoguesGenerator dialoguesGenerator;
    private final InventoryItem loot;
    private boolean hasLoot;

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
            this.hasLoot = false;
            return new InteractionOutput(this.dialoguesGenerator.getLootDialogue(), Optional.of(loot));
        } else {
            return new InteractionOutput(this.dialoguesGenerator.getNoLootDialogue(), Optional.empty());
        }
    }

}
