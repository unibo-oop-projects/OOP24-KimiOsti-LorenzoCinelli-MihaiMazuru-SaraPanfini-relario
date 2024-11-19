package it.unibo.oop.relario.model.entities.enemies;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;

public interface EnemyFactory {
    
    Enemy createRandomEnemy(Position position);

    Enemy createEnemyWithReward(Position position, InventoryItem reward);

    Enemy createEnemyByType(EnemyType type, Position position);

}
