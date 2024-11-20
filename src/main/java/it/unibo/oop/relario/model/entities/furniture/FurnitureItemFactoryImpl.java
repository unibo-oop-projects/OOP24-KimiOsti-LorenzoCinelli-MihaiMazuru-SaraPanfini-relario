package it.unibo.oop.relario.model.entities.furniture;

import it.unibo.oop.relario.model.entities.enemies.EnemyFactory;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Factory used to create different types of furniture.
 */
public class FurnitureItemFactoryImpl implements FurnitureItemFactory {

    FurnitureItemFactoryImpl() {}

    @Override
    public FurnitureItem createWalkableFurnitureItem(final Position pos) {
        final EnemyFactory enemy = new EnemyFactoryImpl();
        return new WalkableFurnitureImpl(pos, enemy.createRandomEnemy(null));
    }

    @Override
    public FurnitureItem createObstructingFurnitureItem(final Position pos) {
        return new ObstructingFurnitureItem(pos);
    }

    @Override
    public FurnitureItem createInteractiveFurnitureItem(final Position pos) { 
        return new InteractiveFurnitureItem(pos);
    }
}
