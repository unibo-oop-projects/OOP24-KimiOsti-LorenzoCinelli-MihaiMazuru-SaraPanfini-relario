package it.unibo.oop.relario.view.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.GameKeyListener;
import it.unibo.oop.relario.utils.impl.ResourceLocator;

/* [TODO]: gestire l'aggiornamento frame by frame */
/* [TODO]: refactoring */

/**
 * View implementations for the exploration phase of the game.
 */
public final class GameView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final double SCREEN_TO_MAP_RATIO = 1.5;
    private static final int PANEL_TO_TEXT_RATIO = 3;

    private final JPanel upperPanel;
    private final JPanel mapPanel;
    private final JPanel lowerPanel;
    private final List<BackgroundTile> background;
    private final List<String> commands;
    private final List<Position> foreground;
    private final Font font;
    private Dimension mapDimension;
    private int tileDimension;

    /**
     * The constructor for the game view.
     * @param controller the observer for user input events.
     */
    public GameView(final MainController controller) {
        this.commands = List.of(
            "WASD - move",
            "I - interact",
            "E - inventory"
        );

        this.upperPanel = new JPanel();
        this.mapPanel = new JPanel();
        this.lowerPanel = new JPanel();
        this.add(this.upperPanel);
        this.add(this.mapPanel);
        this.add(this.lowerPanel);
        this.setBackgroundColor(BACKGROUND_COLOR);

        this.background = new LinkedList<>();
        this.foreground = new LinkedList<>();
        this.font = ResourceLocator.getGameFont(Constants.MONOSPACE_FONT);
        this.addKeyListener(new GameKeyListener(controller.getGameController()));
    }

    /**
     * Renders the scene's background, sized to fit in the current frame.
     * @param dimension the map dimension.
     * @param textures the textures to be rendered on the background, apart from the floor.
     */
    public void renderBackground(final Dimension dimension, final Map<Position, Image> textures) {
        this.renderFloor(dimension);
        this.renderBackgroundTextures(textures);
    }

    /**
     * Renders the scene's foreground, sizing the textures to fit on their background.
     * @param textures the textures to be rendered on the foreground of the scene.
     */
    public void renderTextures(final Map<Position, Image> textures) {
        this.foreground.forEach(
            pos -> {
                this.background.get(this.computeIndex(pos.getX(), pos.getY())).removeAll();
                this.refresh(this.background.get(this.computeIndex(pos.getX(), pos.getY())));
            }
        );
        for (final var texture : textures.entrySet()) {
            this.background.get(this.computeIndex(texture.getKey().getX(), texture.getKey().getY())).add(
                new ForegroundTile(texture.getValue().getScaledInstance(this.tileDimension, this.tileDimension, Image.SCALE_SMOOTH))
            );
            this.foreground.add(texture.getKey());
            this.refresh(this.background.get(this.computeIndex(texture.getKey().getX(), texture.getKey().getY())));
        }
    }

    /**
     * Renders the text resulting from an interaction in the lower panel.
     * @param text the text to be shown.
     */
    public void showInteractionText(final String text) {
        this.lowerPanel.removeAll();
        this.lowerPanel.add(this.getCustomLabel(this.lowerPanel, text));
        this.refresh(this.lowerPanel);
    }

    private void setBackgroundColor(final Color color) {
        this.setBackground(color);
        for (final var component : this.getComponents()) {
            component.setBackground(color);
        }
    }

    private void renderFloor(final Dimension dimension) {
        this.mapDimension = dimension;
        this.tileDimension = this.min(
            (int) (this.getHeight() / SCREEN_TO_MAP_RATIO / this.mapDimension.getHeight()),
            (int) (this.getWidth() / SCREEN_TO_MAP_RATIO / this.mapDimension.getWidth())
        );

        this.resizePanels();
        this.mapPanel.setLayout(new GridLayout(
            this.mapDimension.getHeight(),
            this.mapDimension.getWidth(),
            0,
            0
        ));

        final var texture = ResourceLocator.getFloorTexture().getScaledInstance(
            this.tileDimension,
            this.tileDimension,
            Image.SCALE_SMOOTH
        );

        for (int y = 0; y < dimension.getHeight(); y++) {
            for (int x = 0; x < dimension.getWidth(); x++) {
                this.background.add(
                    this.computeIndex(x, y),
                    new BackgroundTile(texture) //aggiungere immagine del floor
                );
                this.mapPanel.add(
                    this.background.get(this.computeIndex(x, y)),
                    this.computeIndex(x, y)
                );
            }
        }
    }

    private void renderBackgroundTextures(final Map<Position, Image> textures) {
        for (final var texture : textures.entrySet()) {
            final var innerTile = new BackgroundTile(
                texture.getValue().getScaledInstance(
                    this.tileDimension,
                    this.tileDimension,
                    Image.SCALE_SMOOTH
                )
            );

            final var outerTile = this.background.get(this.computeIndex(
                texture.getKey().getX(),
                texture.getKey().getY()
            ));

            outerTile.add(innerTile);
            this.refresh(outerTile);

            this.background.remove(outerTile);
            this.background.add(
                this.computeIndex(texture.getKey().getX(), texture.getKey().getY()),
                innerTile
            );
        }
    }

    private void refresh(final JComponent component) {
        component.revalidate();
        component.repaint();
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

        this.updateUpperPanel();

        this.lowerPanel.setPreferredSize(
            new java.awt.Dimension(
                (int) this.mapPanel.getPreferredSize().getWidth(),
                (this.getHeight() - (int) this.mapPanel.getPreferredSize().getHeight()) / 2
            )
        );
    }

    private int computeIndex(final int x, final int y) {
        return this.mapDimension == null ? 0 : (x + y * this.mapDimension.getWidth());
    }

    private void updateUpperPanel() {
        this.upperPanel.removeAll();
        this.upperPanel.setPreferredSize(
            new java.awt.Dimension(
                this.getWidth(),
                (this.getHeight() - (int) this.mapPanel.getPreferredSize().getHeight()) / 3
            )
        );
        this.commands.forEach(e -> this.upperPanel.add(this.getCustomLabel(this.upperPanel, e)));
    }

    private JLabel getCustomLabel(final JPanel container, final String text) {
        final var label = new JLabel();
        label.setFont(this.font.deriveFont(container.getHeight() / PANEL_TO_TEXT_RATIO));
        label.setForeground(TEXT_COLOR);
        label.setText(text);
        return label;
    }
}
