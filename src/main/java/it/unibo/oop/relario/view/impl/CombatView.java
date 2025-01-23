package it.unibo.oop.relario.view.impl;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.FontHandler;

/* [TODO]: implementare action listener del comando inventory */

/**
 * View implementation for the combat phase of the game.
 */
public class CombatView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final double SCREEN_TO_SCENE_RATIO = 1.5;
    private static final double SIDE_COMPONENTS_RATIO = 0.25;
    private static final double FONT_TO_PANEL_RATIO = 0.25;
    private static final boolean ATTACKING = false;
    private static final boolean BEGGING_MERCY = true;

    private final CombatController controller;
    private final JPanel upperPadding;
    private final CombatScene centralScene;
    private final JPanel commands;
    private final JPanel message;
    private final Font font;

    /**
     * Creates the panel showing combat scenes.
     * @param controller the controller receiving content queries.
     */
    public CombatView(final CombatController controller) {
        this.setBackground(BACKGROUND_COLOR);

        this.controller = controller;
        this.font = FontHandler.getFont(Constants.MONOSPACE_FONT);

        this.upperPadding = new JPanel();
        this.setupPanel(this.upperPadding);
        this.centralScene = new CombatScene(this.controller);
        this.setupPanel(this.centralScene);
        this.commands = this.createCommandPanel();
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
            (int) (this.getWidth() / SCREEN_TO_SCENE_RATIO),
            (int) (this.getHeight() / SCREEN_TO_SCENE_RATIO)
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
        label.setFont(this.font.deriveFont(
            (float) (this.message.getPreferredSize().getHeight() * FONT_TO_PANEL_RATIO)
        ));
        label.setText(msg);
        this.refresh(this.message);
    }

    private JPanel createCommandPanel() {
        final var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final var attack = new JButton("Attacca");
        attack.addActionListener(e -> this.controller.handleCombatAction(ATTACKING));
        final var inventory = new JButton("Inventario");
        inventory.addActionListener(null /* [TODO]: implement action listener */);
        final var mercy = new JButton("Chiedi pietà");
        mercy.addActionListener(e -> this.controller.handleCombatAction(BEGGING_MERCY));
        final var buttons = List.of(attack, inventory, mercy);

        buttons.forEach(e -> {
            panel.add(e);
            e.setFont(this.font);
        });

        return panel;
    }

}
