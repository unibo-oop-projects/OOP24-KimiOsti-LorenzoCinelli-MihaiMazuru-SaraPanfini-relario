package it.unibo.oop.relario.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MenuTest {
    
    @Test
    void testMenuElement() {
        final MenuElement elemClose = new MenuElement(Command.CLOSE);
        final MenuElement elemPlay = new MenuElement(Command.PLAY);
        final MenuElement elemQuit = new MenuElement(Command.QUIT);

        assertEquals(elemClose.getElemCommad(), Command.CLOSE);
        assertEquals(elemClose.getElemName(), Command.CLOSE.getName());
        assertEquals(elemPlay.getElemCommad(), Command.PLAY);
        assertEquals(elemPlay.getElemName(), Command.PLAY.getName());
        assertEquals(elemQuit.getElemCommad(), Command.QUIT);
        assertEquals(elemQuit.getElemName(), Command.QUIT.getName());
    }
}
