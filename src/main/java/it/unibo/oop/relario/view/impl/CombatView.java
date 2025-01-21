package it.unibo.oop.relario.view.impl;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.ResourceLocator;

/* [TODO]: aggiungere il contenuto del component dei comandi */

/**
 * View implementation for the combat phase of the game.
 */
public class CombatView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final double SIDE_COMPONENTS_RATIO = 0.25;
    private static final double FONT_TO_PANEL_RATIO = 0.25;

    private final CombatController controller;
    private final JPanel upperPadding;
    private final CombatScene centralScene;
    private final JPanel commands;
    private final JPanel message;

    public CombatView(final CombatController controller) {
        this.setBackground(BACKGROUND_COLOR);

        this.controller = controller;

        this.upperPadding = new JPanel();
        this.setupPanel(this.upperPadding);
        this.centralScene = new CombatScene(this.controller);
        this.setupPanel(this.centralScene);
        this.commands = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.setupPanel(this.commands);
        this.message = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.setupPanel(this.message);

        this.resizePanels();
    }

    /**
     * Updates the combat view.
     */
    public void update() {
        this.centralScene.update();
        this.updateMessage(this.controller.getCombatState());
    }

    private void setupPanel(final JPanel panel) {
        panel.setBackground(BACKGROUND_COLOR);
        this.add(panel);
    }

    private void resizePanels() {
        this.centralScene.setPreferredSize(new java.awt.Dimension(
            (int) (this.getWidth() / 1.5),
            (int) (this.getHeight() / 1.5)
        ));
        final var sideComponentDim = new java.awt.Dimension(
            (int) (this.centralScene.getPreferredSize().getWidth()),
            (int) (this.getHeight() - this.centralScene.getPreferredSize().getHeight() * SIDE_COMPONENTS_RATIO)
        );
        this.upperPadding.setPreferredSize(sideComponentDim);
        this.commands.setPreferredSize(sideComponentDim);
        this.message.setPreferredSize(sideComponentDim);
        this.refresh(this);
    }

    private void refresh(final JComponent component) {
        component.revalidate();
        component.repaint();
    }

    private void updateMessage(final String msg) {
        this.message.removeAll();
        final var label = new JLabel();
        label.setBackground(BACKGROUND_COLOR);
        label.setForeground(TEXT_COLOR);
        label.setFont(ResourceLocator.getGameFont(Constants.MONOSPACE_FONT).deriveFont(
            (float) (this.message.getPreferredSize().getHeight() * FONT_TO_PANEL_RATIO)
        ));
        label.setText(msg);
        this.refresh(this.message);
    }

}
