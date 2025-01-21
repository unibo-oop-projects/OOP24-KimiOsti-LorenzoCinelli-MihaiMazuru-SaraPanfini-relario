package it.unibo.oop.relario.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.ResourceLocator;

/* [TODO]: implementare la classe */

/**
 * Implementation for the central scene of combat environments.
 */
public class CombatScene extends JPanel {

    private final static Color BACKGROUND_COLOR = Color.BLACK;
    private final static Color TEXT_COLOR = Color.WHITE;

    private final CombatController controller;
    private final Font font;

    public CombatScene(final CombatController controller) {
        this.setLayout(new BorderLayout());
        this.controller = controller;
        this.font = ResourceLocator.getGameFont(Constants.MONOSPACE_FONT);
    }

    public void update() {
        this.removeAll();
        this.add(
            this.getEnemyInfoPanel(
                this.controller.getEnemyLife(),
                this.controller.getEnemyName()
            ),
            BorderLayout.NORTH
        );
        this.add(
            this.getEnemyImagePanel(null /* [TODO]: aggiungere immagine del nemico */),
            BorderLayout.CENTER
        );
        this.add(
            this.getPlayerInfoPanel(
                this.controller.getPlayerLife(),
                this.controller.getItem(),
                this.controller.getArmor()
            ),
            BorderLayout.SOUTH
        );
    }

    private JPanel getEnemyInfoPanel(final int enemyLife, final String enemyName) {
        final var panel = new JPanel();

        return panel;
    }

    private JPanel getEnemyImagePanel(final Image enemy) {
        final var panel = new JPanel();

        return panel;
    }

    private JPanel getPlayerInfoPanel(final int playerLife, final String weapon, final String armor) {
        final var panel = new JPanel();

        return panel;
    }
    
}
