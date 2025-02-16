package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.Timer;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.InteractionsHandler;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.impl.CombatAction;
import it.unibo.oop.relario.controller.impl.InteractionsHandlerImpl;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.furniture.api.InteractiveFurniture;
import it.unibo.oop.relario.model.entities.furniture.api.WalkableFurniture;
import it.unibo.oop.relario.model.entities.npc.Npc;
import it.unibo.oop.relario.model.map.Room;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.Event;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.utils.impl.Pair;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * The test class for the game's interaction handler.
 */
final class InteractionsHandlerTest {

    private static final long ROOM_TRANSITION_SLEEP_TIME = 4000;
    private static final int DELAY_TRANSITION = 4000;

    private final MainController controller;
    private final InteractionsHandler handler;
    private Pair<Position, Npc> itemNpc;
    private Pair<Position, Enemy> enemy;
    private Pair<Position, InteractiveFurniture> itemFurniture;
    private Pair<Position, WalkableFurniture> enemyFurniture;
    private Pair<Position, WalkableFurniture> emptyWalkableFurniture;

    InteractionsHandlerTest() {
        controller = new MainControllerImpl();
        this.controller.moveToNextRoom();
        assertTrue(this.controller.getCurRoom().isPresent());
        this.controller.getGameController().run(this.controller.getCurRoom().isPresent());
        this.controller.getMainView().showPanel(GameState.GAME);
        this.handler = new InteractionsHandlerImpl(controller);
        this.initValues(controller.getCurRoom().get());
    }

    /**
     * A method to test the interaction handling motor.
     */
    @Test
    void testInteractionScenarios() {
        this.controller.getCurRoom().get().getPlayer().setMovement(Direction.RIGHT);
        this.controller.getCurRoom().get().getPlayer().stop();

        if (this.itemNpc != null) {
            this.controller.getCurRoom().get().getPlayer().setPosition(
                new PositionImpl(this.itemNpc.getX().getX() - 1, this.itemNpc.getX().getY())
            );
            assertTrue(this.itemNpc.getY().interact().getLoot().isPresent());
            this.handler.handleInteraction(this.controller.getCurRoom().get());
            assertFalse(this.controller.getCurRoom().get().getPlayer().getItems().isEmpty());
            assertTrue(this.controller.getCurRoom().get().getPlayer().discardItem(
                this.controller.getCurRoom().get().getPlayer().getItems().get(0)
            ));
            assertFalse(this.itemNpc.getY().interact().getLoot().isPresent());

            this.handler.handleInteraction(this.controller.getCurRoom().get());
            assertTrue(this.controller.getCurRoom().get().getPlayer().getItems().isEmpty());
        }

        if (this.itemFurniture != null) {
            this.controller.getCurRoom().get().getPlayer().setPosition(
                new PositionImpl(this.itemFurniture.getX().getX() - 1, this.itemFurniture.getX().getY())
            );
            final var item = this.itemFurniture.getY().dropLoot();
            this.itemFurniture.getY().addLoot(item);
            assertTrue(this.itemFurniture.getY().hasLoot());
            this.handler.handleInteraction(this.controller.getCurRoom().get());
            assertFalse(this.controller.getCurRoom().get().getPlayer().getItems().isEmpty());
            assertTrue(this.controller.getCurRoom().get().getPlayer().discardItem(
                this.controller.getCurRoom().get().getPlayer().getItems().get(0)
            ));
            assertTrue(this.controller.getCurRoom().get().getPlayer().getItems().isEmpty());

            assertFalse(this.itemFurniture.getY().hasLoot());
            this.handler.handleInteraction(this.controller.getCurRoom().get());
            assertTrue(this.controller.getCurRoom().get().getPlayer().getItems().isEmpty());
        }

        if (this.emptyWalkableFurniture != null) {
            assertFalse(this.emptyWalkableFurniture.getY().hasEnemy());
            this.handler.handleInteraction(this.controller.getCurRoom().get());
            this.controller.getGameController().notify(Event.MOVE_DOWN);
            this.controller.getGameController().notify(Event.RELEASED);
            assertEquals(Direction.DOWN, this.controller.getCurRoom().get().getPlayer().getDirection());
            this.controller.getCurRoom().get().getPlayer().setMovement(Direction.RIGHT);
            this.controller.getCurRoom().get().getPlayer().stop();
        }

        if (this.enemy != null) {
            this.handler.handleInteraction(this.controller.getCurRoom().get());
            this.controller.getCurRoom().get().getPlayer().setPosition(
                new PositionImpl(this.enemy.getX().getX() - 1, this.enemy.getX().getY())
            );
            this.handler.handleInteraction(this.controller.getCurRoom().get());
            this.controller.getCombatController().handleAction(CombatAction.ATTACK);
            this.timer(e -> assertNotEquals(Constants.PLAYER_LIFE, 
                this.controller.getCurRoom().get().getPlayer().getLife()));
            this.controller.getGameController().run(this.controller.getCurRoom().isPresent());
        }

        if (this.enemyFurniture != null) {
            this.handler.handleInteraction(this.controller.getCurRoom().get());
            final int life = this.controller.getCurRoom().get().getPlayer().getLife();
            this.controller.getCurRoom().get().getPlayer().setPosition(
                new PositionImpl(this.enemyFurniture.getX().getX() - 1, this.enemyFurniture.getX().getY())
            );
            this.handler.handleInteraction(this.controller.getCurRoom().get());
            this.controller.getCombatController().handleAction(CombatAction.ATTACK);
            this.timer(e -> assertNotEquals(life, 
                this.controller.getCurRoom().get().getPlayer().getLife()));
        }

        for (int i = 0; i < 10 && this.controller.getCurRoom().isPresent(); i++) {
            this.controller.getCurRoom().get().getPlayer().setPosition(
                this.controller.getCurRoom().get().getExit()
            );
            this.controller.getCurRoom().get().setQuest(Optional.empty());
            this.handler.handleInteraction(this.controller.getCurRoom().get());
            try {
                Thread.sleep(ROOM_TRANSITION_SLEEP_TIME);
            } catch (InterruptedException e) {
                assertTrue(false); //NOPMD
                //suppressed as it intentionally fails the test because this sleep should not be interrupted.
            }
        }
        assertTrue(this.controller.getCurRoom().isEmpty());
    }

    private void initValues(final Room room) {
        room.getPopulation().forEach((pos, living) -> {
            if (living instanceof Npc) {
                if (((Npc) living).interact().getLoot().isPresent()) {
                    this.itemNpc = new Pair<>(pos, (Npc) living);
                }
            } else if (living instanceof Enemy) {
                this.enemy = new Pair<>(pos, (Enemy) living);
            }
        });

        room.getFurniture().forEach((pos, furniture) -> {
            if (furniture instanceof InteractiveFurniture) {
                if (((InteractiveFurniture) furniture).hasLoot()) {
                    this.itemFurniture = new Pair<>(pos, (InteractiveFurniture) furniture);
                }
            } else if (furniture instanceof WalkableFurniture) {
                if (((WalkableFurniture) furniture).hasEnemy()) {
                    this.enemyFurniture = new Pair<>(pos, (WalkableFurniture) furniture);
                } else {
                    this.emptyWalkableFurniture = new Pair<>(pos, (WalkableFurniture) furniture);
                }
            }
        });
    }

    private void timer(final ActionListener e) {
        final var timer = new Timer(DELAY_TRANSITION, e);
        timer.setRepeats(false);
        timer.start();
    }
}
