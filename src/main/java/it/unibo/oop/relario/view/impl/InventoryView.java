package it.unibo.oop.relario.view.impl;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import it.unibo.oop.relario.controller.api.MainController;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View implementation for inventory navigation.
 */
public class InventoryView extends JPanel {
    private static final long serialVersionUID = 1L;

    // private final MainController controller;
    private int buttonSelected = 0;

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
        JRadioButton[] radioButtons = new JRadioButton[list.size()];
        ButtonGroup buttonGroup = new ButtonGroup();
                
        ActionListener radioButtonsListener = e -> {
            for (int j = 0; j < radioButtons.length; j++) {
                if (radioButtons[j].isSelected()) {
                    buttonSelected = j;
                }
            }
        };
        radioButtons[buttonSelected].setSelected(true);

        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = new JRadioButton(list.get(i));
            buttonGroup.add(radioButtons[i]);
            itemListPanel.add(radioButtons[i]);
            radioButtons[i].addActionListener(radioButtonsListener);
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
    }

    /**
     * Returns the position of the selected item in the inventory.
     * @return the index of the selected item.
     */
    final public int getItemSelected() {
        return buttonSelected;
    }
}
