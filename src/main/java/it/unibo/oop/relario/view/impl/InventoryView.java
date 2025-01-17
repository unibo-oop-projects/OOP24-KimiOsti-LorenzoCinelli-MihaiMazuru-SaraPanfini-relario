package it.unibo.oop.relario.view.impl;

import javax.swing.JPanel;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.GameKeyListener;
import it.unibo.oop.relario.view.api.InventoryViewFactory;

import java.awt.Color;
import java.awt.Dimension;

/**
 * View implementation for inventory navigation.
 */
public final class InventoryView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final double CONTENT_RATIO = 0.6;
    private static final double COMMANDS_RATIO = 0.15;
    private static final double DEFAULT_RATIO = 1;
    private static final int CONTENT_PANEL_INDEX = 1;

    private final InventoryViewFactory factory;

    /**
     * Creates and initializes the inventory view.
     * @param controller is the main controller of the game.
     */
    public InventoryView(final MainController controller) {
        this.factory = new InventoryViewFactoryImpl(controller.getInventoryController());
        this.addKeyListener(new GameKeyListener(controller.getInventoryController()));
        final var commandPanel = this.factory.createCommandPanel();
        final var contentPanel = this.factory.createContentPanel();
        this.setBackground(Color.BLACK);
        this.add(commandPanel);
        this.add(contentPanel);
        this.resize(commandPanel, COMMANDS_RATIO, DEFAULT_RATIO);
        this.resize(contentPanel, CONTENT_RATIO, CONTENT_RATIO);
        this.validate();
    }

    private void resize(final JPanel panel, final double verticalRatio, final double horizontalRatio) {
        final var dim = new Dimension((int) (this.getWidth() * horizontalRatio), (int) (this.getHeight() * verticalRatio));
        panel.setPreferredSize(dim);
    }

    /**
     * Refresh the inventory view with updated data.
     */
    public void refresh() {
        final var contentPanel = this.factory.createContentPanel();
        this.remove(CONTENT_PANEL_INDEX);
        this.add(contentPanel);
        this.resize(contentPanel, CONTENT_RATIO, CONTENT_RATIO);
        this.repaint();
        this.validate();
    }
}
