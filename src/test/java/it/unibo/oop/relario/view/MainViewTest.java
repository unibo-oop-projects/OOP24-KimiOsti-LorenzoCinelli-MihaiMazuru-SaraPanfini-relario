package it.unibo.oop.relario.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.utils.impl.GameState;

/**
 * Tester class for the game's view entry point.
 */
class MainViewTest {
    /**
     * Testing method for the main view.
     */
    @Test
    void testMainView() {
        final MainControllerImpl controller = new MainControllerImpl();

        controller.getMenuController().showMenu(GameState.MENU, GameState.NONE);
        assertEquals(GameState.MENU, controller.getCurrentState());

        controller.moveToNextRoom();

        controller.getGameController().run(true);
        assertEquals(GameState.GAME, controller.getCurrentState());
        controller.getCombatController().resumeCombat();
        assertEquals(GameState.COMBAT, controller.getCurrentState());
        controller.getInventoryController().init(GameState.COMBAT);
        assertEquals(GameState.INVENTORY, controller.getCurrentState());
    }
}
