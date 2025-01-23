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
import it.unibo.oop.relario.utils.impl.FontHandler;
import it.unibo.oop.relario.view.api.InventoryViewFactory;

/**
 * Implementation of {@link InventoryViewFactory}.
 */
public final class InventoryViewFactoryImpl implements InventoryViewFactory {
    private static final String COMMANDS = """
        ↑↓ - spostarsi tra gli oggetti
        Enter - usa un oggetto
        Backspace - scarta un oggetto
        I - esci dall\'inventario
    """;
    private static final String TITLE = "Inventario                        Vita: ";
    private static final String ITEM_LIST = "Lista oggetti";
    private static final String ITEM_DESCRIPTION = "Descrizione oggetto";
    private static final String EQUIPPED_ITEMS = "Oggetti equipaggiati";
    private static final String ARMOR = "Armatura: ";
    private static final String WEAPON = "Arma: ";

    private final Font font;
    private final InventoryController inventory;

    /**
     * Creates an inventory view factory to populate the inventory view. 
     * @param inventory is the main controller of the game.
     */
    public InventoryViewFactoryImpl(final InventoryController inventory) {
        this.inventory = inventory;
        this.font = FontHandler.getFont(Constants.MONOSPACE_FONT);
    }

    @Override
    public JPanel createCommandPanel() {
        final var panel = new JPanel();
        final var commandsString = COMMANDS;
        final var label = new JLabel(commandsString);
        label.setForeground(Constants.TEXT_SCENE_COLOR);
        label.setFont(font);
        panel.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        panel.add(label);
        return panel;
    }

    @Override
    public JPanel createContentPanel() {
        final var panel = new JPanel(new BorderLayout());
        panel.setBackground(Constants.BACKGROUND_SCENE_COLOR);
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
        panel.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        return panel;
    }

    private JTextArea addTextArea(final String string) {
        final JTextArea area = new JTextArea(string);
        area.setFont(font);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setForeground(Constants.TEXT_SCENE_COLOR);
        area.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        area.setEditable(false);
        area.setFocusable(false);
        return area;
    }

    private JPanel createTitlePanel() {
        final var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final var label = new JLabel(TITLE + this.inventory.getLife());
        label.setForeground(Constants.TEXT_SCENE_COLOR);
        label.setFont(font);
        panel.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        panel.add(label);
        return panel;
    }

    private JPanel createListPanel() {
        final var panel = createContentSubpanel(ITEM_LIST);
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
            radioButtons[i].setForeground(Constants.TEXT_SCENE_COLOR);
            radioButtons[i].setBackground(Constants.BACKGROUND_SCENE_COLOR);
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
        final var panel = createContentSubpanel(ITEM_DESCRIPTION);
        final var description = this.inventory.getItemFullDescription();
        subpanel.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.Y_AXIS));
        subpanel.add(this.addTextArea(description));
        panel.add(subpanel);
        return panel;
    }

    private JPanel createEquippedPanel() {
        final var panel = createContentSubpanel(EQUIPPED_ITEMS);
        final var armor = ARMOR + this.inventory.getEquippedArmor();
        final var weapon = WEAPON + this.inventory.getEquippedWeapon();
        final var subpanel = new JPanel();
        subpanel.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.Y_AXIS));
        subpanel.add(this.addTextArea(armor));
        subpanel.add(this.addTextArea(weapon));
        panel.add(subpanel);
        return panel;
    }

}
