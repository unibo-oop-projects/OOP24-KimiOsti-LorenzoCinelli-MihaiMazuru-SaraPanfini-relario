package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.impl.GameLoop;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.Event;
import it.unibo.oop.relario.utils.impl.GameState;

/**
 * The test class for the game controller.
 */
final class GameControllerTest {

    private MainController controller;

    /**
     * Initialises the main controller used in the tests.
     */
    @BeforeEach
    void init() {
        this.controller = new MainControllerImpl();
    }

    /**
     * Tests the game loop.
     */
    @Test
    void testGameLoop() {
        final var loop = new GameLoop(new MainControllerImpl());
        loop.start();
        assertFalse(loop.isInterrupted());
        loop.interrupt();
        assertTrue(loop.isInterrupted());
    }

    /**
     * Test the game controller on user input scenarios.
     */
    @Test
    void testNotify() {
        this.controller.moveToNextRoom();
        this.controller.getMainView().showPanel(GameState.GAME);

        assertTrue(this.controller.getCurRoom().isPresent());

        final var gameController = this.controller.getGameController();
        gameController.run(this.controller.getCurRoom().isPresent());
        gameController.notify(Event.MOVE_RIGHT);
        assertEquals(Direction.RIGHT, this.controller.getCurRoom().get().getPlayer().getDirection());
        gameController.notify(Event.MOVE_UP);
        assertEquals(Direction.UP, this.controller.getCurRoom().get().getPlayer().getDirection());
        gameController.notify(Event.MOVE_DOWN);
        assertEquals(Direction.DOWN, this.controller.getCurRoom().get().getPlayer().getDirection());
        gameController.notify(Event.MOVE_LEFT);
        assertEquals(Direction.LEFT, this.controller.getCurRoom().get().getPlayer().getDirection());
        gameController.notify(Event.RELEASED);
        final var pos = this.controller.getCurRoom().get().getPlayer().getPosition();
        this.controller.getCurRoom().get().update();
        assertEquals(pos, this.controller.getCurRoom().get().getPlayer().getPosition());

        gameController.notify(Event.ESCAPE);
        this.controller.getMainView().showPanel(GameState.GAME);
        gameController.run(true);

    }

}
