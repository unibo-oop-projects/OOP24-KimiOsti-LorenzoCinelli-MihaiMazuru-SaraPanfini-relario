package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.entities.npc.Npc;
import it.unibo.oop.relario.model.map.Room;

/**
 * Implements a quest objective that requires interaction with an NPC.
 */
public final class NpcInteractObjective implements ObjectiveStrategy {

    private final Optional<GameEntityType> keyEntityType;

    /**
     * Instantiates the quest objective.
     * @param keyEntityType the type of the NPC that the player must interact with.
     */
    public NpcInteractObjective(final Optional<GameEntityType> keyEntityType) {
        this.keyEntityType = keyEntityType;
    }

    @Override
    public boolean check(final Room room) {
        return room.getPopulation().values().stream().filter(lb -> lb instanceof Npc)
        .map(n -> (Npc) n).allMatch(Npc::hasInteracted);
    }

    @Override
    public Optional<GameEntityType> getKeyEntityType() {
        return this.keyEntityType;
    }
}
