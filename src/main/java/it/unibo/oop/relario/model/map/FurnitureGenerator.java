package it.unibo.oop.relario.model.map;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.oop.relario.model.entities.furniture.api.FurnitureItem;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureItemFactory;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureItemFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

public class FurnitureGenerator {
    public static final int FURNITURE_ITEMS_NUMBER = 21;
    public static final int WALKABLE_ITEMS_NUMBER = 4;
    public static final int WALKABLE_ITEMS_SIZE = 3;

    private final Random random = new Random();
    private final FurnitureItemFactory furnitureFactory = new FurnitureItemFactoryImpl();

    public void generateFurniture(Room room) {
        int obstructiveItems = random.nextInt(FURNITURE_ITEMS_NUMBER / 3) + 2;
        int interactiveItems = FURNITURE_ITEMS_NUMBER - obstructiveItems;
        

        placeFurniture(room, obstructiveItems, this.furnitureFactory::createObstructingFurnitureItem);
        placeFurniture(room, interactiveItems / 3, this.furnitureFactory::createInteractiveFurnitureItemEmpty);
        placeFurniture(room, interactiveItems - (interactiveItems / 3), this.furnitureFactory::createInteractiveFurnitureItem);
        placeWalkableFurniture(room);
    }

    /*private void placeItems(Room room, int itemsNumber, Function<Position, FurnitureItem> createItem,
    Supplier<Position> positionSupplier, Function<Position, List<Position>> getPositions) {
        int placedItems = 0;
            
        while (placedItems < itemsNumber) {
            Position randomPosition = positionSupplier.get();
            List<Position> positions = getPositions.apply(randomPosition);
                
            if (positions.stream().allMatch(p -> room.isPositionValid(p) && room.isCellAvailable(p))) {
                FurnitureItem furniture = createItem.apply(randomPosition);
                positions.forEach(p -> room.addFurniture(randomPosition, furniture));
                placedItems++;
            }
        }
    }*/
    
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
            if (isAreaAvailable(room, getArea(initialPosition))) {
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

    private boolean isAreaAvailable(Room room, List<Position> area) {
        return area.stream().allMatch(p -> room.isPositionValid(p) && room.isCellAvailable(p)
        && room.getInnerCells().contains(p));
    }

    private List<Position> getArea(Position position) {
        return IntStream.rangeClosed(position.getX(), position.getX() + WALKABLE_ITEMS_SIZE).boxed()
        .flatMap(x -> IntStream.range(position.getY(), position.getY() + WALKABLE_ITEMS_SIZE)
        .mapToObj(y -> new PositionImpl(x, y))).collect(Collectors.toList());
    }

}
