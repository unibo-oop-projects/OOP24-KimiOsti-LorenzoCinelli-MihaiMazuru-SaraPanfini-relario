package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.map.Room;

/**
 * Implementation of the objective of a quest that requires to defeat a certain enemy.
 */
public final class DefeatEnemyObjective implements ObjectiveStrategy {

    private final Optional<EnemyType> keyEnemyType;

    /**
     * Instantiates the quest's objective.
     * @param keyEnemyType the type of the enemy that has to be defeated.
     */
    public DefeatEnemyObjective(final Optional<GameEntityType> keyEnemyType) {
        if (keyEnemyType.isPresent() && keyEnemyType.get() instanceof EnemyType) {
            this.keyEnemyType = Optional.of((EnemyType) keyEnemyType.get());
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
    public Optional<EnemyType> getKeyEntityType() {
        return this.keyEnemyType;
    }

}
