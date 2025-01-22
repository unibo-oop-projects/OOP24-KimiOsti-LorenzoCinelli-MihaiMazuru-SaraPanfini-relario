package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.impl.GameLoop;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.model.entities.living.MainCharacterImpl;
import it.unibo.oop.relario.model.map.RoomImpl;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.utils.impl.PositionImpl;
import it.unibo.oop.relario.view.impl.GameView;
import it.unibo.oop.relario.view.impl.MainViewImpl;

/**
 * The test class for the game controller.
 */
public class GameControllerTest {

    /**
     * Tests the game loop.
     */
    @Test
    public void testGameLoop() {
        final var controller = new MainControllerImpl();
        final var view = new MainViewImpl(controller);
        view.panelsSetup();
        final var loop = new GameLoop((GameView) view.getPanel(GameState.GAME), new RoomImpl(
            new MainCharacterImpl(),
            new DimensionImpl(5, 5),
            new PositionImpl(0, 0),
            new PositionImpl(5, 5)
        ));
        loop.start();
        assertFalse(loop.isInterrupted());
        loop.interrupt();
        assertTrue(loop.isInterrupted());
    }

    /**
     * Test the event handling of the game controller.
     */
    @Test
    public void testNotify() {

    }

}
