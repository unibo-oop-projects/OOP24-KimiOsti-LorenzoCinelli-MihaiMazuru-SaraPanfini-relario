package it.unibo.oop.relario.view.impl;

import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.view.api.CombatScene;

/**
 * View implementation for the combat phase of the game.
 */
public class CombatView extends JPanel {

    private static final long serialVersionUID = 1L;

    private final CombatController controller;
    private final JPanel upperPadding;
    private final CombatScene centralScene;
    private final JPanel commands;
    private final JPanel message;

    public CombatView(final CombatController controller) {
        this.controller = controller;

        this.upperPadding = new JPanel();
        this.commands = new JPanel();
        this.message = new JPanel();
    }
}
