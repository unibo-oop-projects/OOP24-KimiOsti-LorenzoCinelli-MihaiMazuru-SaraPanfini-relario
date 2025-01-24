package it.unibo.oop.relario.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.utils.impl.Constants;

/**
 * Implementation for the central scene of combat environments.
 */
public final class CombatScene extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final double TEXTURE_TO_PANEL_RATIO = 0.5;
    private static final int INFO_ROWS = 1;
    private static final int INFO_COLS = 3;

    private final CombatController controller;

    /**
     * Creates the combat scene.
     * @param controller the controller to which content queries are directed.
     */
    public CombatScene(final CombatController controller) {
        this.setLayout(new BorderLayout());
        this.controller = controller;
    }

    /**
     * Updates the combat scene.
     */
    public void update() {
        this.removeAll();
        this.add(
            this.getInfoPanel(
                String.valueOf(this.controller.getEnemyLife()),
                this.controller.getEnemyName()
            ),
            BorderLayout.NORTH
        );
        this.add(
            this.getEnemyImagePanel(
                this.controller.getEnemyTexture().getScaledInstance(
                    (int) (this.getWidth() * TEXTURE_TO_PANEL_RATIO),
                    (int) (this.getHeight() * TEXTURE_TO_PANEL_RATIO),
                    Image.SCALE_SMOOTH
                )
            ),
            BorderLayout.CENTER
        );
        this.add(
            this.getInfoPanel(
                String.valueOf(this.controller.getPlayerLife()),
                this.controller.getItem(),
                this.controller.getArmor()
            ),
            BorderLayout.SOUTH
        );

        this.refresh();
    }

    private void refresh() {
        this.revalidate();
        this.repaint();
    }

    private JPanel getInfoPanel(final String... info) {
        final var panel = new JPanel(new GridLayout(INFO_ROWS, INFO_COLS));
        panel.setBackground(BACKGROUND_COLOR);
        for (int i = 0; i < info.length && i < INFO_COLS; i++) {
            final var text = new JLabel();
            text.setBackground(BACKGROUND_COLOR);
            text.setForeground(TEXT_COLOR);
            text.setFont(Constants.FONT);
            panel.add(text);
        }

        return panel;
    }

    private JPanel getEnemyImagePanel(final Image enemy) {
        return new BackgroundTile(enemy);
    }

}
