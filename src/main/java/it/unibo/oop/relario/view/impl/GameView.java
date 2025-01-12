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

/* [TODO]: aggiungere le scritte, e gestire l'aggiornamento frame by frame */

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

    /**
     * The constructor for the game view.
     * @param controller the observer for user input events.
     */
    public GameView(final MainController controller) {
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
    public void renderBackground(final Dimension dimension, final Map<Position, Image> textures) {
        this.mapDimension = dimension;
        this.tileDimension = this.min(
            this.getHeight() / this.mapDimension.getHeight(),
            this.getWidth() / this.mapDimension.getWidth()
        );

        this.resizePanels();

        for (int y = 0; y < dimension.getHeight(); y++) {
            for (int x = 0; x < dimension.getWidth(); x++) {
                this.background.add(
                    this.computeIndex(x, y),
                    new BackgroundTile(null)
                );
                this.add(
                    this.background.get(this.computeIndex(x, y)),
                    this.computeIndex(x, y)
                );
            }
        }

        for (final var texture : textures.entrySet()) {
            final var tile = new BackgroundTile(
                texture.getValue().getScaledInstance(
                    this.tileDimension,
                    this.tileDimension,
                    Image.SCALE_SMOOTH
                )
            );

            this.background.get(this.computeIndex(texture.getKey().getX(), texture.getKey().getY())).add(tile);
            this.background.remove(this.computeIndex(texture.getKey().getX(), texture.getKey().getY()));
            this.background.add(
                this.computeIndex(texture.getKey().getX(), texture.getKey().getY()),
                tile
            );
        }
    }

    /**
     * Renders the scene's foreground, sizing the textures to fit on their background.
     * @param textures the textures to be rendered on the foreground of the scene.
     */
    public void renderTextures(final Map<Position, Image> textures) {
        /* [TODO]: implement method. */
    }

    private void setBackgroundColor(final Color color) {
        this.setBackground(color);
        for (final var component : this.getComponents()) {
            component.setBackground(color);
        }
    }

    private int min(final int x, final int y) {
        return x < y ? x : y;
    }

    private void resizePanels() {
        this.mapPanel.setPreferredSize(
            new java.awt.Dimension(
                this.mapDimension.getWidth() * this.tileDimension,
                this.mapDimension.getHeight() * this.tileDimension
            )
        );

        this.upperPanel.setPreferredSize(
            new java.awt.Dimension(
                this.getWidth(),
                (this.getHeight() - (int) this.mapPanel.getPreferredSize().getHeight()) / 3
            )
        );
    }

    private int computeIndex(final int x, final int y) {
        return this.mapDimension == null ? 0 : (x + y * this.mapDimension.getWidth());
    }
}
