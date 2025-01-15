package it.unibo.oop.relario.view.impl;

import javax.swing.JPanel;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.view.api.InventoryViewFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * View implementation for inventory navigation.
 */
public class InventoryView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final double CONTENT_RATIO = 0.6;
    private static final double COMMANDS_RATIO = 0.15;
    private static final double DEFAULT_RATIO = 1;

    private final InventoryViewFactory factory;
    private JPanel contentPanel;

    /**
     * Creates the inventory view.
     * @param controller the main controller of the game.
     */
    public InventoryView(final MainController controller) {
        factory = new InventoryViewFactoryImpl(controller.getInventoryController());
    }

    /**
     * Initializes the inventory view.
     */
    public void init() {
        final var commandPanel = this.factory.createCommandPanel();
        contentPanel = this.factory.createContentPanel();
        this.setLayout(new FlowLayout());
        this.setBackground(Color.BLACK);
        this.add(commandPanel);
        this.add(contentPanel);
        this.validate();
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
    public final void refresh() {
        this.contentPanel = this.factory.createContentPanel();
        this.validate();
    }
}
