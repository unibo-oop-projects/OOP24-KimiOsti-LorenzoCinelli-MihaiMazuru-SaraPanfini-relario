package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.impl.GameLoop;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.model.entities.living.MainCharacterImpl;
import it.unibo.oop.relario.model.map.RoomImpl;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.Event;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.utils.impl.PositionImpl;
import it.unibo.oop.relario.view.impl.GameView;

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
        final var loop = new GameLoop((GameView) this.controller.getMainView().getPanel(GameState.GAME), new RoomImpl(
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
