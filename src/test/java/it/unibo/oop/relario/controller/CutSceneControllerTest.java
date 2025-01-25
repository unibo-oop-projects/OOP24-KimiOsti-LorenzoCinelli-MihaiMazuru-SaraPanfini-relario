package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.CutSceneController;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Test class for the {@link CutSceneController} interface.
 */
final class CutSceneControllerTest {
    private static final int INTRODUCTION_TIME = 10_000;
    private static final int NEXT_ROOM_TIME = 6000;
    private static final int SCENE_TIME = 5000;

    private MainView mainView;
    private CutSceneController cutscene;

    /**
     * Sets up the testing.
     */
    @BeforeEach
    void setUp() {
        final var mainController = new MainControllerImpl();
        this.mainView = mainController.getMainView();
        this.cutscene = mainController.getCutSceneController();
    }

    /**
     * Tests the changing of states.
     */
    @Test
    void testShow() {
        assertEquals(GameState.MENU, this.mainView.getCurrentPanel());

        testShowFromMenu();
        testShowFromGame();
        testShowDefeat();
        testShowVictory();
    }

    @Disabled
    void testShowFromMenu() {
        this.cutscene.show(GameState.MENU);
        assertEquals(GameState.CUT_SCENE, this.mainView.getCurrentPanel());

        try {
            Thread.sleep(INTRODUCTION_TIME);
        } catch (InterruptedException e) {
            e.addSuppressed(e);
        }
        assertEquals(GameState.GAME, this.mainView.getCurrentPanel());
    }

    @Disabled
    void testShowFromGame() {
        this.cutscene.show(GameState.GAME);
        assertEquals(GameState.CUT_SCENE, this.mainView.getCurrentPanel());

        try {
            Thread.sleep(NEXT_ROOM_TIME);
        } catch (InterruptedException e) {
            e.addSuppressed(e);
        }
        assertEquals(GameState.GAME, this.mainView.getCurrentPanel());
    }

    @Disabled
    void testShowDefeat() {
        this.cutscene.show(GameState.GAME_OVER);
        assertEquals(GameState.CUT_SCENE, this.mainView.getCurrentPanel());

        try {
            Thread.sleep(SCENE_TIME);
        } catch (InterruptedException e) {
            e.addSuppressed(e);
        }
        assertEquals(GameState.MENU, this.mainView.getCurrentPanel());
    }

    @Disabled
    void testShowVictory() {
        this.cutscene.show(GameState.VICTORY);
        assertEquals(GameState.CUT_SCENE, this.mainView.getCurrentPanel());

        try {
            Thread.sleep(SCENE_TIME);
        } catch (InterruptedException e) {
            e.addSuppressed(e);
        }
        assertEquals(GameState.MENU, this.mainView.getCurrentPanel());
    }
}
