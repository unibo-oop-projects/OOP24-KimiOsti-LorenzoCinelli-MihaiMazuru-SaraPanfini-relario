package it.unibo.oop.relario.model.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.LivingBeingImpl;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactory;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.npc.NpcFactory;
import it.unibo.oop.relario.model.entities.npc.NpcFactoryImpl;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * This class generates and places living beings, such as enemies and NPCs, in the room.
 */
public final class LivingBeingsGenerator {

    public static final int CHARACTERS_NUMBER = 4;

    private final Random random = new Random();
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final NpcFactory npcFactory = new NpcFactoryImpl();
    private List<Area> areas = new ArrayList<>();

    /**
     * Generates and places enemies and NPCs randomly.
     * @param room where the living beings are placed
     */
    public void generateLivingBeings(final Room room) {
        final int enemiesNumber = random.nextInt(CHARACTERS_NUMBER);
        final int npcNumber = CHARACTERS_NUMBER - enemiesNumber;

        divideRoom(room.getDimension());

        placeCharacters(room, enemiesNumber, this.enemyFactory::createRandomEnemy);
        placeCharacters(room, npcNumber, this.npcFactory::createRandomNpc);
    }

    private void divideRoom(final Dimension dimension) {
        final int areaWidth = (int) Math.floor(dimension.getWidth() / 2);
        final int areaHeight = (int) Math.floor(dimension.getHeight() / 2);
        
        this.areas.add(new Area(new PositionImpl(0, 0), new DimensionImpl(areaWidth, areaHeight)));
        this.areas.add(new Area(new PositionImpl(areaWidth, 0), new DimensionImpl(areaWidth, areaHeight)));
        this.areas.add(new Area(new PositionImpl(0, areaHeight), new DimensionImpl(areaWidth, areaHeight)));
        this.areas.add(new Area(new PositionImpl(areaWidth, areaHeight), new DimensionImpl(areaWidth, areaHeight)));
    }

    private void placeCharacters(final Room room, final int charactersNumber, final Function<Position, 
    LivingBeing> createCharacter) {
        int placedCharacters = 0;
        while (placedCharacters < charactersNumber) {
            final Position position = getRandomPositionInArea(this.areas.get(placedCharacters));
            if (room.isPositionValid(position) && isAreaPositionValid(position, this.areas.get(placedCharacters))) {
                room.addEntity(position, createCharacter.apply(position));
                placedCharacters++;
            }
        }
    }

    private boolean isAreaPositionValid(final Position position, final Area area) {
        return position.getX() + LivingBeingImpl.DIRECTION_RANGE < area.initialPosition().getX() + area.dimension().getWidth() - 1 &&
        position.getX() - LivingBeingImpl.DIRECTION_RANGE > area.initialPosition().getX() &&
        position.getY() > area.initialPosition.getY() + 1 && position.getY() < area.dimension().getHeight() - 1;
    }

    private Position getRandomPositionInArea(final Area area) {
        return new PositionImpl(area.initialPosition().getX() + random.nextInt(area.dimension().getWidth()), 
        area.initialPosition().getY() + random.nextInt(area.dimension().getHeight()));
    }

    private record Area(Position initialPosition, Dimension dimension) {}

}
