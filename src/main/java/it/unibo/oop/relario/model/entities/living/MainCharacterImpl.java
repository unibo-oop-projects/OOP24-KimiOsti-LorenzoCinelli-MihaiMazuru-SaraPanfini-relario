package it.unibo.oop.relario.model.entities.living;

import it.unibo.oop.relario.model.inventory.Inventory;
import it.unibo.oop.relario.model.inventory.InventoryImpl;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Implementation for the main character.
 */
public final class MainCharacterImpl implements MainCharacter {

    private int life;
    private final int atk;
    private final Inventory inventory;
    private Position position;
    private boolean moving;
    private Direction direction;

    /**
     * Initialises the main character.
     * @param initialPosition the initial player position.
     */
    public MainCharacterImpl(final Position initialPosition) {
        this.life = Constants.DEFAULT_PLAYER_LIFE;
        this.atk = Constants.DEFAULT_PLAYER_ATK;
        this.inventory = new InventoryImpl();
        this.position = new PositionImpl(initialPosition.getX(), initialPosition.getY());
    }

    @Override
    public Position getPosition() {
        return new PositionImpl(this.position.getX(), this.position.getY());
    }

    @Override
    public void setPosition(final Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public boolean isMoving() {
        return this.moving;
    }

    @Override
    public void setMovement(final Direction direction) {
        moving = true;
        this.direction = direction;
    }

    @Override
    public void stop() {
        moving = false;
    }

    @Override
    public void update() {
        if (moving) {
            this.position = direction.move(position);
        }
    }

    @Override
    public Inventory handleInventory() {
        return null;
    }

    @Override
    public boolean attacked(final int damage) {
        if (damage >= life) {
            life = 0;
            return false;
        } else {
            life = life - damage;
            return true;
        }
    }

    @Override
    public int getAttack() {
        return this.atk;
    }
}
