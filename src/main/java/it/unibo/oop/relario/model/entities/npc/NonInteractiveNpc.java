package it.unibo.oop.relario.model.entities.npc;

import java.util.Optional;

import it.unibo.oop.relario.utils.api.Position;

public class NonInteractiveNpc extends NpcImpl {

    private final DialoguesGenerator dialoguesGenerator;

    public NonInteractiveNpc(String name, Position position, DialoguesGenerator dialoguesGenerator) {
        super(name, position);
        this.dialoguesGenerator = dialoguesGenerator;
    }

    @Override
    public InteractionOutput interact() {
        return new InteractionOutput(this.dialoguesGenerator.getDefaultDialogue(), Optional.empty());
    }

}
