package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.map.Room;

/**
 * 
 */

public final class DefeatEnemyObjective implements ObjectiveStrategy {

    private final EnemyType keyEnemyType;

    public DefeatEnemyObjective(final GameEntityType keyEnemyType) {
        if (keyEnemyType instanceof EnemyType) {
            this.keyEnemyType = (EnemyType) keyEnemyType;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean check(final Room room) {
        return room.getPopulation().values().stream().filter(lb -> lb instanceof Enemy)
        .map(e -> (Enemy) e).noneMatch(e -> e.getType().equals(this.keyEnemyType));
    }

    @Override
    public GameEntityType getKeyEntityType() {
        return this.keyEnemyType;
    }

}
