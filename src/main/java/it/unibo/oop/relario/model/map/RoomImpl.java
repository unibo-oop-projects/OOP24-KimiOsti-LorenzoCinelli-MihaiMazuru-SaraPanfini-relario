package it.unibo.oop.relario.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureItem;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

public class RoomImpl implements Room {

    private final Dimension dimension;
    private Map<Position, LivingBeing> population = new HashMap<>();
    private Map<Position, FurnitureItem> furniture = new HashMap<>();
    private final MainCharacter player;
    private final Position entry;
    private final Position exit;
    // private final Optional<Quest> quest;

    public RoomImpl(final Dimension dimension, final MainCharacter player) {
        this.dimension = dimension;
        this.player = player;
        this.entry = new PositionImpl(0, this.dimension.getHeight() / 2);
        this.exit = new PositionImpl(this.dimension.getWidth() - 1, this.dimension.getHeight() / 2);
        this.player.setPosition(entry);
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
        return !this.population.containsKey(position) && !this.furniture.containsKey(position);
    }

    @Override
    public boolean addFurniture(Position position, FurnitureItem furniture) {
        if (isPositionValid(position) && isCellAvailable(position)) {
            this.furniture.put(position, furniture);
            return true;
        }
        return false;
    }

    @Override
    public boolean addCharacter(Position position, LivingBeing character) {
        if (isPositionValid(position) && isCellAvailable(position)) {
            this.population.put(position, character);
            return true;
        }
        return false;
    }

    @Override
    public void removeEntity(Position position) {
        if (population.containsKey(position)) {
            this.population.remove(position);
        } else if (furniture.containsKey(position)) {
            this.furniture.remove(position);
        }
    }

    @Override
    public Room getNextRoom() {
        return new RoomImpl(dimension, player);
    }

    private boolean isPositionValid(final Position position) {
        return position.getX() >= 0 && position.getX() < this.dimension.getWidth()
            && position.getY() >= 0 && position.getY() < this.dimension.getHeight();
    }
    
}
