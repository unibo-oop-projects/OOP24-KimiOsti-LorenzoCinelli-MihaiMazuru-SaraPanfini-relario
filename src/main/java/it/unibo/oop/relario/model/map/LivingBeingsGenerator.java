package it.unibo.oop.relario.model.map;

import java.util.Random;

import it.unibo.oop.relario.model.entities.enemies.EnemyFactory;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;

public class LivingBeingsGenerator {
    public static final int ENEMIES_NUMBER = 5;

    private final Random random = new Random();
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();

    public void generateLivingBeings(Room room) {
        int placedEnemies = 0;

        while (placedEnemies < ENEMIES_NUMBER) {
            Position position = getRandomInnerPosition(room);
            if (room.isPositionValid(position) && room.isCellAvailable(position)) {
                room.addCharacter(position, this.enemyFactory.createRandomEnemy(position));
            }
        } 
    }

    private Position getRandomInnerPosition(Room room) {
        return room.getInnerCells().get(random.nextInt(room.getInnerCells().size()));
    }

}
