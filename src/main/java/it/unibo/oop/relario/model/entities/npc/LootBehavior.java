package it.unibo.oop.relario.model.entities.npc;

/**
 * Implementation for the behavior of an NPC that releases a loot.
 */
public final class LootBehavior implements NpcBehavior {

    private final DialoguesGenerator dialoguesGenerator;

    /**
     * Instantiates a loot behavior.
     * @param dialoguesGenerator the generator for the NPC's dialogue.
     */
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
