package it.unibo.oop.relario.model.entities.npc;

public class LootBehavior implements NpcBehavior {

    private final DialoguesGenerator dialoguesGenerator;

    public LootBehavior(final DialoguesGenerator dialoguesGenerator) {
        this.dialoguesGenerator = dialoguesGenerator;
    }

    @Override
    public String getDialogue(final boolean hasLoot) {
        if (hasLoot) {
            return this.dialoguesGenerator.getDialogue(DialogueType.LOOT);
        } else {
            return this.dialoguesGenerator.getDialogue(DialogueType.NO_LOOT);
        }
    }
    
}
