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

    private int buttonSelected;
    private final Font font;

    private enum InventoryType {
        ITEM_LIST("Item list"),
        ITEM_DESCRIPTION("Item description"),
        EQUIPPED_ITEMS("Equipped items");

        private final String string;

        InventoryType(final String string) {
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
        this.buttonSelected = 0;

        this.add(setupTitle(), BorderLayout.NORTH);
        this.add(setupInventory(inventory, InventoryType.ITEM_LIST), BorderLayout.WEST);
        this.add(setupInventory(inventory, InventoryType.ITEM_DESCRIPTION), BorderLayout.CENTER);
        this.add(setupInventory(inventory, InventoryType.EQUIPPED_ITEMS), BorderLayout.EAST);
    }

    private JPanel setupTitle() {
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel titleLabel = new JLabel(convertToHtmlString("<b>Inventory</b>"));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(font);
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(titleLabel);
        return titlePanel;
    }

    private JPanel setupInventory(final InventoryController inventory, final InventoryType type) {
        final JPanel panel = new JPanel();
        final JPanel subpanel = new JPanel();
        final JLabel label = new JLabel(type.toString());
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(font);
        subpanel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);
        panel.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label);
        switch (type) {
            case ITEM_LIST:
                setupList(inventory, panel);
                break;
            case ITEM_DESCRIPTION:
                label.setAlignmentX(CENTER_ALIGNMENT);
                setupDescription(inventory, panel);
                break;
            case EQUIPPED_ITEMS:
                setupEquipped(inventory, panel);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        panel.add(subpanel);
        return panel;
    }

    private void setupList(final InventoryController inventory, final JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        final var list = inventory.getItemsNames();
        final JRadioButton[] radioButtons = new JRadioButton[list.size()];
        final ButtonGroup buttonGroup = new ButtonGroup();

        final ActionListener radioButtonsListener = e -> {
            for (int i = 0; i < radioButtons.length; i++) {
                if (radioButtons[i].isSelected()) {
                    buttonSelected = i;
                    this.add(setupInventory(inventory, InventoryType.ITEM_DESCRIPTION), BorderLayout.CENTER);
                    this.validate();
                }
            }
        };

        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = new JRadioButton(list.get(i));
            buttonGroup.add(radioButtons[i]);
            radioButtons[i].addActionListener(radioButtonsListener);
            radioButtons[i].setForeground(Color.WHITE);
            radioButtons[i].setBackground(Color.BLACK);
            panel.add(radioButtons[i]);
        }
        radioButtons[buttonSelected].setSelected(true);
    }

    private void setupDescription(final InventoryController inventory, final JPanel panel) {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        final var description = inventory.getItemFullDescription(buttonSelected);
        final JLabel descriptionLabel = new JLabel(convertToHtmlString(description));
        descriptionLabel.setForeground(Color.WHITE);
        panel.add(descriptionLabel);
    }

    private void setupEquipped(final InventoryController inventory, final JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    private String convertToHtmlString(final String string) {
        final String result = "<html>" + string + "</html>";
        return result.replace("\n", "<br>");
    }

    /**
     * Returns the position of the selected item in the inventory.
     * @return the index of the selected item.
     */
    public final int getSelectedItem() {
        return buttonSelected;
    }
}
