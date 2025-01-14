package it.unibo.oop.relario.view.impl;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

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

    private final InventoryController inventory;
    private final Font font;
    private int buttonSelected;
    private JPanel listPanel;
    private JPanel descriptionPanel;
    private JPanel equippedPanel;

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
     * Creates the inventory view.
     * @param controller the main controller of the game.
     */
    public InventoryView(final MainController controller) {
        this.inventory = controller.getInventoryController();
        this.font = ResourceLocator.getGameFont(Constants.MONOSPACE_FONT);
        this.buttonSelected = 0;
    }

    /**
     * Initializes the inventory view.
     */
    public void init() {
        this.setLayout(new BorderLayout());
        this.refresh();
        this.add(setupTitle());
        this.add(listPanel);
        this.add(descriptionPanel);
        this.add(equippedPanel);
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

    private JPanel setupInventory(final InventoryType type) {
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
            case ITEM_LIST -> setupList(panel);
            case ITEM_DESCRIPTION -> {
                    label.setAlignmentX(CENTER_ALIGNMENT);
                    setupDescription(panel);
                }
            case EQUIPPED_ITEMS -> setupEquipped(panel);
            default -> { }
        }
        panel.add(subpanel);
        return panel;
    }

    private void setupList(final JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        final var list = this.inventory.getItemsNames();
        final JRadioButton[] radioButtons = new JRadioButton[list.size()];
        final ButtonGroup buttonGroup = new ButtonGroup();

        final ActionListener radioButtonsListener = e -> {
            for (int i = 0; i < radioButtons.length; i++) {
                if (radioButtons[i].isSelected()) {
                    buttonSelected = i;
                    this.descriptionPanel = setupInventory(InventoryType.ITEM_DESCRIPTION);
                    this.validate();
                }
            }
        };

        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = new JRadioButton(list.get(i));
            buttonGroup.add(radioButtons[i]);
            radioButtons[i].addActionListener(radioButtonsListener);
            radioButtons[i].setFont(font);
            radioButtons[i].setForeground(Color.WHITE);
            radioButtons[i].setBackground(Color.BLACK);
            panel.add(radioButtons[i]);
        }
        radioButtons[buttonSelected].setSelected(true);
    }

    private void setupDescription(final JPanel panel) {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        final var description = this.inventory.getItemFullDescription(buttonSelected);
        panel.add(addTextArea(description));
    }

    private void setupEquipped(final JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        final var armor = "Armor: " + this.inventory.getEquippedArmor();
        final var weapon = "Weapon: " + this.inventory.getEquippedWeapon();
        panel.add(addTextArea(armor));
        panel.add(addTextArea(weapon));
    }

    private JTextArea addTextArea(final String string) {
        final JTextArea area = new JTextArea(string);
        area.setFont(font);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setForeground(Color.WHITE);
        area.setBackground(Color.BLACK);
        area.setEditable(false);
        return area;
    }

    /**
     * Returns the position of the selected item in the inventory.
     * @return the index of the selected item.
     */
    public final int getSelectedItem() {
        return buttonSelected;
    }

    /**
     * Repaints the inventory view.
     */
    public final void refresh() {
        this.listPanel = setupInventory(InventoryType.ITEM_LIST);
        this.descriptionPanel = setupInventory(InventoryType.ITEM_DESCRIPTION);
        this.equippedPanel = setupInventory(InventoryType.EQUIPPED_ITEMS);
        this.validate();
    }
}
