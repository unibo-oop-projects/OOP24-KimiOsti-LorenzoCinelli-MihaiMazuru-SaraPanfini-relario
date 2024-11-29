package it.unibo.oop.relario.model.entities.npc;

import java.util.Optional;

import it.unibo.oop.relario.model.inventory.InventoryItem;

public class InteractionOutput {
    private final String dialogue;
    private final Optional<InventoryItem> item;

    public InteractionOutput(final String dialogue, final Optional<InventoryItem> item) {
        this.dialogue = dialogue;
        this.item = item;
    }

    public String getDialogue() {
        return this.dialogue;
    }

    public Optional<InventoryItem> getItem() {
        return this.item;
    }
    
}
