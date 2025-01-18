package it.unibo.oop.relario.model.map;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureFactory;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureFactoryImpl;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureType;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * This class generates and places different types of furniture in the room.
 * Each furniture item is placed in an available and valid position.
 * Each walkable item occupies an area of the room.
 */
public final class FurnitureGenerator {

    /** The width of the walkable items' area. */
    public static final int WALKABLE_ITEMS_WIDTH = 3;

    /** The height of the walkable items' area. */
    public static final int WALKABLE_ITEMS_HEIGHT = 2;

    public static final int FURNITURE_ITEMS_SIZE = 1;

    private final Random random = new Random();
    private final FurnitureFactory furnitureFactory;
    private final int perimeterFurnitureItems;
    private final int walkableFurnitureItems;

    public FurnitureGenerator(final Dimension dimension) {
        furnitureFactory = new FurnitureFactoryImpl();
        this.perimeterFurnitureItems = (int) Math.round((dimension.getWidth() + dimension.getHeight()) * 2 / 4);
        this.walkableFurnitureItems = (int) Math.round((dimension.getWidth() * dimension.getHeight()) / 
        (WALKABLE_ITEMS_WIDTH * WALKABLE_ITEMS_HEIGHT) * 0.10);
    }

    /**
     * Generates and places all the items in the room randomly.
     * @param room where the items are placed
     */
    public void generateFurniture(final Room room) {
        final int obstructiveItems = random.nextInt(((int) Math.floor(this.perimeterFurnitureItems / 2)) 
        - ((int) Math.round(this.perimeterFurnitureItems / 3)) + 1) + ((int) Math.round(this.perimeterFurnitureItems / 3));
        final int interactiveItems = this.perimeterFurnitureItems - obstructiveItems;
        final int walkableInteractiveItems = (int) Math.ceil(this.walkableFurnitureItems / 2);
        
        this.addExitCarpet(room);
        placeItems(room, obstructiveItems, this.furnitureFactory::createRandomObstructingFurniture, 
        getRandomPerimeterPosition(room), new DimensionImpl(FURNITURE_ITEMS_SIZE, FURNITURE_ITEMS_SIZE));
        placeItems(room, interactiveItems, this.furnitureFactory::createRandomInteractiveFurniture, 
        getRandomPerimeterPosition(room), new DimensionImpl(FURNITURE_ITEMS_SIZE, FURNITURE_ITEMS_SIZE));
        placeItems(room, walkableInteractiveItems, this.furnitureFactory::createRandomWalkableFurniture, 
        getRandomInnerPosition(room), new DimensionImpl(WALKABLE_ITEMS_WIDTH, WALKABLE_ITEMS_HEIGHT));
        placeItems(room, walkableFurnitureItems - walkableInteractiveItems, this.furnitureFactory::createRandomWalkableFurnitureEmpty, 
        getRandomInnerPosition(room), new DimensionImpl(WALKABLE_ITEMS_WIDTH, WALKABLE_ITEMS_HEIGHT));
    }

    private void addExitCarpet(final Room room) {
        Position initialPosition = new PositionImpl(room.getExit().getX() - 2, room.getExit().getY());
        Furniture carpet = this.furnitureFactory.createWalkableFurnitureByItemEmpty(initialPosition, FurnitureType.CARPET);
        for (int i = 0; i < WALKABLE_ITEMS_WIDTH; i++) {
            room.addEntity(new PositionImpl(initialPosition.getX() + i, initialPosition.getY()), carpet);
        }
    }

    private void placeItems(final Room room, final int itemsNumber, final Function<Position, Furniture> createItem,
    final Supplier<Position> positionSupplier, final Dimension itemDimension) {
        int placedItems = 0;
        while (placedItems < itemsNumber) {
            Position position = positionSupplier.get();
            List<Position> furnitureArea = getArea(position, itemDimension);
            if (isAreaAvailable(room, furnitureArea)) {
                final Furniture furnitureItem = createItem.apply(position);
                furnitureArea.forEach(p -> room.addEntity(p, furnitureItem));
                placedItems++;
            }
        }
    }    

    private Supplier<Position> getRandomInnerPosition(final Room room) {
        return () -> {
            List<Position> innerPositions = room.getCellsByState(CellState.INNER_EMPTY);
            return innerPositions.get(random.nextInt(innerPositions.size()));
        };
    }

    private boolean isAreaAvailable(final Room room, final List<Position> area) {
        return area.stream().allMatch(p -> room.isPositionValid(p) && room.isCellAvailable(p));
    }

    private List<Position> getArea(final Position position, final Dimension dimension) {
        return IntStream.range(position.getX(), position.getX() + dimension.getWidth()).boxed()
        .flatMap(x -> IntStream.range(position.getY(), position.getY() + dimension.getHeight())
        .mapToObj(y -> new PositionImpl(x, y))).collect(Collectors.toList());
    }

    private Supplier<Position> getRandomPerimeterPosition(final Room room) {
        return () -> {
            List<Position> perimeterPositions = room.getCellsByState(CellState.PERIMETER_EMPTY);
            return perimeterPositions.get(random.nextInt(perimeterPositions.size()));
        };
    }

}
