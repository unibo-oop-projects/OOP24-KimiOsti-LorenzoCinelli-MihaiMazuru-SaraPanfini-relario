package it.unibo.oop.relario.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Test class for {@link LivingBeing}.
 */
class LivingBeingTest {

    private static final String TEST_NAME = "Napstablook";
    private static final Direction TEST_DIRECTION = Direction.DOWN;

    private final SecureRandom randomGenerator;

    LivingBeingTest() {
        this.randomGenerator = new SecureRandom();
    }

    @Test
    void testImplementation() {
        final var testX = this.randomGenerator.nextInt();
        final var testY = this.randomGenerator.nextInt();
        final LivingBeingImpl living = new Dummy(
            TEST_NAME,
            new PositionImpl(testX, testY)
        );
        var pos = this.testInterface(living, testX, testY);

        living.setDirection(TEST_DIRECTION);
        this.moveLivingBeing(living);
        assertTrue(living.getPosition().isPresent());
        assertEquals(TEST_DIRECTION.move(pos), living.getPosition().get());

        assertTrue(living.getPosition().isPresent());
        pos = living.getPosition().get();
        living.setMoving(false);
        for (int i = 0; i < 1000; i++) {
            living.update();
        }
        assertTrue(living.getPosition().isPresent());
        assertEquals(pos, living.getPosition().get());
    }

    private Position testInterface(final LivingBeing living, final int testX, final int testY) {
        assertEquals(TEST_NAME, living.getName());
        assertTrue(living.getPosition().isPresent());
        assertEquals(new PositionImpl(testX, testY), living.getPosition().get());
        this.moveLivingBeing(living);
        assertTrue(living.getPosition().isPresent());
        assertEquals(
            Direction.RIGHT.move(new PositionImpl(testX, testY)),
            living.getPosition().get()
        );

        while (living.getDirection().equals(Direction.RIGHT)) {
            living.update();
        }
        assertNotEquals(Direction.RIGHT, living.getDirection());
        assertTrue(living.getPosition().isPresent());
        final var pos = living.getPosition().get();
        this.moveLivingBeing(living);
        assertTrue(living.getPosition().isPresent());
        assertEquals(living.getDirection().move(pos), living.getPosition().get());

        return living.getPosition().get();
    }

    private void moveLivingBeing(final LivingBeing livingBeing) {
        if (livingBeing.getPosition().isPresent()) {
            final var position = livingBeing.getPosition().get();
            do {
                livingBeing.update();
                assertTrue(livingBeing.getPosition().isPresent());
            } while (position.equals(livingBeing.getPosition().get()));
        }
    }

    private static final class Dummy extends LivingBeingImpl {
        Dummy(final String name, final Position position) {
            super(name, position);
        }

        Dummy() {
            super(TEST_NAME, new PositionImpl(0, 0));
        }
    }
}
