package it.unibo.oop.relario.model.entities.enemies;

import it.unibo.oop.relario.utils.api.Position;

public interface EnemyFactory {
    
    Enemy createRandomEnemy(Position position);

    Enemy createEnemy(EnemyType type, Position position);

}
