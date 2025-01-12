package it.unibo.oop.relario.view.impl;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.controller.api.MainController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

/**
 * View implementation for inventory navigation.
 */
public class InventoryView extends JPanel {
    private static final long serialVersionUID = 1L;

    private int buttonSelected = 0;

    /**
     * Initializes the inventory view.
     * @param controller 
     */
    public InventoryView(final MainController controller) {
        final InventoryController inventory = controller.getInventoryController();
        this.setLayout(new BorderLayout());
        setupTitle();
        setupList(inventory);
        setupDescription(inventory);
        setupEquipped(inventory);
    }

    private void setupTitle() {
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        titlePanel.add(new JLabel("Inventory"));
        this.add(titlePanel, BorderLayout.NORTH);
    }

    private void setupList(final InventoryController inventory) {
        final JPanel itemListPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        itemListPanel.add(new JLabel("item list"));
        final var list = inventory.getItemsNames();
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

    }

    private void setupDescription(final InventoryController inventory) {
        final JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        descriptionPanel.add(new JLabel("item description"));
        final int index = 0;
        final var description = inventory.getItemFullDescription(index);
        descriptionPanel.add(new JLabel(description));
        this.add(descriptionPanel, BorderLayout.CENTER);
    }

    private void setupEquipped(final InventoryController inventory) {
        final JPanel equippedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        equippedPanel.add(new JLabel("equipped items"));
        this.add(equippedPanel, BorderLayout.EAST);
    }

    /**
     * Returns the position of the selected item in the inventory.
     * @return the index of the selected item.
     */
    public final int getSelectedItem() {
        return buttonSelected;
    }
}
