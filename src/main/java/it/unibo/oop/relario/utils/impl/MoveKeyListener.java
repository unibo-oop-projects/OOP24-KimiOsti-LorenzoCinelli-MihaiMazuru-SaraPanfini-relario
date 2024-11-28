package it.unibo.oop.relario.utils.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Implemention for handling the movement of the main character.
 */
public final class MoveKeyListener implements KeyListener {

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        /**
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> System.out.println("SU");
            //this.MoveController.move(UP);
            case KeyEvent.VK_DOWN -> System.out.println("GIU");
            //this.MoveController.move(DOWN);
            case KeyEvent.VK_RIGHT -> System.out.println("DENTRA");
            //this.MoveController.move(DESTRA);
            case KeyEvent.VK_LEFT -> System.out.println("SINISTA");
            //this.MoveController.move(SINISTRA);
            default -> System.out.println("Error");
        }
        */
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    } 
}
