package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.enemies.Enemy;

public final class DefeatEnemyObjective implements ObjectiveStrategy {

    @Override
    public boolean check(final Entity keyEntity) {
        if (keyEntity instanceof Enemy) {
            return ((Enemy)keyEntity).getLife() <= 0; // ??
        }
        throw new IllegalArgumentException();        
    }

}
