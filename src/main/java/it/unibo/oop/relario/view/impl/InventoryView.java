package it.unibo.oop.relario.view.impl;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.ResourceLocator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

/**
 * View implementation for inventory navigation.
 */
public class InventoryView extends JPanel {
    private static final long serialVersionUID = 1L;

    private int buttonSelected = 0;
    private Font font;

    private enum InventoryType {
        ITEM_LIST("Item list"),
        ITEM_DESCRIPTION("Item description"),
        EQUIPPED_ITEMS("Equipped items");

        private final String string;

        private InventoryType(final String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    /**
     * Initializes the inventory view.
     * @param controller the main controller of the game.
     */
    public InventoryView(final MainController controller) {
        final InventoryController inventory = controller.getInventoryController();
        this.font = ResourceLocator.getGameFont(Constants.MONOSPACE_FONT);
        // this.font.deriveFont(1.1f);
        this.setLayout(new BorderLayout());
        
        this.add(setupTitle(), BorderLayout.NORTH);
        setupList(inventory);
        setupDescription(inventory);
        setupEquipped(inventory);
    }

    private JPanel setupTitle() {
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel titleLabel = new JLabel("Inventory");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(font);
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(titleLabel);
        return titlePanel;
    }

    private JPanel setupInventory(final InventoryController inventory, final InventoryType type) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        final JLabel label = new JLabel(type.toString());
        label.setForeground(Color.WHITE);
        label.setFont(font);
        panel.setBackground(Color.BLACK);
        panel.setAlignmentX(CENTER_ALIGNMENT);
        switch (type) {
            case ITEM_LIST:

                break;
            case ITEM_DESCRIPTION:

                break;
            case EQUIPPED_ITEMS:

                break;
            default:
                break;
        }
        panel.add(label);
        return panel;
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
