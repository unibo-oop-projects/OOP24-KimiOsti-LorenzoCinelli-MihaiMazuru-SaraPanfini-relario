package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.api.MenuController;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.controller.impl.MenuControllerImpl;
import it.unibo.oop.relario.model.menu.Command;
import it.unibo.oop.relario.utils.impl.Event;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;
import it.unibo.oop.relario.view.impl.MainViewImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * Used to avoid CheckStyle violations for magic numbers, here used for test scenarios. 
 */
/**
 * Test class for {@link MenuControllerImpl} class.
 */
class MenuControllerTest {

    /**
     * Tests the menu controller method notify and its getters.
     */
    @Test
    void testMenuController() {
        final MainController mainController = new MainControllerImpl();
        final MainView view = new MainViewImpl(mainController);
        view.panelsSetup();
        final MenuController controller = new MenuControllerImpl(view, mainController);

        mainController.moveToNextRoom();
        assertEquals(controller.getInGameMenuElements().size(), 2);
        assertEquals(controller.getInGameMenuElements().get(0).getElemCommad(), Command.CLOSE);
        assertEquals(controller.getInGameMenuElements().get(1).getElemCommad(), Command.QUIT);
        assertEquals(controller.getStartMenuElements().size(), 2);
        assertEquals(controller.getStartMenuElements().get(0).getElemCommad(), Command.PLAY);
        assertEquals(controller.getStartMenuElements().get(1).getElemCommad(), Command.QUIT);

        controller.showMenu(GameState.MENU, GameState.GAME);
        assertEquals(view.getCurrentPanel(), GameState.MENU);
        controller.notify(Event.ESCAPE);
        assertNotEquals(view.getCurrentPanel(), GameState.GAME);
        controller.showMenu(GameState.MENU_IN_GAME, GameState.INVENTORY);
        controller.notify(Event.ESCAPE);
        assertEquals(view.getCurrentPanel(), GameState.INVENTORY);
        controller.showMenu(GameState.MENU_IN_GAME, GameState.GAME);
        controller.notify(Event.ESCAPE);
        assertEquals(view.getCurrentPanel(), GameState.GAME);
        controller.showMenu(GameState.MENU_IN_GAME, GameState.COMBAT);
        controller.notify(Event.ESCAPE);
        assertEquals(view.getCurrentPanel(), GameState.COMBAT);
        controller.showMenu(GameState.MENU_IN_GAME, GameState.COMBAT);
        controller.notify(Event.INVENTORY);
        assertNotEquals(view.getCurrentPanel(), GameState.COMBAT);
    }
}
