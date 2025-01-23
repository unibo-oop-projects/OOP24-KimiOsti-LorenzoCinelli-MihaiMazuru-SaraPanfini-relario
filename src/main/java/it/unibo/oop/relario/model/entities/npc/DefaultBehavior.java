package it.unibo.oop.relario.model.entities.npc;

public class DefaultBehavior implements NpcBehavior {

    private final DialoguesGenerator dialoguesGenerator;

    public DefaultBehavior(final DialoguesGenerator dialoguesGenerator) {
        this.dialoguesGenerator = dialoguesGenerator;
    }

    @Override
    public String getDialogue() {
        return this.dialoguesGenerator.getDialogue(DialogueType.DEFAULT);
    }
    
}
