package it.unibo.oop.relario.model.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
@SuppressFBWarnings(
    value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
    justification = "The room serves as an entry point for the model,"
        + "and therefore stores a reference to the player and offers a getter to access it."
)
public final class RoomImpl implements Room {

    private static final int FURNITURE_EXCLUSION_RANGE = 2;

    private final MainCharacter player;
    private final Dimension dimension;
    private final Map<Position, CellState> cellStates = new HashMap<>();
    private Map<Position, LivingBeing> population = new HashMap<>();
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
        return this.cellStates.get(position).equals(CellState.PERIMETER_EMPTY)
        || this.cellStates.get(position).equals(CellState.INNER_EMPTY);
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
        final Map<Position, LivingBeing> buffer = new HashMap<>(this.population);
        // final Map<Position, LivingBeing> temp = new HashMap<>(this.population);
        if (!Interactions.canMove(this.player.getPosition().get(), this.player.getDirection(), 
        this.dimension, this.population, this.furniture)) {
            this.player.stop();
        }
        this.player.update();
        buffer.put(this.player.getPosition().get(), player);
        for (final LivingBeing chara : this.population.values()) {
            if (Interactions.canMove(chara.getPosition().get(), chara.getDirection(), 
            this.dimension, buffer, this.furniture)) {
                buffer.remove(chara.getPosition().get());
                chara.update();
                buffer.put(chara.getPosition().get(), chara);
            }
        }
        buffer.remove(this.player.getPosition().get());
        this.population = buffer;
    }

    @Override
    public List<Position> getCellsByState(final CellState state) {
        return this.cellStates.entrySet().stream().filter(entry -> entry.getValue().equals(state))
        .map(Entry::getKey).collect(Collectors.toList());
    }

    private void addFurniture(final Position position, final Furniture furniture) {
        this.furniture.put(position, furniture);
        this.cellStates.put(position, CellState.OCCUPIED);
        for (final Position pos : adjacentCells(position)) {
            this.cellStates.put(pos, CellState.RESTRICTED);
        }
    }

    private Set<Position> adjacentCells(final Position position) {
        return IntStream.rangeClosed(position.getX() - FURNITURE_EXCLUSION_RANGE, position.getX() + FURNITURE_EXCLUSION_RANGE)
        .boxed()
        .flatMap(x ->
            IntStream.rangeClosed(position.getY() - FURNITURE_EXCLUSION_RANGE, position.getY() + FURNITURE_EXCLUSION_RANGE
        )
        .mapToObj(y -> new PositionImpl(x, y))).filter(p -> isPositionValid(p) && isCellAvailable(p))
        .collect(Collectors.toSet());
    }

    private void initializeRoom() {
        this.player.setPosition(entry);
        this.cellStates.putAll(IntStream.range(0, this.dimension.getWidth())
        .boxed().flatMap(x -> IntStream.range(0, this.dimension.getHeight())
        .mapToObj(y -> new PositionImpl(x, y))).collect(Collectors.toMap(pos -> pos,
        pos -> isPerimeter(pos.getX(), pos.getY()) ? CellState.PERIMETER_EMPTY
        : isInnerPerimeter(pos.getX(), pos.getY()) || isCorner(pos) 
        ? CellState.RESTRICTED : CellState.INNER_EMPTY)));
        this.cellStates.put(this.entry, CellState.RESTRICTED);
        this.cellStates.put(new PositionImpl(this.entry.getX(), this.entry.getY() - 1), CellState.RESTRICTED);
        this.cellStates.put(new PositionImpl(this.entry.getX(), this.entry.getY() + 1), CellState.RESTRICTED);
        this.cellStates.put(new PositionImpl(this.entry.getX(), this.entry.getY() - 2), CellState.RESTRICTED);
        this.cellStates.put(new PositionImpl(this.entry.getX(), this.entry.getY() + 2), CellState.RESTRICTED);
    }

    private boolean isCorner(final Position position) {
        final Position topLeft = new PositionImpl(0, 0);
        final Position topRight = new PositionImpl(this.dimension.getWidth() - 1, 0);
        final Position bottomLeft = new PositionImpl(0, this.dimension.getHeight() - 1);
        final Position bottomRight = new PositionImpl(this.dimension.getWidth() - 1, this.dimension.getHeight() - 1);
        return position.equals(topLeft) || position.equals(topRight) || position.equals(bottomLeft) 
        || position.equals(bottomRight) || isAdjacent(position, topLeft) || isAdjacent(position, topRight)
        || isAdjacent(position, bottomLeft) || isAdjacent(position, bottomRight);
    }

    private boolean isAdjacent(final Position pos1, final Position pos2) {
        final int dx = Math.abs(pos1.getX() - pos2.getX());
        final int dy = Math.abs(pos1.getX() - pos2.getX());
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

    private boolean isPerimeter(final int x, final int y) {
        return x == 0 || y == 0 || x == this.dimension.getWidth() - 1 || y == this.dimension.getHeight() - 1;
    }

    private boolean isInnerPerimeter(final int x, final int y) {
        return x == 1 || y == 1 || x == this.dimension.getWidth() - 2 || y == this.dimension.getHeight() - 2;
    }

}
