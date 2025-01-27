package it.unibo.oop.relario.view.impl;

import java.awt.Dimension;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.impl.CombatAction;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.utils.impl.AttackDirection;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.SoundLocators;

/**
 * View implementation for the combat phase of the game.
 */
public final class CombatView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final double VOLUME = 0.5;
    private static final int SCREEN_TO_SCENE_RATIO = 2;
    private static final int SIDE_COMPONENTS_RATIO = 4;
    private static final int COMPONENT_TO_FONT_RATIO = 5;
    private static final String FIGHT_SONG_URL = "soundtrack/normal_battle";
    private static final String BOSS_FIGHT_SONG_URL = "soundtrack/boss_battle";

    private final transient CombatController controller;
    private transient Clip song;
    private final JPanel upperPadding;
    private final CombatScene combatScene;
    private final JPanel messageContainer;
    private final JPanel commands;

    /**
     * Creates the panel showing combat scenes.
     * @param controller the controller receiving content queries.
     */
    public CombatView(final CombatController controller) {
        this.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        this.controller = controller;

        this.upperPadding = new JPanel();
        this.upperPadding.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        this.add(this.upperPadding);

        this.combatScene = new CombatScene(controller);
        this.add(this.combatScene);

        this.messageContainer = new JPanel();
        this.messageContainer.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        this.add(this.messageContainer);

        this.commands = this.createCommandPanel();
        this.add(this.commands);
    }

    /**
     * Updates the combat view.
     * @param direction the direction of the attack.
     */
    public void update(final AttackDirection direction) {
        this.resizePanels();
        /*this.centralScene.update(direction);*/
        this.updateMessage(this.controller.getCombatState());
    }

    /**
     * Starts the menu sound track.
     * @param type is the type of enemy in the fight.
     */
    public void startSoundTrack(final EnemyType type) {
        final String url = type.equals(EnemyType.BOSS) ? BOSS_FIGHT_SONG_URL : FIGHT_SONG_URL;
        this.song = SoundLocators.getAudio(url, VOLUME);
        this.song.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the menu sound track.
     */
    public void stopSoundTrack() {
        this.song.close();
    }

    private JPanel createCommandPanel() {
        final var panel = new JPanel();
        panel.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        final var attack = this.getGameButton("Attacca");
        attack.addActionListener(e -> this.controller.handleAction(CombatAction.ATTACK));
        final var inventory = this.getGameButton("Inventario");
        inventory.addActionListener(e -> this.controller.handleAction(CombatAction.OPEN_INVENTORY));
        final var mercy = this.getGameButton("Chiedi pieta'");
        mercy.addActionListener(e -> this.controller.handleAction(CombatAction.MERCY));
        final var buttons = List.of(attack, inventory, mercy);

        buttons.forEach(panel::add);

        return panel;
    }

    private JButton getGameButton(final String text) {
        final var button = new JButton();
        button.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        button.setForeground(Constants.TEXT_SCENE_COLOR);
        button.setFont(Constants.FONT);
        button.setText(text);
        return button;
    }

    private void resizePanels() {
        this.combatScene.setPreferredSize(new Dimension(
            this.getWidth() / SCREEN_TO_SCENE_RATIO,
            this.getHeight() / SCREEN_TO_SCENE_RATIO
        ));
        final var sideComponentDim = new Dimension(
            this.getWidth(),
            (int) ((this.getHeight() - this.combatScene.getPreferredSize().getHeight()) / SIDE_COMPONENTS_RATIO)
        );
        this.upperPadding.setPreferredSize(sideComponentDim);
        this.messageContainer.setPreferredSize(sideComponentDim);
        this.commands.setPreferredSize(sideComponentDim);
        this.revalidate();
        this.repaint();
    }

    private void updateMessage(final String msg) {
        this.messageContainer.removeAll();
        final var label = new JLabel();
        label.setForeground(Constants.TEXT_SCENE_COLOR);
        label.setFont(Constants.FONT.deriveFont(
            (float) (this.messageContainer.getPreferredSize().getHeight() / COMPONENT_TO_FONT_RATIO)
        ));
        label.setText(msg);
        this.messageContainer.add(label);
    }
}
