package it.unibo.oop.relario.model.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.LivingBeingImpl;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactory;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.entities.npc.NpcFactory;
import it.unibo.oop.relario.model.entities.npc.NpcFactoryImpl;
import it.unibo.oop.relario.model.quest.Quest;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * This class generates and places living beings, such as enemies and NPCs, in the room.
 */
public final class LivingBeingsGenerator {

    private static final int CHARACTERS_NUMBER = 4;
    private static final int LOOP_ATTEMPTS = 100;

    private final Random random = new Random();
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final NpcFactory npcFactory = new NpcFactoryImpl();
    private final List<Area> areas = new ArrayList<>();

    /**
     * Generates and places enemies and NPCs randomly.
     * @param room where the living beings are placed
     */
    public void generateLivingBeings(final Room room) {
        final int npcNumber = random.nextInt(CHARACTERS_NUMBER - 2 + 1) + 2;
        final int enemiesNumber = CHARACTERS_NUMBER - npcNumber;

        this.addQuestEntities(room);

        /*if (room.getQuest().isPresent()) {
            final Position questNpcPosition = new PositionImpl(room.getEntry().getX() + 2, room.getEntry().getY() - 1);
            final Npc questNpc = this.npcFactory.createQuestNpc(questNpcPosition, room.getQuest().get().getDescription());
            ((NpcImpl) questNpc).setMoving(false);
            room.addEntity(questNpcPosition, questNpc);
        }
        if (room.getQuest().isPresent() && room.getQuest().get().getKeyEntityType().isPresent()) {
            this.addQuestKeyEntity(room);
        }*/
        divideRoom(room.getDimension());
        placeCharacters(room, enemiesNumber, this.enemyFactory::createRandomEnemy);
        placeCharacters(room, npcNumber, this.npcFactory::createRandomNpc);
    }

    private void addQuestEntities(final Room room) {
        if (room.getQuest().isPresent()) {
            final Quest quest = room.getQuest().get();
            final Position questNpcPosition = new PositionImpl(room.getEntry().getX() + 2, room.getEntry().getY() - 1);
            this.addCharacterToRoom(room, Direction.DOWN, 
            this.npcFactory.createQuestNpc(questNpcPosition, quest.getDescription()));
            if (quest.getKeyEntityType().isPresent() && quest.getKeyEntityType().get() instanceof EnemyType) {
                final Position enemyPosition = new PositionImpl(room.getExit().getX() - 4, room.getExit().getY());
                this.addCharacterToRoom(room, Direction.LEFT, 
                this.enemyFactory.createEnemyByTypeEmpty((EnemyType) quest.getKeyEntityType().get(), enemyPosition));
            }
        }
    }

    private void addCharacterToRoom(final Room room, final Direction direction, final LivingBeing keyCharacter) {
        ((LivingBeingImpl) keyCharacter).setMoving(false);
        ((LivingBeingImpl) keyCharacter).setDirection(direction);
        room.addEntity(keyCharacter.getPosition().get(), keyCharacter);
    }

    private void divideRoom(final Dimension dimension) {
        final int areaWidth = dimension.getWidth() / 2;
        final int areaHeight = dimension.getHeight() / 2;

        this.areas.add(new Area(new PositionImpl(0, 0), new DimensionImpl(areaWidth, areaHeight)));
        this.areas.add(new Area(new PositionImpl(areaWidth, 0), new DimensionImpl(areaWidth, areaHeight)));
        this.areas.add(new Area(new PositionImpl(0, areaHeight + 1), new DimensionImpl(areaWidth, areaHeight)));
        this.areas.add(new Area(new PositionImpl(areaWidth, areaHeight + 1), new DimensionImpl(areaWidth, areaHeight)));
    }

    private void placeCharacters(final Room room, final int charactersNumber, final Function<Position, 
    LivingBeing> createCharacter) {
        int placedCharacters = 0;
        int attempts = 0;
        while (placedCharacters < charactersNumber && attempts < LOOP_ATTEMPTS) {
            final Area area = this.areas.get(random.nextInt(areas.size()));
            final Position position = getRandomPositionInArea(area);
            if (room.isPositionValid(position) && isAreaPositionValid(position, area)) {
                room.addEntity(position, createCharacter.apply(position));
                this.areas.remove(area);
                placedCharacters++;
            }
            attempts++;
        }
    }

    private boolean isAreaPositionValid(final Position position, final Area area) {
        return position.getX() + LivingBeingImpl.DIRECTION_RANGE
        < area.initialPosition().getX() + area.dimension().getWidth() - 2
        && position.getX() - LivingBeingImpl.DIRECTION_RANGE > area.initialPosition().getX() + 1
        && position.getY() > area.initialPosition.getY() + 1 && position.getY() < area.initialPosition.getY() 
        + area.dimension().getHeight() - 2;
    }

    private Position getRandomPositionInArea(final Area area) {
        return new PositionImpl(area.initialPosition().getX() + random.nextInt(area.dimension().getWidth()), 
        area.initialPosition().getY() + random.nextInt(area.dimension().getHeight()));
    }

    private record Area(Position initialPosition, Dimension dimension) { }

}
