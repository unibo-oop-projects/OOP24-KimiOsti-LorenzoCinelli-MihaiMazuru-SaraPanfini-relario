package it.unibo.oop.relario.view.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import it.unibo.oop.relario.controller.api.CutSceneController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.FontHandler;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.utils.impl.ImageLocators;
import it.unibo.oop.relario.utils.impl.SoundLocators;
import it.unibo.oop.relario.view.api.CutSceneView;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation of {@link CutSceneView}.
 */
public final class CutSceneViewImpl extends JPanel implements CutSceneView {
    private static final long serialVersionUID = 1L;
    private static final Color TRANSPARENT_BLACK = new Color(0, 0, 0, 0);
    private static final int FADE_SPEED = 10;
    private static final int FADE_LIMIT = 256;
    private static final int TRANSITION_DELAY = 5000;
    private static final int INTRODUCTION_SCENE = 0;
    private static final int VICTORY_SCENE = 1;
    private static final int DEFEAT_SCENE = 2;
    private static final int INSETS = 10;
    private static final int NO_INSETS = 0;
    private static final double SCENE_RATIO = 0.6;
    private static final double CHARACTER_RATIO = 0.05;
    private static final List<String> MESSAGES = List.of(
        "INTRODUZIONE",
        "HAI VINTO",
        "GAME OVER"
    );
    private static final Map<String, String> URL = Map.of(
        "castle", "cutscene/castle_zoom",
        "victory", "cutscene/castle_zoom",
        "character", "cutscene/character",
        "door", "door_sound"
    );

    private final CutSceneController controller;
    private final MainView mainView;
    private final Font font;

    /**
     * Creates a new cutscene panel.
     * @param controller is the main controller of the game.
     * @param view is the main view of the game.
     */
    public CutSceneViewImpl(final MainController controller, final MainView view) {
        this.controller = controller.getCutSceneController();
        this.mainView = view;
        this.font = FontHandler.getFont(Constants.MONOSPACE_FONT);
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);
    }

    @Override
    public void showStartScene() {
        this.sceneLoader(INTRODUCTION_SCENE);
        final var timer = new Timer(TRANSITION_DELAY, e -> this.controller.progress(GameState.GAME));
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void showNextRoomScene() {
        final var audio = SoundLocators.getAudio(URL.get("door"));
        audio.start();
        this.fadeOutOverLastView();
        audio.close();
        this.controller.progress(GameState.GAME);
    }

    @Override
    public void showVictoryScene() {
        this.fadeOutOverLastView();
        this.sceneLoader(VICTORY_SCENE);
        final var timer = new Timer(TRANSITION_DELAY, e -> this.controller.progress(GameState.MENU));
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void showDefeatScene() {
        this.fadeOutOverLastView();
        final var label = new JLabel(MESSAGES.get(DEFEAT_SCENE));
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setFont(this.font);
        this.add(label);
        this.validate();
        final var timer = new Timer(TRANSITION_DELAY, e -> this.controller.progress(GameState.MENU));
        timer.setRepeats(false);
        timer.start();
    }

    private void sceneLoader(final int scene) {
        this.removeAll();

        final var image = ImageLocators.getFixedSizeImage(URL.get("castle"), SCENE_RATIO, SCENE_RATIO);
        this.add(new JLabel(image));

        final var labelConstraints = new GridBagConstraints();
        labelConstraints.gridy = 1;
        labelConstraints.insets = new Insets(INSETS, NO_INSETS, NO_INSETS, NO_INSETS);

        final var label = new JLabel();
        if (scene == INTRODUCTION_SCENE) {
            final var playerImage = ImageLocators.getFixedSizeImage(URL.get("player"), CHARACTER_RATIO, CHARACTER_RATIO);
            label.setIcon(playerImage);
        }
        label.setText(MESSAGES.get(scene));
        label.setFont(this.font);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE);
        this.add(label, labelConstraints);

        this.repaint();
        this.validate();
    }

    private void fadeOutOverLastView() {
        final var pane = new JLayeredPane();
        final var panel = new JPanel();
        panel.setBackground(TRANSPARENT_BLACK);
        pane.add(this.mainView.getPanel(this.mainView.getCurrentPanel()), JLayeredPane.DEFAULT_LAYER);
        pane.add(panel, JLayeredPane.POPUP_LAYER);
        this.removeAll();
        this.add(pane);
        this.validate();
        this.fadeOut(pane, panel, FADE_SPEED);
    }

    private void fadeOut(final JLayeredPane pane, final JPanel panel, final int speed) {
        int i = 0;
        while (i < FADE_LIMIT) {
            final Color color = new Color(panel.getBackground().getRed(),
                panel.getBackground().getGreen(), panel.getBackground().getBlue(), i);
            i++;
            panel.setBackground(color);
            pane.repaint();
            pane.validate();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
