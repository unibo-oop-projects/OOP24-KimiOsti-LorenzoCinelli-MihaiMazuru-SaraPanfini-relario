package it.unibo.oop.relario.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.impl.MainViewImpl;

/**
 * Tester class for the game's view entry point.
 */
public class MainViewTest {
    /**
     * Testing method for the main view.
     */
    @Test
    public void testMainView() {
        final var view = new MainViewImpl(new MainControllerImpl());
        view.panelsSetup();

        view.showPanel(GameState.MENU);
        assertEquals(view.getPanel(GameState.MENU), view.getPanel(view.getCurrentPanel()));

        view.showPanel(GameState.GAME);
        assertEquals(view.getPanel(GameState.GAME), view.getPanel(view.getCurrentPanel()));
        assertEquals(view.getPanel(GameState.MENU), view.getPanel(view.getCurrentPanel()));
    }
}
