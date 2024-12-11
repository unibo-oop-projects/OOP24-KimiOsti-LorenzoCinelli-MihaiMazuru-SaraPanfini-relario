package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.enemies.Enemy;

public final class DefeatEnemyObjective implements ObjectiveStrategy {

    private final Enemy enemy;

    public DefeatEnemyObjective(final Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public boolean check() {
        return this.enemy.getLife() <= 0; // ??
    }

    @Override
    public Entity getKeyItem() {
        return this.enemy;
    }

}
