package it.unibo.oop.relario.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.ResourceLocator;
import it.unibo.oop.relario.view.api.InventoryViewFactory;

/**
 * Implementation of {@link InventoryViewFactory}.
 */
public final class InventoryViewFactoryImpl implements InventoryViewFactory {

    private final Font font;
    private final InventoryController inventory;

    /**
     * Creates an inventory view factory to populate the inventory view. 
     * @param inventory is the main controller of the game.
     */
    public InventoryViewFactoryImpl(final InventoryController inventory) {
        this.inventory = inventory;
        this.font = ResourceLocator.getGameFont(Constants.MONOSPACE_FONT);
    }

    @Override
    public JPanel createCommandPanel() {
        final var panel = new JPanel();
        final var commandsString = """
            ↑↓ - spostarsi tra gli oggetti
            Enter - usa un oggetto
            Backspace - scarta un oggetto
            I - esci dall\'inventario
        """;
        final var label = new JLabel(commandsString);
        label.setForeground(Color.WHITE);
        label.setFont(font);
        panel.setBackground(Color.BLACK);
        panel.add(label);
        return panel;
    }

    @Override
    public JPanel createContentPanel() {
        final var panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.add(createTitlePanel(), BorderLayout.NORTH);
        panel.add(createListPanel(), BorderLayout.WEST);
        panel.add(createDescriptionPanel(), BorderLayout.CENTER);
        panel.add(createEquippedPanel(), BorderLayout.EAST);
        return panel;
    }

    private JPanel createContentSubpanel(final String text) {
        final var panel = new JPanel();
        final var label = new JLabel(text);
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(font);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        return panel;
    }

    private JTextArea addTextArea(final String string) {
        final JTextArea area = new JTextArea(string);
        area.setFont(font);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setForeground(Color.WHITE);
        area.setBackground(Color.BLACK);
        area.setEditable(false);
        area.setFocusable(false);
        return area;
    }

    private JPanel createTitlePanel() {
        final var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final var label = new JLabel("Inventario                        Vita: " + this.inventory.getLife());
        label.setForeground(Color.WHITE);
        label.setFont(font);
        panel.setBackground(Color.BLACK);
        panel.add(label);
        return panel;
    }

    private JPanel createListPanel() {
        final var panel = createContentSubpanel("Lista oggetti");
        final var list = this.inventory.getItemsNames();
        final var radioButtons = new JRadioButton[list.size()];
        final var buttonGroup = new ButtonGroup();

        final ActionListener radioButtonsListener = e -> {
            for (int i = 0; i < radioButtons.length; i++) {
                if (radioButtons[i].isSelected()) {
                    inventory.setSelectedItemIndex(i);
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
            radioButtons[i].setFocusable(false);
            panel.add(radioButtons[i]);
        }
        if (radioButtons.length > 0) {
            radioButtons[inventory.getSelectedItemIndex()].setSelected(true);
        }
        return panel;
    }

    private JPanel createDescriptionPanel() {
        final var subpanel = new JPanel();
        final var panel = createContentSubpanel("Descrizione oggetto");
        final var description = this.inventory.getItemFullDescription();
        subpanel.setBackground(Color.BLACK);
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.Y_AXIS));
        subpanel.add(this.addTextArea(description));
        panel.add(subpanel);
        return panel;
    }

    private JPanel createEquippedPanel() {
        final var panel = createContentSubpanel("Oggetti equipaggiati");
        final var armor = "Armatura: " + this.inventory.getEquippedArmor();
        final var weapon = "Arma: " + this.inventory.getEquippedWeapon();
        final var subpanel = new JPanel();
        subpanel.setBackground(Color.BLACK);
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.Y_AXIS));
        subpanel.add(this.addTextArea(armor));
        subpanel.add(this.addTextArea(weapon));
        panel.add(subpanel);
        return panel;
    }

}
