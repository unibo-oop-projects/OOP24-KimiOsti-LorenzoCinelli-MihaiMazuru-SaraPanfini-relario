package it.unibo.oop.relario.model.entities.npc;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.LivingBeingImpl;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Class representing a non-interactive NPC.
 * A non-interactive NPC can interact with the player only through default dialogues.
 */
public final class NotInteractiveNpc extends LivingBeingImpl implements Npc {

    private final NpcBehavior behavior;

    /**
     * Constructs a non-interactive NPC.
     * @param name of the NPC
     * @param position of the NPC
     * @param dialoguesGenerator use to generate a random dialogue for the NPC
     */
    public NotInteractiveNpc(final String name, final Position position, 
    final NpcBehavior behavior) {
        super(name, position);
        this.behavior = behavior;
    }

    @Override
    public InteractionOutput interact() {
        return new InteractionOutput(this.behavior.getDialogue(), Optional.empty());
    }

}
