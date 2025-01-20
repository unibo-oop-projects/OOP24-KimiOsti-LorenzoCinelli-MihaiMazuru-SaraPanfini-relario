package it.unibo.oop.relario.view.impl;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.CombatController;

/**
 * View implementation for the combat phase of the game.
 */
public class CombatView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final double SIDE_COMPONENTS_RATIO = 0.25;

    private final CombatController controller;
    private final JPanel upperPadding;
    private final CombatScene centralScene;
    private final JPanel commands;
    private final JPanel message;

    /* [TODO]: aggiungere il contenuto ai component e scrivere il metodo update */

    public CombatView(final CombatController controller) {
        this.setBackground(BACKGROUND_COLOR);

        this.controller = controller;

        this.upperPadding = new JPanel();
        this.setupPanel(this.upperPadding);
        this.centralScene = new CombatScene(this.controller);
        this.setupPanel(this.centralScene);
        this.commands = new JPanel();
        this.setupPanel(this.commands);
        this.message = new JPanel();
        this.setupPanel(this.message);

        this.resizePanels();
        this.update();
    }

    /**
     * Updates the combat view.
     */
    public void update() {

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

}
