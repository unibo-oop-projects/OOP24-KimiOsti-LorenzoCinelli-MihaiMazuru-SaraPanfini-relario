package it.unibo.oop.relario.model.entities.npc;

public class QuestBehavior implements NpcBehavior {

    private final String questDialogue;

    public QuestBehavior(final String questDialogue) {
        this.questDialogue = questDialogue;
    }

    @Override
    public String getDialogue() {
        return this.questDialogue;
    }
    
}
