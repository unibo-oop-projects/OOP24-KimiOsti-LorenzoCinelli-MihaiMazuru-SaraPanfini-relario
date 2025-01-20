package it.unibo.oop.relario.model.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.oop.relario.model.Interactions;
import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
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

    private final MainCharacter player;
    private final Dimension dimension;
    private final Map<Position, CellState> cellStates = new HashMap<>();
    private final Map<Position, LivingBeing> population = new HashMap<>();
    private final Map<Position, Furniture> furniture = new HashMap<>();
    private final Position entry;
    private final Position exit;
    private Optional<Quest> quest = Optional.empty();

    /**
     * Constructs a room with the specified dimension and main character.
     * The room has an entry and an exit and the player is placed at the entry.
     * @param dimension of the room
     * @param player that is placed in the room
     * @param entry of the room
     * @param exit of the room
     */
    public RoomImpl(final MainCharacter player, final Dimension dimension, final Position entry, 
    final Position exit) {
        this.player = player;
        this.dimension = dimension;
        this.entry = new PositionImpl(entry.getX(), entry.getY());
        this.exit = new PositionImpl(exit.getX(), exit.getY());
        initializeRoom();
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
    public Optional<Quest> getQuest() {
        return this.quest;
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
    public Map<Position, Furniture> getFurniture() {
        return Map.copyOf(this.furniture);
    }

    @Override
    public Optional<Entity> getCellContent(final Position position) {
        return this.population.containsKey(position) ? Optional.of(this.population.get(position))
        : this.furniture.containsKey(position) ? Optional.of(this.furniture.get(position)) : Optional.empty();
    }

    @Override
    public void setQuest(final Optional<Quest> quest) {
        this.quest = quest;
    }

    @Override
    public boolean isCellAvailable(final Position position) {
        return this.cellStates.get(position).equals(CellState.PERIMETER_EMPTY) || 
        this.cellStates.get(position).equals(CellState.INNER_EMPTY);
    }

    @Override
    public void addEntity(final Position position, final Entity entity) {
        if (entity instanceof LivingBeing) {
            this.population.put(position, (LivingBeing) entity);
        } else if (entity instanceof Furniture) {
            addFurniture(position, (Furniture) entity);
        }
    }

    @Override
    public void removeEnemy(final Position position) {
        if (population.containsKey(position) && population.get(position) instanceof Enemy) {
            this.population.remove(position);
        }
    }

    @Override
    public boolean isPositionValid(final Position position) {
        return position.getX() >= 0 && position.getX() < this.dimension.getWidth()
            && position.getY() >= 0 && position.getY() < this.dimension.getHeight();
    }

    @Override
    public Position getExit() {
        return new PositionImpl(this.exit.getX(), this.exit.getY());
    }

    @Override
    public Position getEntry() {
        return new PositionImpl(this.entry.getX(), this.entry.getY());
    }

    @Override
    public void update() {
        if (!Interactions.canMove(this.player.getPosition().get(), this.player.getDirection(), 
        this.dimension, this.population, this.furniture)) {
            this.player.stop();
        }
        this.player.update();

        for (LivingBeing chara : this.population.values()) {
            chara.update();
        }
    }

    @Override
    public List<Position> getCellsByState(final CellState state) {
        return this.cellStates.entrySet().stream().filter(entry -> entry.getValue().equals(state))
        .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private void addFurniture(final Position position, final Furniture furniture) {
        this.furniture.put(position, furniture);
        this.cellStates.put(position, CellState.OCCUPIED);
        for (Position pos : adjacentCells(position, furniture)) {
            this.cellStates.put(pos, CellState.RESTRICTED);
        }
    }

    private Set<Position> adjacentCells(final Position position, final Entity entity) {
        return IntStream.rangeClosed(position.getX() - EXCLUSION_RANGE, position.getX() + EXCLUSION_RANGE)
        .boxed().flatMap(x -> IntStream.rangeClosed(position.getY() - EXCLUSION_RANGE, position.getY() + EXCLUSION_RANGE)
        .mapToObj(y -> new PositionImpl(x, y))).filter(p -> isPositionValid(p) && isCellAvailable(p))
        .collect(Collectors.toSet());
    }

    private void initializeRoom() {
        this.player.setPosition(entry);
        this.cellStates.putAll(IntStream.range(0, this.dimension.getWidth())
        .boxed().flatMap(x -> IntStream.range(0, this.dimension.getHeight())
        .mapToObj(y -> new PositionImpl(x, y))).collect(Collectors.toMap(pos -> pos,
        pos -> isPerimeter(pos.getX(), pos.getY()) ? CellState.PERIMETER_EMPTY :
        (isInnerPerimeter(pos.getX(), pos.getY()) ? CellState.RESTRICTED : CellState.INNER_EMPTY))));
        this.cellStates.put(this.entry, CellState.RESTRICTED);
        this.cellStates.put(new PositionImpl(this.entry.getX(), this.entry.getY() - 1), CellState.RESTRICTED);
        this.cellStates.put(new PositionImpl(this.entry.getX(), this.entry.getY() + 1), CellState.RESTRICTED);
    }

    private boolean isPerimeter(final int x, final int y) {
        return x == 0 || y == 0 || x == this.dimension.getWidth() - 1 || y == this.dimension.getHeight() - 1;
    }

    private boolean isInnerPerimeter(final int x, final int y) {
        return x == 1 || y == 1 || x == this.dimension.getWidth() - 2 || y == this.dimension.getHeight() - 2;
    }

}
