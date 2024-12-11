package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.living.MainCharacter;

public final class CollectItemObjective implements ObjectiveStrategy {

    private final MainCharacter player;

    public CollectItemObjective(final MainCharacter player) {
        this.player = player;
    }

    @Override
    public boolean check(final Entity keyEntity) {
        return this.player.getItems().contains(keyEntity);
    }

}
