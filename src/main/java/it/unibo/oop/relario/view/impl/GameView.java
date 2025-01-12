package it.unibo.oop.relario.view.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.GameKeyListener;
import it.unibo.oop.relario.utils.impl.ResourceLocator;

/**
 * View implementations for the exploration phase of the game.
 */
public final class GameView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final String FONT_NAME = "monogram";

    private final JPanel upperPanel;
    private final JPanel mapPanel;
    private final JPanel lowerPanel;
    private final List<BackgroundTile> background;
    private final Map<Position, ForegroundTile> foreground;
    private final Font font;
    private Dimension mapDimension;
    private int tileDimension;

    public GameView(MainController controller) {
        this.upperPanel = new JPanel();
        this.mapPanel = new JPanel();
        this.lowerPanel = new JPanel();
        this.setBackgroundColor(Color.BLACK);

        this.background = new LinkedList<>();
        this.foreground = new HashMap<>();
        this.font = ResourceLocator.getGameFont(FONT_NAME);

        this.add(this.upperPanel);
        this.add(this.mapPanel);
        this.add(this.lowerPanel);
        this.addKeyListener(new GameKeyListener(controller.getGameController()));
    }

    /**
     * Renders the scene's background, sized to fit in the current frame.
     * @param dimension the map dimension.
     * @param textures the textures to be rendered on the background, apart from the floor.
     */
    public void renderBackground(Dimension dimension, Map<Position, Image> textures) {
        /* [TODO]: implement method. */
    }

    /**
     * Renders the scene's foreground, sizing the textures to fit on their background.
     * @param textures the textures to be rendered on the foreground of the scene.
     */
    public void renderTextures(Map<Position, Image> textures) {
        /* [TODO]: implement method. */
    }

    private void setBackgroundColor(Color color) {
        this.setBackground(color);
        for (final var component : this.getComponents()) {
            component.setBackground(color);
        }
    }

    private int min(int x, int y) {
        return x < y ? x : y;
    }

    private int computeIndex(Position pos) {
        if(this.mapDimension != null) {
            return pos.getX() + pos.getY() * this.mapDimension.getWidth();
        } else {
            return 0;
        }
    }
}
