package it.unibo.oop.relario.model.map;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureFactory;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * This class generates and places different types of furniture in the room.
 * Each furniture item is placed in an available and valid position.
 * Each walkable item occupies an area of the room.
 */
public final class FurnitureGenerator {

    /** The number of furniture items that have to be placed. */
    public static final int FURNITURE_ITEMS_NUMBER = 21;

    /** The number of walkable items that have to be placed. */
    public static final int WALKABLE_ITEMS_NUMBER = 4;

    /** The size of the walkable items' area. */
    public static final int WALKABLE_ITEMS_SIZE = 3;

    private final Random random = new Random();
    private final FurnitureFactory furnitureFactory = new FurnitureFactoryImpl();

    /**
     * Generates and places all the items in the room randomly.
     * @param room where the items are placed
     */
    public void generateFurniture(final Room room) {
        final int obstructiveItems = random.nextInt(FURNITURE_ITEMS_NUMBER / 3) + 2;
        final int interactiveItems = FURNITURE_ITEMS_NUMBER - obstructiveItems;


        placeFurniture(room, obstructiveItems, this.furnitureFactory::createRandomObstructingFurniture);
        placeFurniture(room, interactiveItems, this.furnitureFactory::createRandomInteractiveFurniture);
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

    private void placeFurniture(final Room room, final int itemsNumber, final Function<Position, Furniture> createItem) {
        int placedItems = 0;

        while (placedItems < itemsNumber) {
            final Position position = getRandomPerimeterPosition(room);
            if (room.isPositionValid(position) && room.isCellAvailable(position)) {
                room.addEntity(position, createItem.apply(position));
                placedItems++;
            }
        }
    }

    private void placeWalkableFurniture(final Room room) {
        int placedItems = 0;

        while (placedItems < WALKABLE_ITEMS_NUMBER) {
            final Position initialPosition = getRandomInnerPosition(room);
            if (isAreaAvailable(room, getArea(initialPosition))) {
                final Furniture walkableItem = random.nextBoolean() 
                ? this.furnitureFactory.createRandomWalkableFurniture(initialPosition)
                : this.furnitureFactory.createRandomWalkableFurnitureEmpty(initialPosition);
                getArea(initialPosition).forEach(p -> room.addEntity(p, walkableItem));
                placedItems++;
            }
        }
    }

    private Position getRandomPerimeterPosition(final Room room) {
        return room.getPerimeter().get(random.nextInt(room.getPerimeter().size()));
    }

    private Position getRandomInnerPosition(final Room room) {
        return room.getInnerCells().get(random.nextInt(room.getInnerCells().size()));
    }

    private boolean isAreaAvailable(final Room room, final List<Position> area) {
        return area.stream().allMatch(p -> room.isPositionValid(p) && room.isCellAvailable(p)
        && room.getInnerCells().contains(p));
    }

    private List<Position> getArea(final Position position) {
        return IntStream.rangeClosed(position.getX(), position.getX() + WALKABLE_ITEMS_SIZE).boxed()
        .flatMap(x -> IntStream.range(position.getY(), position.getY() + WALKABLE_ITEMS_SIZE)
        .mapToObj(y -> new PositionImpl(x, y))).collect(Collectors.toList());
    }

}
