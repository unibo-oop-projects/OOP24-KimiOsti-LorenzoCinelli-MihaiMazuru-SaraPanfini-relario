package it.unibo.oop.relario.view.impl;

import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.GameKeyListener;
import it.unibo.oop.relario.view.api.InventoryViewFactory;
import it.unibo.oop.relario.view.api.InventoryView;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Implementation of {@link InventoryView}.
 */
public final class InventoryViewImpl extends JPanel implements InventoryView {
    private static final long serialVersionUID = 1L;
    private static final double CONTENT_RATIO = 0.6;
    private static final double COMMANDS_RATIO = 0.15;
    private static final double DEFAULT_RATIO = 1;
    private static final int CONTENT_PANEL_INDEX = 1;

    private final transient InventoryController controller;
    private transient InventoryViewFactory factory;

    /**
     * Creates the inventory view.
     * @param controller is the main controller of the game.
     */
    public InventoryViewImpl(final MainController controller) {
        this.controller = controller.getInventoryController();
        this.addKeyListener(new GameKeyListener(this.controller));
        this.setBackground(Constants.BACKGROUND_SCENE_COLOR);
    }

    @Override
    public void refresh() {
        final var contentPanel = this.factory.createContentPanel();
        this.remove(CONTENT_PANEL_INDEX);
        this.add(contentPanel);
        this.resize(contentPanel, CONTENT_RATIO, CONTENT_RATIO);
        this.validate();
    }

    @Override
    public void init() {
        this.factory = new InventoryViewFactoryImpl(this.controller);
        final var commandPanel = this.factory.createCommandPanel();
        final var contentPanel = this.factory.createContentPanel();
        this.removeAll();
        this.add(commandPanel);
        this.add(contentPanel);
        this.resize(commandPanel, COMMANDS_RATIO, DEFAULT_RATIO);
        this.resize(contentPanel, CONTENT_RATIO, CONTENT_RATIO);
        this.validate();
    }

    private void resize(final JPanel panel, final double verticalRatio, final double horizontalRatio) {
        var dim = Toolkit.getDefaultToolkit().getScreenSize();
        dim = new Dimension((int) (dim.getWidth() * horizontalRatio), (int) (dim.getHeight() * verticalRatio));
        panel.setPreferredSize(dim);
    }

}
