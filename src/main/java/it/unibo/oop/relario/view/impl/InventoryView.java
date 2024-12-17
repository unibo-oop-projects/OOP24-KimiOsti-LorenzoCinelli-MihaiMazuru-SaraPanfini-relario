package it.unibo.oop.relario.view.impl;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.MainController;

import java.awt.*;

/**
 * View implementation for inventory navigation.
 */
public class InventoryView extends JPanel {
    private static final long serialVersionUID = 1L;

    // private final MainController controller;

    /**
     * Initializes the inventory view.
     * @param controller 
     */
    public InventoryView(final MainController controller) {
        // this.controller = controller;
        this.setLayout(new BorderLayout(10, 10));

        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        titlePanel.add(new JLabel("Inventory"));
        this.add(titlePanel, BorderLayout.NORTH);

        final JPanel itemListPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        itemListPanel.add(new JLabel("item list"));
        final var list = controller.getInventoryController().getItemsNames();
        for (final var name : list) {
            itemListPanel.add(new JButton(name));
        }
        itemListPanel.add(new JLabel(list.toString()));
        this.add(itemListPanel, BorderLayout.WEST);

        final JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        descriptionPanel.add(new JLabel("item description"));
        final int index = 0;
        final var description = controller.getInventoryController().getItemFullDescription(index);
        descriptionPanel.add(new JLabel(description));
        this.add(descriptionPanel, BorderLayout.CENTER);

        final JPanel equippedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        equippedPanel.add(new JLabel("equipped items"));
        this.add(equippedPanel, BorderLayout.EAST);

        final JPanel commandsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        commandsPanel.add(new JLabel("commands list"));
        commandsPanel.add(new JLabel("commands list"));
        commandsPanel.add(new JLabel("commands list"));
        commandsPanel.add(new JLabel("commands list"));
        this.add(commandsPanel, BorderLayout.SOUTH);
    }
}
