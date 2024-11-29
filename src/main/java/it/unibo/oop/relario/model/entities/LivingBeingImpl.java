package it.unibo.oop.relario.model.entities;

import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * This class represents all living beings of the game and their common properties.
*/

public abstract class LivingBeingImpl implements LivingBeing {

    /** The number of updates after which the character changes direction. */
    public static final int DIRECTION_RANGE = 2;

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
    }

    @Override
    public final Position getPosition() {
        return new PositionImpl(this.position.getX(), this.position.getY());
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final void update() {
        counter++;
        if (counter > DIRECTION_RANGE) {
            changeDirection();
            counter = 1;
        } 
        setPosition(this.direction.move(position));
    }

    private void setPosition(final Position position) {
        this.position = position;
    }

    private void changeDirection() {
        this.direction = this.direction.equals(Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT;
    }

}
