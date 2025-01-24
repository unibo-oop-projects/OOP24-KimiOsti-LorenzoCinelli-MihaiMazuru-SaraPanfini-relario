package it.unibo.oop.relario.model.entities.npc;

/**
 * Implementation of {@link NpcBehavior} as default behavior.
 */
public final class DefaultBehavior implements NpcBehavior {

    private final DialoguesGenerator dialoguesGenerator;

    /**
     * Initializes a default behavior.
     * @param dialoguesGenerator the dialogue generator for the NPC. 
     */
    public DefaultBehavior(final DialoguesGenerator dialoguesGenerator) {
        this.dialoguesGenerator = dialoguesGenerator;
    }

    @Override
    public String getDialogue() {
        return this.dialoguesGenerator.getDialogue(DialogueType.DEFAULT);
    }

}
