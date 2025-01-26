package it.unibo.oop.relario.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.impl.CombatAction;
import it.unibo.oop.relario.utils.impl.AttackDirection;
import it.unibo.oop.relario.utils.impl.Constants;

/**
 * View implementation for the combat phase of the game.
 */
public final class CombatView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final double SCREEN_TO_SCENE_RATIO = 1.5;
    private static final double SIDE_COMPONENTS_RATIO = 0.25;
    private static final double FONT_TO_PANEL_RATIO = 0.25;

    private final transient CombatController controller;
    private final JPanel upperPadding;
    private final CombatScene centralScene;
    private final JPanel commands;
    private final JPanel message;

    /**
     * Creates the panel showing combat scenes.
     * @param controller the controller receiving content queries.
     */
    public CombatView(final CombatController controller) {
        this.setBackground(BACKGROUND_COLOR);

        this.controller = controller;

        this.upperPadding = new JPanel();
        this.setupPanel(this.upperPadding);
        this.centralScene = new CombatScene(this.controller);
        this.setupPanel(this.centralScene);
        this.commands = this.createCommandPanel();
        this.setupPanel(this.commands);
        this.message = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.setupPanel(this.message);
    }

    /**
     * Updates the combat view.
     * @param direction the direction of the attack.
     */
    public void update(final AttackDirection direction) {
        this.resizePanels();
        this.centralScene.update(direction);
        this.updateMessage(this.controller.getCombatState());
    }

    private void setupPanel(final JPanel panel) {
        panel.setBackground(BACKGROUND_COLOR);
        this.add(panel);
    }

    private void resizePanels() {
        this.centralScene.setPreferredSize(new Dimension(
            (int) (this.getWidth() / SCREEN_TO_SCENE_RATIO),
            (int) (this.getHeight() / SCREEN_TO_SCENE_RATIO)
        ));
        final var sideComponentDim = new Dimension(
            this.getWidth(),
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
        label.setFont(Constants.FONT.deriveFont(
            (float) (this.message.getPreferredSize().getHeight() * FONT_TO_PANEL_RATIO)
        ));
        label.setText(msg);
        this.refresh(this.message);
    }

    private JPanel createCommandPanel() {
        final var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final var attack = this.getCustomButton("Attacca");
        attack.addActionListener(e -> this.controller.handleAction(CombatAction.ATTACK));
        final var inventory = this.getCustomButton("Inventario");
        inventory.addActionListener(e -> this.controller.handleAction(CombatAction.OPEN_INVENTORY));
        final var mercy = this.getCustomButton("Chiedi pieta'");
        mercy.addActionListener(e -> this.controller.handleAction(CombatAction.MERCY));
        final var buttons = List.of(attack, inventory, mercy);

        buttons.forEach(button -> panel.add(button));

        return panel;
    }

    private JButton getCustomButton(final String text) {
        final var button = new JButton();
        button.setFont(Constants.FONT);
        button.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        button.setForeground(Constants.TEXT_SCENE_COLOR);
        button.setText(text);
        return button;
    }

}
