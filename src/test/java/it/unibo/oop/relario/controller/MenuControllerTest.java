package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    private MainView view;

    /**
     * Sets up the view needed to create the menu controller.
     */
    @BeforeEach
    void setUp() {
        view = new MainViewImpl(new MainControllerImpl());
        view.panelsSetup();
    }

    /**
     * Tests the menu controller method notify and its getters.
     */
    @Test
    void testMenuController() {
        final MenuController controller = new MenuControllerImpl(view);

        assertEquals(controller.getInGameMenuElements().size(), 2);
        assertEquals(controller.getInGameMenuElements().get(0).getElemCommad(), Command.CLOSE);
        assertEquals(controller.getInGameMenuElements().get(1).getElemCommad(), Command.QUIT);
        assertEquals(controller.getStartMenuElements().size(), 2);
        assertEquals(controller.getStartMenuElements().get(0).getElemCommad(), Command.PLAY);
        assertEquals(controller.getStartMenuElements().get(1).getElemCommad(), Command.QUIT);

        view.showPanel(GameState.INVENTORY);
        view.showPanel(GameState.MENU_IN_GAME);
        controller.notify(Event.INTERACT);
        assertNotEquals(view.getCurrentPanel(), GameState.INVENTORY);
        controller.notify(Event.ESCAPE);
        assertEquals(view.getCurrentPanel(), GameState.INVENTORY);
    }
}
