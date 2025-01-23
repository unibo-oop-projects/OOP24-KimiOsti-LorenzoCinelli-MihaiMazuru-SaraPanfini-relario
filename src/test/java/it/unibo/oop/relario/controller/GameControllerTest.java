package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
     * Test the game controller on user input scenarios.
     */
    @Test
    public void testNotify() {
        final var controller = new MainControllerImpl();
        controller.moveToNextRoom();
        controller.getCutSceneController().show(GameState.GAME);

        assertTrue(controller.getCurRoom().isPresent());

        final var gameController = controller.getGameController();
        gameController.notify(Event.MOVE_RIGHT);
        assertEquals(Direction.RIGHT, controller.getCurRoom().get().getPlayer().getDirection());
        gameController.notify(Event.MOVE_UP);
        assertEquals(Direction.UP, controller.getCurRoom().get().getPlayer().getDirection());
        gameController.notify(Event.MOVE_DOWN);
        assertEquals(Direction.DOWN, controller.getCurRoom().get().getPlayer().getDirection());
        gameController.notify(Event.MOVE_LEFT);
        assertEquals(Direction.LEFT, controller.getCurRoom().get().getPlayer().getDirection());
        gameController.notify(Event.RELEASED);
        assertFalse(controller.getCurRoom().get().getPlayer().isMoving());
    }

}
