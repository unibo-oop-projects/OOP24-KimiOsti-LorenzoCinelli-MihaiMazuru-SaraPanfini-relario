package it.unibo.oop.relario.model.entities.npc;

/**
 * Implementation of {@link NpcBehavior} as quest behavior.
 */
public final class QuestBehavior implements NpcBehavior {

    private final String questDialogue;

    /**
     * Initializes a quest behavior.
     * @param questDialogue the dialogue generator for the NPC. 
     */
    public QuestBehavior(final String questDialogue) {
        this.questDialogue = questDialogue;
    }

    @Override
    public String getDialogue() {
        return this.questDialogue;
    }

}
