package it.unibo.oop.relario.view.impl;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.utils.impl.AttackDirection;
import it.unibo.oop.relario.utils.impl.Constants;

/**
 * Implementation for the central scene of combat environments.
 */
public final class CombatScene extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int PANEL_TO_CONTENT_RATIO = 7;
    private static final int INFO_TO_FONT_RATIO = 4;
    private static final double EMPTY_SPACE_TO_TEXTURE_RATIO = 1.1;
    private static final int INFO_ROWS = 1;
    private static final int INFO_COLS = 3;
    private static final int INFO_GAP = 0;

    private final transient CombatController controller;
    private final JPanel enemyInfo;
    private final JPanel enemyTextureContainer;
    private final JPanel playerInfo;

    /**
     * Creates the combat scene.
     * @param controller the controller to which content queries are directed.
     */
    public CombatScene(final CombatController controller) {
        this.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        this.controller = controller;

        this.enemyInfo = this.getInfoPanel();
        this.add(this.enemyInfo);

        this.enemyTextureContainer = new JPanel();
        this.enemyTextureContainer.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        this.add(this.enemyTextureContainer);

        this.playerInfo = this.getInfoPanel();
        this.add(this.playerInfo);
    }

    /**
     * Updates the combat scene.
     * @param direction the direction of the attack.
     */
    public void update(final AttackDirection direction) {
        this.resizePanels();
        this.updateEnemyInfo();
        this.updateEnemyTexture(direction);
        this.updatePlayerInfo();
    }

    private JPanel getInfoPanel() {
        final var panel = new JPanel(new GridLayout(INFO_ROWS, INFO_COLS, INFO_GAP, INFO_GAP));
        panel.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        return panel;
    }

    private void resizePanels() {
        this.enemyInfo.setPreferredSize(new Dimension(
            (int) this.getPreferredSize().getWidth(),
            (int) (this.getPreferredSize().getHeight() / PANEL_TO_CONTENT_RATIO)
        ));
        this.playerInfo.setPreferredSize(new Dimension(
            (int) this.getPreferredSize().getWidth(),
            (int) (this.getPreferredSize().getHeight() / PANEL_TO_CONTENT_RATIO)
        ));
        this.enemyTextureContainer.setPreferredSize(new Dimension(
            (int) this.getPreferredSize().getWidth(),
            (int) (
                (this.getPreferredSize().getHeight()
                - this.enemyInfo.getPreferredSize().getHeight()
                - this.playerInfo.getPreferredSize().getHeight())
                / EMPTY_SPACE_TO_TEXTURE_RATIO
            )
        ));
    }

    private void updateEnemyInfo() {
        this.enemyInfo.removeAll();
        this.enemyInfo.add(
            this.getTextLabel(
                "Nemico: " + this.controller.getEnemyName(),
                this.enemyInfo.getPreferredSize().getHeight()
            ),
            0
        );
        this.enemyInfo.add(
            this.getTextLabel(
                "Vita del nemico: " + this.controller.getEnemyLife(),
                this.enemyInfo.getPreferredSize().getHeight()
            ),
            1
        );
    }

    private void updateEnemyTexture(final AttackDirection direction) {
        final int textureDimension = (int) Math.min(
            this.enemyTextureContainer.getPreferredSize().getHeight() / EMPTY_SPACE_TO_TEXTURE_RATIO,
            this.enemyTextureContainer.getPreferredSize().getWidth() / EMPTY_SPACE_TO_TEXTURE_RATIO
        );
        this.enemyTextureContainer.removeAll();
        if (direction != AttackDirection.NONE) {
            final var animation = new CombatAnimationImpl(direction);
            this.enemyTextureContainer.add(animation);
            animation.start();
            final Timer timer = new Timer(Constants.COMBAT_ANIMATION_TIME, e -> this.enemyTextureContainer.remove(animation));
            timer.setRepeats(false);
            timer.start();
        }
        this.enemyTextureContainer.add(
            new ForegroundTile(
            this.controller.getEnemyTexture().getScaledInstance(
                textureDimension,
                textureDimension,
                Image.SCALE_SMOOTH
            )
        ));
    }

    private void updatePlayerInfo() {
        this.playerInfo.removeAll();
        this.playerInfo.add(
            this.getTextLabel(
                "Vita: " + this.controller.getPlayerLife(),
                this.playerInfo.getPreferredSize().getHeight()
            ),
            0
        );
        this.playerInfo.add(
            this.getTextLabel(
                "Arma: " + this.controller.getItem(),
                this.playerInfo.getPreferredSize().getHeight()
            ),
            1
        );
        this.playerInfo.add(
            this.getTextLabel(
                "Armatura: " + this.controller.getArmor(),
                this.playerInfo.getPreferredSize().getHeight()
            ),
            2
        );
    }

    private JLabel getTextLabel(final String text, final double panelHeight) {
        final var label = new JLabel();
        label.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        label.setForeground(Constants.TEXT_SCENE_COLOR);
        label.setFont(Constants.FONT.deriveFont((float) (panelHeight / INFO_TO_FONT_RATIO)));
        label.setText(text);
        return label;
    }

}
