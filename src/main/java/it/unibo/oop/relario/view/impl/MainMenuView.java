package it.unibo.oop.relario.view.impl;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.MainMenuController;

/**
 * View implementation for the main menu.
 */
public final class MainMenuView extends JPanel {

    private static final long serialVersionUID = 1L;
    private final transient MainMenuController controller;

    /**
     * Initializes the main menu.
     * @param controller is the controller of the main menu.
     */
    public MainMenuView(final MainMenuController controller) {
        this.controller = controller;
        final JPanel myPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.insets = new Insets(1, 1, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;

        final JButton play = new JButton("PLAY");
        myPanel.add(play, c);
        c.gridy++;

        play.addActionListener(e -> { 
            this.controller.progress(); 
        });

        final JButton quit = new JButton("QUIT");
        myPanel.add(quit, c);
        c.gridy++;

        quit.addActionListener(e -> { 
            this.controller.exit(); 
        });

        this.setLayout(new BorderLayout());
        this.add(myPanel, BorderLayout.CENTER);
    }
}
