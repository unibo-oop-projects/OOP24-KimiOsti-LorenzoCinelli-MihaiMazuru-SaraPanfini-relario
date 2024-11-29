package it.unibo.oop.relario.model.entities.npc;

import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;

public abstract class NpcImpl implements Npc {
    
    public static final int DIRECTION_RANGE = 2;

    private final String name;
    private Position position;
    private Direction direction;
    private int counter;

    public NpcImpl(final String name, final Position position) {
        this.name = name;
        this.position = position;
        this.direction = Direction.RIGHT;
        this.counter = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update() {
        counter++;
        if (counter > DIRECTION_RANGE) {
            changeDirection();
            counter = 1;
        } 
        setPosition(this.direction.move(position));
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    private void setPosition(final Position position) {
        this.position = position;
    }

    private void changeDirection() {
        this.direction = this.direction.equals(Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT;
    }
    
}
