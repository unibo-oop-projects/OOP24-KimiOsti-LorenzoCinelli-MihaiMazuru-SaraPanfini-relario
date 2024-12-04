package it.unibo.oop.relario.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.LivingBeingImpl;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureItem;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.quest.Quest;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Implementation of a room in the game, containing the player, furniture and living beings.
 */
public final class RoomImpl implements Room {

    /** The range around furniture, used to define restricted areas. */
    public static final int EXCLUSION_RANGE = 2;

    /** The range around living beings, used to define restricted areas. */
    public static final int CHARACTERS_EXCLUSION_RANGE = LivingBeingImpl.DIRECTION_RANGE + EXCLUSION_RANGE;

    /** The border value used to define the edges of the room. */
    public static final int BORDER = 2;

    private final MainCharacter player;
    private final Dimension dimension;
    private final Map<Position, LivingBeing> population = new HashMap<>();
    private final Map<Position, FurnitureItem> furniture = new HashMap<>();
    private final Position entry;
    private final Position exit;
    private final Set<Position> unavailableCells = new HashSet<>();
    private List<Position> perimeter;
    private List<Position> innerCells;
    private final Optional<Quest> quest;

    /**
     * Constructs a room with the specified dimension and main character.
     * The room has an entry and an exit and the player is placed at the entry.
     * @param dimension of the room
     * @param player that is placed in the room
     */
    public RoomImpl(final MainCharacter player, final Dimension dimension, final Position entry, final Position exit, final Optional<Quest> quest) {
        this.player = player;
        this.dimension = dimension;
        this.entry = entry;
        this.exit = exit;
        this.quest = quest;
        initializeRoom();
    }

    private void initializeRoom() {
        this.player.setPosition(entry);
        this.unavailableCells.add(this.entry);
        this.unavailableCells.add(this.exit);
        this.perimeter = perimeterPositions();
        this.innerCells = innerCells();
    }

    @Override
    public Room getRoom() {
        return this;
    }

    @Override
    public MainCharacter getPlayer() {
        return this.player;
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    @Override
    public Map<Position, LivingBeing> getPopulation() {
        return Map.copyOf(this.population);
    }

    @Override
    public Map<Position, FurnitureItem> getFurniture() {
        return Map.copyOf(this.furniture);
    }

    @Override
    public Optional<Entity> getCellContent(final Position position) {
        return this.population.containsKey(position) ? Optional.of(this.population.get(position))
        : this.furniture.containsKey(position) ? Optional.of(this.furniture.get(position)) : Optional.empty();
    }

    @Override
    public boolean isCellAvailable(final Position position) {
        return !unavailableCells.contains(position);
    }

    @Override
    public void addFurniture(final Position position, final FurnitureItem furniture) {
        this.furniture.put(position, furniture);
        this.unavailableCells.addAll(adjacentCells(position, furniture));
    }

    @Override
    public void addCharacter(final Position position, final LivingBeing character) {
        this.population.put(position, character);
        this.unavailableCells.addAll(adjacentCells(position, character));
    }

    @Override
    public void removeEntity(final Position position) {
        if (population.containsKey(position)) {
            this.population.remove(position);
        } else if (furniture.containsKey(position)) {
            this.furniture.remove(position);
        }
    }

    @Override
    public List<Position> getPerimeter() {
        return List.copyOf(this.perimeter);
    }

    @Override
    public List<Position> getInnerCells() {
        return List.copyOf(this.innerCells);
    }

    @Override
    public boolean isPositionValid(final Position position) {
        return position.getX() >= 0 && position.getX() < this.dimension.getWidth()
            && position.getY() >= 0 && position.getY() < this.dimension.getHeight();
    }

    /* public boolean canExit() {
        return this.player.getPosition().equals(this.exit);
    } */

    @Override
    public Position getExit() {
        return new PositionImpl(this.exit.getX(), this.exit.getY());
    }

    @Override
    public Position getEntry() {
        return new PositionImpl(this.entry.getX(), this.entry.getY());
    }

    private Set<Position> adjacentCells(final Position position, final Entity entity) {
        final int rangeX = entity instanceof FurnitureItem ? EXCLUSION_RANGE : CHARACTERS_EXCLUSION_RANGE;
        final int rangeY = EXCLUSION_RANGE;

        return IntStream.rangeClosed(position.getX() - rangeX, position.getX() + rangeX)
        .boxed().flatMap(x -> IntStream.rangeClosed(position.getY() - rangeY, position.getY() + rangeY)
        .mapToObj(y -> new PositionImpl(x, y))).filter(this::isPositionValid).collect(Collectors.toSet());
    }

    private List<Position> perimeterPositions() {
        final List<Position> perimeter = new ArrayList<>();

        for (int x = 0; x < this.dimension.getWidth(); x++) {
            perimeter.add(new PositionImpl(x, 0));
            perimeter.add(new PositionImpl(x, this.dimension.getHeight() - 1));
        }
        for (int y = 1; y < this.dimension.getHeight() - 1; y++) {
            perimeter.add(new PositionImpl(this.dimension.getWidth() - 1, y));
            perimeter.add(new PositionImpl(0, y));
        }

        return perimeter;
    }

    private List<Position> innerCells() {
        final List<Position> innerCells = new ArrayList<>();

        for (int x = BORDER; x < this.dimension.getWidth() - BORDER; x++) {
            for (int y = BORDER; y < this.dimension.getHeight() - BORDER; y++) {
                innerCells.add(new PositionImpl(x, y));
            }
        }

        return innerCells;
    }

}
