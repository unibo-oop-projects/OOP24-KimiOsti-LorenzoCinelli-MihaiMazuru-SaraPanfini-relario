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
import it.unibo.oop.relario.model.entities.enemies.EnemyImpl;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureItem;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

public class RoomImpl implements Room {

    public static final int EXCLUSION_RANGE = 2;
    public static final int ENEMIES_EXCLUSION_RANGE = EnemyImpl.DIRECTION_RANGE + EXCLUSION_RANGE;
    public static final int BORDER = 2;

    private final Dimension dimension;
    private Map<Position, LivingBeing> population = new HashMap<>();
    private Map<Position, FurnitureItem> furniture = new HashMap<>();
    private final MainCharacter player;
    private final Position entry;
    private final Position exit;
    private Set<Position> unavailableCells = new HashSet<>();
    private final List<Position> perimeter;
    private final List<Position> innerCells;
    // private final Optional<Quest> quest;

    public RoomImpl(final Dimension dimension, final MainCharacter player) {
        this.dimension = dimension;
        this.player = player;
        this.entry = new PositionImpl(0, this.dimension.getHeight() / 2);
        this.exit = new PositionImpl(this.dimension.getWidth() - 1, this.dimension.getHeight() / 2);
        this.player.setPosition(entry);
        this.unavailableCells.add(this.entry);
        this.unavailableCells.add(this.exit);
        this.perimeter = perimeterPositions();
        this.innerCells = innerCells();
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    @Override
    public Map<Position, LivingBeing> getPopulation() {
        return this.population;
    }

    @Override
    public Map<Position, FurnitureItem> getFurniture() {
        return this.furniture;
    }

    @Override
    public Optional<Entity> getCellContent(Position position) {
        return this.population.containsKey(position) ? Optional.of(this.population.get(position))
        : (this.furniture.containsKey(position) ? Optional.of(this.furniture.get(position)) : Optional.empty());
    }

    @Override
    public boolean isCellAvailable(Position position) {
        return !unavailableCells.contains(position);
    }

    @Override
    public void addFurniture(Position position, FurnitureItem furniture) {
        this.furniture.put(position, furniture);
        this.unavailableCells.addAll(adjacentCells(position, furniture));
    }

    @Override
    public void addCharacter(Position position, LivingBeing character) {
        this.population.put(position, character);
        this.unavailableCells.addAll(adjacentCells(position, character));
    }

    @Override
    public void removeEntity(Position position) {
        if (population.containsKey(position)) {
            this.population.remove(position);
        } else if (furniture.containsKey(position)) {
            this.furniture.remove(position);
        }
    }

    public List<Position> getPerimeter() {
        return this.perimeter;
    }

    public List<Position> getInnerCells() {
        return this.innerCells;
    }

    public boolean isPositionValid(final Position position) {
        return position.getX() >= 0 && position.getX() < this.dimension.getWidth()
            && position.getY() >= 0 && position.getY() < this.dimension.getHeight();
    }

    /* public boolean canExit() {
        return this.player.getPosition().equals(this.exit);
    } */

    public Position getExit() {
        return this.exit;
    }
    
    private Set<Position> adjacentCells(Position position, Entity entity) {
        int rangeX = entity instanceof FurnitureItem ? EXCLUSION_RANGE : ENEMIES_EXCLUSION_RANGE;
        int rangeY = EXCLUSION_RANGE;

        return IntStream.rangeClosed(position.getX() - rangeX, position.getX() + rangeX)
        .boxed().flatMap(x -> IntStream.rangeClosed(position.getY() - rangeY, position.getY() + rangeY)
        .mapToObj(y -> new PositionImpl(x, y))).filter(p -> isPositionValid(p)).collect(Collectors.toSet());
    }

    private List<Position> perimeterPositions() {
        List<Position> perimeter = new ArrayList<>();

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
        List<Position> innerCells = new ArrayList<>();

        for (int x = BORDER; x < this.dimension.getWidth() - BORDER; x++) {
            for (int y = BORDER; y < this.dimension.getHeight() - BORDER; y++) {
                innerCells.add(new PositionImpl(x, y));
            }
        }

        return innerCells;
    }

}
