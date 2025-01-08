package it.unibo.oop.relario.utils.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implemention for handling the movement of the main character.
 */
public final class GameKeyListener implements KeyListener {

    private final MainView view;
    private final MainController controller;

    /**
     * Initializes the game game listener.
     * @param view is the main view that contains references to all game panels.
     * @param controller is the main controller that contains references to all controllers. 
     */
    public GameKeyListener(final MainView view, final MainController controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (this.view.getCurrentPanel().equals(GameState.INVENTORY)) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                this.view.showPreviousPanel();
            }
        } else if (this.view.getCurrentPanel().equals(GameState.GAME)) {
            if (e.getKeyCode() == KeyEvent.VK_I) {
                this.view.showPanel(GameState.INVENTORY);
            } else if (e.getKeyCode() == KeyEvent.VK_E) {
                //gestione interazioni;
            } else if (isMovementKey(e)) {
                final MainCharacter player = controller.getCurRoom().get().getPlayer();
                player.setMovement(convertDirection(e));
                player.update();
                //this.view.getCurrentPanel().draw(); //aggiungere metodo draw   
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                this.view.showPanel(GameState.MENU_IN_GAME);
            }
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    } 

    /**
     * Checks if the key pressed is a valid key for movement.
     * @param e is the key pressed.
     * @return true if the the key pressed is a valid key for movement, false otherwise.
     */
    private boolean isMovementKey(final KeyEvent e) {
        final int keyCode = e.getKeyCode();
        return keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S ||
        keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A;
    }

    /**
     * Converts the the key pressed into a direction.
     * @param e is the key pressed.
     * @return the direction corresponding to the key pressed.
     */
    private static Direction convertDirection(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: 
                return Direction.UP;
            case KeyEvent.VK_DOWN: 
                return Direction.DOWN;
            case KeyEvent.VK_RIGHT: 
                return Direction.RIGHT;
            case KeyEvent.VK_LEFT: 
                return Direction.LEFT;
            default: throw new IllegalArgumentException("Illegal pressed key");
        }
    }

}
