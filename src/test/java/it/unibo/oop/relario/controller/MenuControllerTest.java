package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.api.MenuController;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.controller.impl.MenuControllerImpl;
import it.unibo.oop.relario.model.menu.Command;

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
        final MenuController controller = new MenuControllerImpl(mainController);

        mainController.moveToNextRoom();
        assertEquals(controller.getInGameMenuElements().size(), 2);
        assertEquals(controller.getInGameMenuElements().get(0).getElemCommad(), Command.CLOSE);
        assertEquals(controller.getInGameMenuElements().get(1).getElemCommad(), Command.QUIT);
        assertEquals(controller.getStartMenuElements().size(), 2);
        assertEquals(controller.getStartMenuElements().get(0).getElemCommad(), Command.PLAY);
        assertEquals(controller.getStartMenuElements().get(1).getElemCommad(), Command.QUIT);

    }
}
