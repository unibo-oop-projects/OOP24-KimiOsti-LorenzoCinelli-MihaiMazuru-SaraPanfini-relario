package it.unibo.oop.relario.model.entities.npc;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.LivingBeingImpl;
import it.unibo.oop.relario.utils.api.Position;

public final class NotInteractiveNpc extends LivingBeingImpl implements Npc {

    private final DialoguesGenerator dialoguesGenerator;

    public NotInteractiveNpc(final String name, final Position position, final DialoguesGenerator dialoguesGenerator) {
        super(name, position);
        this.dialoguesGenerator = dialoguesGenerator;
    }

    @Override
    public InteractionOutput interact() {
        return new InteractionOutput(this.dialoguesGenerator.getDefaultDialogue(), Optional.empty());
    }

}
