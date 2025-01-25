package it.unibo.oop.relario.view.impl;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import it.unibo.oop.relario.controller.api.CutSceneController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.utils.impl.ImageLocators;
import it.unibo.oop.relario.utils.impl.SoundLocators;
import it.unibo.oop.relario.view.api.CutSceneView;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation of {@link CutSceneView}.
 */
public final class CutSceneViewImpl extends JPanel implements CutSceneView {
    
    private enum Scene {
        INTRODUCTION,
        VICTORY,
        DEFEAT;
    }

    private static final long serialVersionUID = 1L;
    private static final Color TRANSPARENT_BLACK = new Color(0, 0, 0, 0);
    private static final int FADE_SPEED = 10;
    private static final int FADE_LIMIT = 256;
    private static final int SCENE_TRANSITION_DELAY = 9000;
    private static final int ROOM_TRANSITION_DELAY = 3000;
    private static final int INSETS = 10;
    private static final int NO_INSETS = 0;
    private static final double SCENE_RATIO = 0.6;
    private static final double CHARACTER_RATIO = 0.075;
    private static final String CHARACTER_IMAGE_URL = "cutscene/character";
    private static final String DOOR_SOUND_URL = "door_sound";
    private static final Map<Scene, String> MESSAGES = Map.of(
        Scene.INTRODUCTION, """
        <html>Il vecciho re di Relario sentiva vicino il momento della sua fine, \
        ma sapeva di non aver lasciato eredi al trono.<br>\
        Per questo ha deciso che chiunque riuscità a superare tutte le prove del suo castello \
        erediterà il suo titolo.<br>\
        Spero di essere all'altezza!</html>
        """,
        Scene.VICTORY, """
        <html><center>HAI VINTO<br>
        Hai ufficialmente ereditato il trono di Relario</center></html>
        """,
        Scene.DEFEAT, "<html><center>HAI PERSO</center></html>"
    );
    private static final Map<Scene, String> URL = Map.of(
        Scene.INTRODUCTION, "cutscene/castle_zoom",
        Scene.VICTORY, "cutscene/throne",
        Scene.DEFEAT, "cutscene/skull"
    );

    private final CutSceneController controller;
    private final MainView mainView;

    /**
     * Creates a new cutscene panel.
     * @param controller is the main controller of the game.
     * @param view is the main view of the game.
     */
    public CutSceneViewImpl(final MainController controller) {
        this.controller = controller.getCutSceneController();
        this.mainView = controller.getMainView();
        this.setLayout(new GridBagLayout());
        this.setBackground(Constants.BACKGROUND_SCENE_COLOR);
    }

    @Override
    public void showStartScene() {
        this.sceneLoader(Scene.INTRODUCTION);
        final var timer = new Timer(SCENE_TRANSITION_DELAY, e -> this.controller.progressView(GameState.GAME));
        timer.setRepeats(false);
        timer.start();
        this.controller.progressGame(GameState.GAME);
    }

    @Override
    public void showNextRoomScene() {
        final var audio = SoundLocators.getAudio(DOOR_SOUND_URL);
        audio.start();
        this.fadeOutOverLastView();
        final var timer = new Timer(ROOM_TRANSITION_DELAY, e -> this.controller.progressView(GameState.GAME));
        timer.setRepeats(false);
        timer.start();
        this.controller.progressGame(GameState.GAME);
    }

    @Override
    public void showVictoryScene() {
        this.fadeOutOverLastView();
        this.sceneLoader(Scene.VICTORY);
        final var timer = new Timer(SCENE_TRANSITION_DELAY, e -> this.controller.progressView(GameState.MENU));
        timer.setRepeats(false);
        timer.start();
        this.controller.progressGame(GameState.MENU);
    }

    @Override
    public void showDefeatScene() {
        this.fadeOutOverLastView();
        this.sceneLoader(Scene.DEFEAT);
        final var timer = new Timer(SCENE_TRANSITION_DELAY, e -> this.controller.progressView(GameState.MENU));
        timer.setRepeats(false);
        timer.start();
        this.controller.progressGame(GameState.MENU);
    }

    private void sceneLoader(final Scene scene) {
        this.removeAll();
        final var image = ImageLocators.getFixedSizeImage(URL.get(scene), SCENE_RATIO, SCENE_RATIO);
        this.add(new JLabel(image));

        final var labelConstraints = new GridBagConstraints();
        labelConstraints.gridy = 1;
        labelConstraints.insets = new Insets(INSETS, NO_INSETS, NO_INSETS, NO_INSETS);

        final var label = new JLabel();
        if (scene == Scene.INTRODUCTION) {
            final var playerImage = ImageLocators.getFixedSizeImage(CHARACTER_IMAGE_URL, CHARACTER_RATIO, CHARACTER_RATIO);
            label.setIcon(playerImage);
        }
        label.setText(MESSAGES.get(scene));
        label.setFont(Constants.FONT);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Constants.TEXT_SCENE_COLOR);
        this.add(label, labelConstraints);

        this.repaint();
        this.validate();
    }

    private void fadeOutOverLastView() {
        final var pane = new JLayeredPane();
        final var oldPanel = this.mainView.getPanel(this.mainView.getCurrentPanel());
        final var panel = new JPanel();

        oldPanel.setBounds(0, 0, this.getSize().width, this.getSize().height);
        panel.setBackground(TRANSPARENT_BLACK);
        panel.setBounds(0, 0, this.getSize().width, this.getSize().height);
        pane.setPreferredSize(this.getSize());
        pane.add(panel, JLayeredPane.DEFAULT_LAYER);
        pane.add(oldPanel, JLayeredPane.DEFAULT_LAYER);

        this.removeAll();
        this.add(pane);
        this.repaint();
        this.validate();

        final Timer timer = new Timer(FADE_SPEED, null);
        timer.addActionListener(e -> fadeOut(timer, panel));
        timer.start();
    }

    private void fadeOut(final Timer timer, final JPanel panel) {
        timer.start();
        final int alpha = panel.getBackground().getAlpha() + 1;
        if (alpha < FADE_LIMIT) {
            final Color color = new Color(
                panel.getBackground().getRed(),
                panel.getBackground().getGreen(),
                panel.getBackground().getBlue(),
                alpha
            );
            panel.setBackground(color);
            this.repaint();
            this.validate();
        } else {
            timer.stop();
        }
    }
}
