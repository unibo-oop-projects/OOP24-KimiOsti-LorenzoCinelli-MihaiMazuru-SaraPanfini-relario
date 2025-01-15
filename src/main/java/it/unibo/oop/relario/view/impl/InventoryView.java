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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

/**
 * View implementation for inventory navigation.
 */
public class InventoryView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final double CONTENT_RATIO = 0.6;
    private static final double COMMANDS_RATIO = 0.15;

    private final InventoryController inventory;
    private final Font font;
    private int buttonSelected;
    final JPanel contentPanel;
    
    private enum InventoryType {
        ITEM_LIST("Lista oggetti"),
        ITEM_DESCRIPTION("Descrizione oggetto"),
        EQUIPPED_ITEMS("Oggetti equipaggiati");

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
        this.contentPanel = new JPanel(new BorderLayout());
    }

    /**
     * Initializes the inventory view.
     */
    public void init() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.BLACK);
        //command panel
        var secondPanel = new JPanel();
        var label = new JLabel("↑↓ - spostarsi tra gli oggetti \t Enter - usa un oggetto \t Backspace - scarta un oggetto \t I - esci dall\'inventario");
        label.setForeground(Color.WHITE);
        label.setFont(font);
        secondPanel.add(label);
        secondPanel.setBackground(Color.BLACK);
        //
        this.add(setupTitle());
        this.add(contentPanel);
        this.refresh();
        //ridimensiona i pannelli
        var dim = new Dimension((int) (this.getWidth() * CONTENT_RATIO), (int) (this.getHeight() * CONTENT_RATIO));
        var dim2 = new Dimension((int) (this.getWidth()), (int) (this.getHeight() * COMMANDS_RATIO));
        secondPanel.setPreferredSize(dim2);
        contentPanel.setPreferredSize(dim);
        validate();
    }

    private JPanel setupTitle() {
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel titleLabel = new JLabel("Inventario");
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
        subpanel.setBackground(Color.BLACK);
        subpanel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(font);
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
        final var list = this.inventory.getItemsNames();
        final JRadioButton[] radioButtons = new JRadioButton[list.size()];
        final ButtonGroup buttonGroup = new ButtonGroup();

        final ActionListener radioButtonsListener = e -> {
            for (int i = 0; i < radioButtons.length; i++) {
                if (radioButtons[i].isSelected()) {
                    buttonSelected = i;
                    this.contentPanel.add(setupInventory(InventoryType.ITEM_DESCRIPTION), BorderLayout.CENTER);
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
        final var description = this.inventory.getItemFullDescription(buttonSelected);
        panel.add(addTextArea(description));
    }

    private void setupEquipped(final JPanel panel) {
        final var armor = "Armatura: " + this.inventory.getEquippedArmor();
        final var weapon = "Arma: " + this.inventory.getEquippedWeapon();
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
     * Refresh the inventory view with updated data.
     */
    public final void refresh() {
        this.contentPanel.add(setupInventory(InventoryType.ITEM_LIST), BorderLayout.WEST);
        this.contentPanel.add(setupInventory(InventoryType.ITEM_DESCRIPTION), BorderLayout.CENTER);
        this.contentPanel.add(setupInventory(InventoryType.EQUIPPED_ITEMS), BorderLayout.EAST);
        this.validate();
    }
}
