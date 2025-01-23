package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.entities.npc.InteractiveNpc;
import it.unibo.oop.relario.model.map.Room;

public class NpcInteractObjective implements ObjectiveStrategy {

    private final Optional<GameEntityType> keyEntityType;

    public NpcInteractObjective(final Optional<GameEntityType> keyEntityType) {
        this.keyEntityType = keyEntityType;
    }

    @Override
    public boolean check(Room room) {
        return room.getPopulation().values().stream().filter(lb -> lb instanceof InteractiveNpc)
        .map(n -> (InteractiveNpc) n).allMatch(n -> !n.hasLoot());
    }

    @Override
    public Optional<GameEntityType> getKeyEntityType() {
        return this.keyEntityType;
    }
    
}
