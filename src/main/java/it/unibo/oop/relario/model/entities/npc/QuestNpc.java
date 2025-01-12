package it.unibo.oop.relario.model.entities.npc;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.LivingBeingImpl;
import it.unibo.oop.relario.utils.api.Position;

public class QuestNpc extends LivingBeingImpl implements Npc {

    private final String dialogue;

    public QuestNpc(final String name, final Position position, final String dialogue) {
        super(name, position);
        this.dialogue = dialogue;
    }

    @Override
    public InteractionOutput interact() {
        return new InteractionOutput(this.dialogue, Optional.empty());
    }
    
}
