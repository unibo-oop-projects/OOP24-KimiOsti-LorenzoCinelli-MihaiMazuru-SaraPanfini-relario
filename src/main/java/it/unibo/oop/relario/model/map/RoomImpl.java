package it.unibo.oop.relario.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.oop.relario.model.Interactions;
import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.LivingBeingImpl;
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

    /** The range around living beings, used to define restricted areas. */
    public static final int CHARACTERS_EXCLUSION_RANGE = LivingBeingImpl.DIRECTION_RANGE + EXCLUSION_RANGE;

    /** The border value used to define the edges of the room. */
    public static final int BORDER = 2;

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
            addCharacter(position, (LivingBeing) entity);
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

    private void addFurniture(final Position position, final Furniture furniture) {
        Set<Position> adjacentCells = adjacentCells(position, furniture);
        this.furniture.put(position, furniture);
        this.cellStates.put(position, CellState.OCCUPIED);
        for (Position pos : adjacentCells) {
            this.cellStates.put(pos, CellState.RESTRICTED);
        }
    }

    private void addCharacter(final Position position, final LivingBeing character) {
        Set<Position> adjacentCells = adjacentCells(position, character);
        this.population.put(position, character);
        this.cellStates.put(position, CellState.OCCUPIED);
        for (Position pos : adjacentCells) {
            this.cellStates.put(pos, CellState.RESTRICTED);
        }
    }

    private Set<Position> adjacentCells(final Position position, final Entity entity) {
        final int rangeX = entity instanceof Furniture ? EXCLUSION_RANGE : CHARACTERS_EXCLUSION_RANGE;
        final int rangeY = EXCLUSION_RANGE;

        return IntStream.rangeClosed(position.getX() - rangeX, position.getX() + rangeX)
        .boxed().flatMap(x -> IntStream.rangeClosed(position.getY() - rangeY, position.getY() + rangeY)
        .mapToObj(y -> new PositionImpl(x, y))).filter(p -> isPositionValid(p) && p.equals(position))
        .collect(Collectors.toSet());
    }

    private void initializeRoom() {
        this.player.setPosition(entry);
        this.cellStates.putAll(IntStream.range(0, this.dimension.getWidth())
        .boxed().flatMap(x -> IntStream.range(0, this.dimension.getHeight())
        .mapToObj(y -> new PositionImpl(x, y))).collect(Collectors.toMap(pos -> pos,
        pos -> isPerimeter(pos.getX(), pos.getY()) ? CellState.PERIMETER_EMPTY : CellState.INNER_EMPTY)));
        this.cellStates.put(this.entry, CellState.RESTRICTED);
    }

    private boolean isPerimeter(final int x, final int y) {
        return x == 0 || y == 0 || x == this.dimension.getWidth() - 1 || y == this.dimension.getHeight() - 1;
    }

}
