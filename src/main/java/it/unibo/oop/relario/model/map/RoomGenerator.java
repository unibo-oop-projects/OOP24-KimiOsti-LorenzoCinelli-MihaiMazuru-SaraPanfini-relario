package it.unibo.oop.relario.model.map;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.oop.relario.model.entities.enemies.EnemyFactory;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureItem;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureItemFactory;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureItemFactoryImpl;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.PositionImpl;

public class RoomGenerator {

    public static final Dimension DEFAULT_DIMENSION = new DimensionImpl(0, 0);
    public static final int FURNITURE_ITEMS_NUMBER = 21;
    public static final int WALKABLE_ITEMS_NUMBER = 4;
    public static final int WALKABLE_ITEMS_SIZE = 3;
    public static final int ENEMIES_NUMBER = 5;

    private final Random random = new Random();
    private final FurnitureItemFactory furnitureFactory = new FurnitureItemFactoryImpl();
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    
    public Room createRoom(MainCharacter player) {
        Room newRoom = new RoomImpl(DEFAULT_DIMENSION, player);
    
        generateFurniture(newRoom);
        generateCharacters(newRoom);

        return newRoom;
    }

    private void generateCharacters(Room newRoom) {
        
        placeEnemies(newRoom);

    }

    private void placeEnemies(Room room) {
        int placedEnemies = 0;

        while (placedEnemies < ENEMIES_NUMBER) {
            Position position = getRandomInnerPosition(room);
            if (room.isPositionValid(position) && room.isCellAvailable(position)) {
                room.addCharacter(position, this.enemyFactory.createRandomEnemy(position));
            }
        }
    }

    private void generateFurniture(Room newRoom) {
        int obstructiveItems = random.nextInt(FURNITURE_ITEMS_NUMBER / 3) + 2;
        int interactiveItems = FURNITURE_ITEMS_NUMBER - obstructiveItems;
        

        placeFurniture(newRoom, obstructiveItems, this.furnitureFactory::createObstructingFurnitureItem);
        placeFurniture(newRoom, interactiveItems / 2, this.furnitureFactory::createInteractiveFurnitureItem);
        placeFurniture(newRoom, interactiveItems / 2, this.furnitureFactory::createInteractiveFurnitureItemEmpty);
        placeWalkableFurniture(newRoom);
    }

    private void placeFurniture(Room room, int itemsNumber, Function<Position, FurnitureItem> createItem) {
        int placedItems = 0;
        
        while (placedItems < itemsNumber) {
            Position position = getRandomPerimeterPosition(room);
            if (room.isPositionValid(position) && room.isCellAvailable(position)) {
                room.addFurniture(position, createItem.apply(position));
                placedItems++;
            }
        }
    }

    private void placeWalkableFurniture(Room room) {
        int placedItems = 0;

        while (placedItems < WALKABLE_ITEMS_NUMBER) {
            Position initialPosition = getRandomInnerPosition(room);
            if (isAreaAvailable(room, initialPosition)) {
                FurnitureItem walkableItem = random.nextBoolean() ? this.furnitureFactory.createWalkableFurnitureItem(initialPosition)
                : this.furnitureFactory.createWalkableFurnitureItemEmpty(initialPosition);
                getArea(initialPosition).forEach(p -> room.addFurniture(p, walkableItem));
                placedItems++;
            }
        }
    }

    private Position getRandomPerimeterPosition(Room room) {
        return room.getPerimeter().get(random.nextInt(room.getPerimeter().size()));
    }

    private Position getRandomInnerPosition(Room room) {
        return room.getInnerCells().get(random.nextInt(room.getInnerCells().size()));
    }

    private boolean isAreaAvailable(Room room, Position position) {
        return getArea(position).stream().allMatch(p -> room.isPositionValid(p) && room.isCellAvailable(p)
        && room.getInnerCells().contains(p));
    }

    private List<Position> getArea(Position position) {
        return IntStream.rangeClosed(position.getX(), position.getX() + WALKABLE_ITEMS_SIZE).boxed()
        .flatMap(x -> IntStream.range(position.getY(), position.getY() + WALKABLE_ITEMS_SIZE)
        .mapToObj(y -> new PositionImpl(x, y))).collect(Collectors.toList());
    }

}
