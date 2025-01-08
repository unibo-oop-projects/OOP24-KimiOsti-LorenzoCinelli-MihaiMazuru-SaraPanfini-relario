package it.unibo.oop.relario.view.impl;

import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;

/**
 * View implementations for the exploration phase of the game.
 */
public final class GameView extends JPanel {

    private static final long serialVersionUID = 1L;

    private final MainController controller;

    public GameView(MainController controller) {
        this.controller = controller;
        /**
         * [TODO]: completa costruttore per sfruttare i listener corretti e comunicare col controller.
         */
    }

    public void renderFloor(Dimension dimension) {
        /* [TODO]: implement method. */
    }

    public void renderTextures(Map<Position, ImageIcon> textures) {
        /* [TODO]: implement method. */
    }
}
