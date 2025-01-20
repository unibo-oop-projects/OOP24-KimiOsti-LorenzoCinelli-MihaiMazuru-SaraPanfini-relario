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

class MenuControllerTest {

    private MainView view;

    @BeforeEach
    void setUp() {
        view = new MainViewImpl(new MainControllerImpl());
        view.panelsSetup();
    }

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
