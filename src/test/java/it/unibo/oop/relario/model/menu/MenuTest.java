package it.unibo.oop.relario.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuTest {

    private MenuElement elemClose;
    private MenuElement elemPlay;
    private MenuElement elemQuit;

    @BeforeEach
    void setUp() {
        elemClose = new MenuElement(Command.CLOSE);
        elemPlay = new MenuElement(Command.PLAY);
        elemQuit = new MenuElement(Command.QUIT);
    }
    
    @Test
    void testMenuElement() {
        assertEquals(elemClose.getElemCommad(), Command.CLOSE);
        assertEquals(elemClose.getElemName(), Command.CLOSE.getName());
        assertEquals(elemPlay.getElemCommad(), Command.PLAY);
        assertEquals(elemPlay.getElemName(), Command.PLAY.getName());
        assertEquals(elemQuit.getElemCommad(), Command.QUIT);
        assertEquals(elemQuit.getElemName(), Command.QUIT.getName());
    }

    @Test
    void testMenuImpl() {
        final MenuImpl menu = new MenuImpl();

        assertEquals(menu.getElem(), new ArrayList<>());
        menu.addElem(elemPlay);
        menu.addElem(elemClose);
        menu.addElem(elemQuit);
        assertEquals(menu.getElem(), List.of(elemPlay, elemClose, elemQuit));
        menu.addElem(elemClose);
        assertEquals(menu.getElem(), List.of(elemPlay, elemClose, elemQuit, elemClose));
    }

    @Test
    void testMenuManager() {
        final MenuManager manager = new MenuManager();

        assertEquals(manager.getStartMenu().getElem().size(), 2);
        assertEquals(manager.getStartMenu().getElem().get(0).getElemCommad(), Command.PLAY);
        assertEquals(manager.getStartMenu().getElem().get(1).getElemCommad(), Command.QUIT);
        assertEquals(manager.getInGameMenu().getElem().size(), 2);
        assertEquals(manager.getInGameMenu().getElem().get(0).getElemCommad(), Command.CLOSE);
        assertEquals(manager.getInGameMenu().getElem().get(1).getElemCommad(), Command.QUIT);
    }
}
