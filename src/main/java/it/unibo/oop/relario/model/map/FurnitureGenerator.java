package it.unibo.oop.relario.model.map;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureFactory;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureFactoryImpl;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureType;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.PositionImpl;
import it.unibo.oop.relario.model.GameEntityType;

/**
 * This class generates and places different types of furniture in the room.
 * Each furniture item is placed in an available and valid position.
 * Each walkable item occupies an area of the room.
 */
public final class FurnitureGenerator {

    private static final int CARPET_WIDTH = 3;
    private static final int CARPET_HEIGHT = 2;
    private static final int PERIMETER_FURNITURE_ITEMS = 10;
    private static final int WALKABLE_FURNITURE_ITEMS = 6;
    private static final int LOOP_ATTEMPTS = 100;

    private final Random random = new Random();
    private final FurnitureFactory furnitureFactory;

    /**
     * Initializes the furniture generator.
     */
    public FurnitureGenerator() {
        furnitureFactory = new FurnitureFactoryImpl();
    }

    /**
     * Generates and places all the items in the room randomly.
     * @param room where the items are placed.
     */
    public void generateFurniture(final Room room) {
        final int obstructiveItems = random.nextInt(PERIMETER_FURNITURE_ITEMS / 2)
        - Math.round(PERIMETER_FURNITURE_ITEMS / 3) + 1 + Math.round(PERIMETER_FURNITURE_ITEMS / 3);
        int interactiveItems = PERIMETER_FURNITURE_ITEMS - obstructiveItems;
        final int walkableInteractiveItems = (WALKABLE_FURNITURE_ITEMS + 1) / 2;

        this.addExitCarpet(room);
        if (room.getQuest().isPresent() && room.getQuest().get().getKeyEntityType().isPresent()) {
            this.addQuestKeyEntity(room);
            interactiveItems--;
        }

        placePerimeterItems(room, obstructiveItems, this.furnitureFactory::createRandomObstructingFurniture);
        placePerimeterItems(room, interactiveItems, this.furnitureFactory::createRandomInteractiveFurniture);
        placeWalkableItems(room, walkableInteractiveItems, this.furnitureFactory::createRandomWalkableFurniture);
        placeWalkableItems(room, WALKABLE_FURNITURE_ITEMS - walkableInteractiveItems,
            this.furnitureFactory::createRandomWalkableFurnitureEmpty);
    }

    private void addQuestKeyEntity(final Room room) {
        final GameEntityType keyEntityType = room.getQuest().get().getKeyEntityType().get();
        if (keyEntityType instanceof InventoryItemType) {
            final Position randomPosition = this.getRandomPerimeterPosition(room).get();
            room.addEntity(randomPosition, this.furnitureFactory.createInteractiveFurnitureLoot(randomPosition,
            (InventoryItemType) keyEntityType));
        }
    }

    private void addExitCarpet(final Room room) {
        final Position initialPosition = new PositionImpl(room.getExit().getX() - 2, room.getExit().getY());
        final Furniture carpet = this.furnitureFactory.createWalkableFurnitureByItemEmpty(initialPosition, FurnitureType.CARPET);
        for (int i = 0; i < CARPET_WIDTH; i++) {
            room.addEntity(new PositionImpl(initialPosition.getX() + i, initialPosition.getY()), carpet);
        }
    }

    private void placeWalkableItems(final Room room, final int itemsNumber, final Function<Position, Furniture> createItem) {
        int placedItems = 0;
        Dimension defaultDimension;
        int attempts = 0;
        while (placedItems < itemsNumber && attempts < LOOP_ATTEMPTS) {
            final Optional<Position> position = getRandomInnerPosition(room);
            if (position.isEmpty()) {
                attempts = LOOP_ATTEMPTS;
            } else {
                final Furniture furniture = createItem.apply(position.get());
                defaultDimension = furniture.getType().equals(FurnitureType.CARPET)
                ? new DimensionImpl(CARPET_WIDTH, CARPET_HEIGHT) 
                : new DimensionImpl(1, 1);
                if (isAreaAvailable(room, getArea(position.get(), defaultDimension))) {
                    getArea(position.get(), defaultDimension).forEach(p -> room.addEntity(p, furniture));
                    placedItems++;
                }
            }
            attempts++;
        }
    }

    private void placePerimeterItems(final Room room, final int itemsNumber, final Function<Position, Furniture> createItem) {
        int placedItems = 0;
        int attempts = 0;
        while (placedItems < itemsNumber && attempts < LOOP_ATTEMPTS) {
            final Optional<Position> position = getRandomPerimeterPosition(room);
            if (position.isEmpty()) {
                attempts = LOOP_ATTEMPTS;
            } else if (room.isPositionValid(position.get()) && room.isCellAvailable(position.get())) {
                room.addEntity(position.get(), createItem.apply(position.get()));
                placedItems++;
            }
            attempts++;
        }
    }

    private Optional<Position> getRandomInnerPosition(final Room room) {
        final List<Position> innerPositions = room.getCellsByState(CellState.INNER_EMPTY);
        return !innerPositions.isEmpty() ? Optional.of(innerPositions.get(random.nextInt(innerPositions.size())))
        : Optional.empty();
    }

    private boolean isAreaAvailable(final Room room, final List<Position> area) {
        return area.stream().allMatch(p -> room.isPositionValid(p) && room.isCellAvailable(p));
    }

    private List<Position> getArea(final Position position, final Dimension dimension) {
        return IntStream.range(position.getX(), position.getX() + dimension.getWidth()).boxed()
        .flatMap(x -> IntStream.range(position.getY(), position.getY() + dimension.getHeight())
        .mapToObj(y -> new PositionImpl(x, y))).collect(Collectors.toList());
    }

    private Optional<Position> getRandomPerimeterPosition(final Room room) {
        final List<Position> perimeterPositions = room.getCellsByState(CellState.PERIMETER_EMPTY);
        return !perimeterPositions.isEmpty() ? Optional.of(perimeterPositions.get(random.nextInt(perimeterPositions.size())))
        : Optional.empty();
    }

}
