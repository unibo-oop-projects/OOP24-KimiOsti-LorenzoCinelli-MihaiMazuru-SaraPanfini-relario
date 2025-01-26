package it.unibo.oop.relario.model.entities;

import java.util.Optional;

import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * This class represents all living beings of the game and their common properties.
*/

public abstract class LivingBeingImpl implements LivingBeing {

    /** The number of updates after which the character changes direction. */
    public static final int DIRECTION_RANGE = 2;

    private static final int UPDATE_FREQUENCY = 8;

    private boolean moving;
    private final String name;
    private Position position;
    private Direction direction;
    private int counter;

    /**
     * Constructs a new instance of living being.
     * @param name of the living being
     * @param position where the living being has to be set
     */
    public LivingBeingImpl(final String name, final Position position) {
        this.name = name;
        this.position = new PositionImpl(position.getX(), position.getY());
        this.direction = Direction.RIGHT;
        this.counter = 0;
        this.moving = true;
    }

    @Override
    public final Optional<Position> getPosition() {
        return Optional.of(new PositionImpl(this.position.getX(), this.position.getY()));
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final void update() {
        if (moving) {
            counter++;
            if (counter % UPDATE_FREQUENCY == 0) {
                setPosition(this.direction.move(position));
            }
            if (counter >= DIRECTION_RANGE * UPDATE_FREQUENCY) {
                changeDirection();
                counter = 0;
            }
        }
    }

    @Override
    public final Direction getDirection() {
        return this.direction;
    }

    /**
     * Sets the moving status of the living being.
     * @param moving true if the entity is moving, false otherwise
     */
    public void setMoving(final boolean moving) {
        this.moving = moving;
    }

    /**
     * Sets the direction of the living being.
     * @param direction the direction that has to be set
     */
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    private void changeDirection() {
        this.direction = this.direction.equals(Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT;
    }

    private void setPosition(final Position position) {
        this.position = position;
    }

}
