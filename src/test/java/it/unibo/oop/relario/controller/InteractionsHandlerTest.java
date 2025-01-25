package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.impl.InteractionsHandlerImpl;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureFactoryImpl;
import it.unibo.oop.relario.model.entities.living.MainCharacterImpl;
import it.unibo.oop.relario.model.entities.npc.NpcFactoryImpl;
import it.unibo.oop.relario.model.map.RoomImpl;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.utils.impl.PositionImpl;
import it.unibo.oop.relario.view.impl.GameView;

/**
 * The test class for the game's interaction handler.
 */
final class InteractionsHandlerTest {

    private MainController controller;

    @BeforeEach
    void init() {
        controller = new MainControllerImpl();
    }

    /**
     * A method to test the interaction handling motor.
     */
    @Test
    void testInteractionScenarios() {
        this.controller.moveToNextRoom();
        assertTrue(this.controller.getCurRoom().isPresent());

        this.controller.getGameController().run(true);

        this.controller.getMainView().showPanel(GameState.GAME);
        final var handler = new InteractionsHandlerImpl(
            this.controller,
            (GameView) this.controller.getMainView().getPanel(GameState.GAME)
        );

        final var room = new RoomImpl(
            new MainCharacterImpl(),
            new DimensionImpl(10, 10),
            new PositionImpl(0, 0),
            new PositionImpl(9, 9)
        );
        final var interactiveNpcPos = new PositionImpl(1, 1);
        final var npcPos = new PositionImpl(2, 2);
        final var interactiveFurniturePos = new PositionImpl(3, 3);
        final var walkableFurniturePos = new PositionImpl(4, 4);

        final var npcFactory = new NpcFactoryImpl();
        final var furnitureFactory = new FurnitureFactoryImpl();

        room.addEntity(interactiveNpcPos, npcFactory.createInteractiveNpc(interactiveNpcPos));
        room.addEntity(npcPos, npcFactory.createDefaultNpc(npcPos));
        room.addEntity(
            interactiveFurniturePos,
            furnitureFactory.createRandomInteractiveFurniture(interactiveFurniturePos)
        );
        room.addEntity(
            walkableFurniturePos,
            furnitureFactory.createRandomWalkableFurniture(walkableFurniturePos)
        );

        room.getPlayer().setPosition(new PositionImpl(0, 1));
        room.getPlayer().setMovement(Direction.RIGHT);
        handler.handleInteraction(room);
        assertFalse(room.getPlayer().getItems().isEmpty());
        assertTrue(room.getPlayer().discardItem(room.getPlayer().getItems().get(0)));

        room.getPlayer().setPosition(new PositionImpl(1, 2));
        handler.handleInteraction(room);
        assertTrue(room.getPlayer().getItems().isEmpty());

        room.getPlayer().setPosition(new PositionImpl(2, 3));
        handler.handleInteraction(room);
        assertFalse(room.getPlayer().getItems().isEmpty());
        assertTrue(room.getPlayer().useItem(room.getPlayer().getItems().get(0)));

        room.getPlayer().setPosition(new PositionImpl(3, 4));
        handler.handleInteraction(room);
        assertTrue(room.getPlayer().getItems().isEmpty());

    }
}
