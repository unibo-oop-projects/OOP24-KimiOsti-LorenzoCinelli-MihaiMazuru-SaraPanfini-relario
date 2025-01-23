package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.CutSceneController;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test class for the {@link CutSceneControllerImpl} class.
 */
final class CutSceneControllerTest {

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

    private void testShowFromMenu() {
        this.cutscene.show(GameState.MENU);
        assertEquals(GameState.CUT_SCENE, this.mainView.getCurrentPanel());

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.addSuppressed(e);
        }
        assertEquals(GameState.GAME, this.mainView.getCurrentPanel());
    }

    private void testShowFromGame() {
        this.cutscene.show(GameState.GAME);
        assertEquals(GameState.CUT_SCENE, this.mainView.getCurrentPanel());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.addSuppressed(e);
        }
        assertEquals(GameState.GAME, this.mainView.getCurrentPanel());
    }

    private void testShowDefeat() {
        this.cutscene.show(GameState.GAME_OVER);
        assertEquals(GameState.CUT_SCENE, this.mainView.getCurrentPanel());

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.addSuppressed(e);
        }
        assertEquals(GameState.MENU, this.mainView.getCurrentPanel());
    }

    private void testShowVictory() {
        this.cutscene.show(GameState.VICTORY);
        assertEquals(GameState.CUT_SCENE, this.mainView.getCurrentPanel());

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.addSuppressed(e);
        }
        assertEquals(GameState.MENU, this.mainView.getCurrentPanel());
    }
}
