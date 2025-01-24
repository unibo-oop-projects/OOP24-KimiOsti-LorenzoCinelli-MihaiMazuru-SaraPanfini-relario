package it.unibo.oop.relario.model.entities.living;

import java.util.Optional;

import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Implementation for {@link EntityMovementHandler}.
 */
public final class EntityMovementHandlerImpl implements EntityMovementHandler {

    private Optional<Position> position;
    private Direction direction;
    private boolean moving;

    /**
     * Instantiates a new entity movement handler.
     */
    public EntityMovementHandlerImpl() {
        this.setPosition(null);
    }

    @Override
    public Optional<Position> getPosition() {
        return this.position.isPresent() ? Optional.of(this.position.get()) : Optional.empty();
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setPosition(final Position newPos) {
        this.position = newPos == null ? Optional.empty() : Optional.of(
            new PositionImpl(newPos.getX(), newPos.getY())
        );
        this.direction = Constants.ENTITY_INITIAL_DIRECTION;
        this.moving = false;
    }

    @Override
    public void setMovement(final Direction newDir) {
        this.direction = newDir;
        this.moving = true;
    }

    @Override
    public void move() {
        if (this.position.isPresent() && this.moving) {
            this.position = Optional.of(this.direction.move(this.position.get()));
        }
    }

    @Override
    public void stop() {
        this.moving = false;
    }

}
