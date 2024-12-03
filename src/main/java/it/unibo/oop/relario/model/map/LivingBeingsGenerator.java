package it.unibo.oop.relario.model.map;

import java.util.Random;
import java.util.function.Function;

import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactory;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.npc.NpcFactory;
import it.unibo.oop.relario.model.entities.npc.NpcFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;

/**
 * This class generates and places living beings, such as enemies and NPCs, in the room.
 */
public final class LivingBeingsGenerator {

    /** The number of enemies in the room. */
    public static final int ENEMIES_NUMBER = 5;

    /** The number of NPCs in the room. */
    public static final int NPC_NUMBER = 3;

    private final Random random = new Random();
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final NpcFactory npcFactory = new NpcFactoryImpl();

    /**
     * Generates and places enemies and NPCs randomly.
     * @param room where the living beings are placed
     */
    public void generateLivingBeings(final Room room) {
        placeCharacters(room, ENEMIES_NUMBER, this.enemyFactory::createRandomEnemy);
        placeCharacters(room, NPC_NUMBER, this.npcFactory::createRandomNpc);
    }

    private void placeCharacters(final Room room, final int charactersNumber, final Function<Position, 
    LivingBeing> createCharacter) {
        int placedCharacters = 0;

        while (placedCharacters < charactersNumber) {
            final Position position = getRandomInnerPosition(room);
            if (room.isPositionValid(position) && room.isCellAvailable(position)) {
                room.addCharacter(position, createCharacter.apply(position));
                placedCharacters++;
            }
        }
    }

    private Position getRandomInnerPosition(final Room room) {
        return room.getInnerCells().get(random.nextInt(room.getInnerCells().size()));
    }

}
