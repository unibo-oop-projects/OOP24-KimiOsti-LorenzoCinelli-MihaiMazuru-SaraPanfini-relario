package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.entities.npc.Npc;
import it.unibo.oop.relario.model.map.Room;

/**
 * TODO.
 */
public final class NpcInteractObjective implements ObjectiveStrategy {

    private final Optional<GameEntityType> keyEntityType;

    /**
     * TODO.
     * @param keyEntityType
     */
    public NpcInteractObjective(final Optional<GameEntityType> keyEntityType) {
        this.keyEntityType = keyEntityType;
    }

    @Override
    public boolean check(final Room room) {
        return room.getPopulation().values().stream().filter(lb -> lb instanceof Npc)
        .map(n -> (Npc) n).allMatch(n -> n.hasInteracted());
    }

    @Override
    public Optional<GameEntityType> getKeyEntityType() {
        return this.keyEntityType;
    }
}
