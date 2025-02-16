package it.unibo.oop.relario.view.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import it.unibo.oop.relario.controller.api.CutSceneController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.utils.impl.ImageLocators;
import it.unibo.oop.relario.utils.impl.SoundLocators;
import it.unibo.oop.relario.view.api.CutSceneView;

/**
 * Implementation of {@link CutSceneView}.
 */
public final class CutSceneViewImpl extends JPanel implements CutSceneView {

    private enum Scene {
        INTRODUCTION,
        VICTORY_GOOD,
        VICTORY_BAD,
        DEFEAT;
    }

    private static final long serialVersionUID = 1L;
    private static final int SCENE_TRANSITION_DELAY = 4000;
    private static final int ROOM_TRANSITION_DELAY = 3000;
    private static final int INSETS = 10;
    private static final int NO_INSETS = 0;
    private static final double VOLUME = 1.0;
    private static final double SCENE_RATIO = 0.6;
    private static final double CHARACTER_RATIO = 0.075;
    private static final String CHARACTER_IMAGE_URL = "cutscene/character";
    private static final String DOOR_SOUND_URL = "cutscene/door_sound";
    private static final String DEFEAT_SOUND_URL = "cutscene/defeat_sound";
    private static final String VICTORY_SOUND_URL = "cutscene/victory_sound";
    private static final Map<Scene, String> MESSAGES = Map.of(
        Scene.INTRODUCTION, """
        <html>Il vecchio re di Relario sentiva vicino il momento della sua fine,<br>
        ma sapeva di non aver lasciato eredi al trono.<br>
        Per questo ha deciso che chiunque sarebbe riuscito a superare tutte le prove<br>
        del suo castello avrebbe ereditato il suo titolo.<br>
        Relano decide di tentare nell'impresa.</html>
        """,
        Scene.VICTORY_GOOD, """
        <html><center>HAI VINTO<br>
        Un regno di pace vede oggi la luce a Relario!</center></html>
        """,
        Scene.VICTORY_BAD, """
        <html><center>HAI VINTO<br>
        Un trono conquistato con violenza potr&agrave portare solo dolore ai suoi sudditi...</center></html>
        """,
        Scene.DEFEAT, "<html><center>HAI PERSO</center></html>"
    );
    private static final Map<Scene, String> URL = Map.of(
        Scene.INTRODUCTION, "cutscene/castle_zoom",
        Scene.VICTORY_GOOD, "cutscene/throne_good",
        Scene.VICTORY_BAD, "cutscene/throne_corrupted",
        Scene.DEFEAT, "cutscene/skull"
    );

    private final transient CutSceneController controller;

    /**
     * Creates a new cutscene panel.
     * @param controller is the main controller of the game.
     */
    public CutSceneViewImpl(final MainController controller) {
        this.controller = controller.getCutSceneController();
        this.setLayout(new GridBagLayout());
        this.setBackground(Constants.BACKGROUND_SCENE_COLOR);
    }

    @Override
    public void showStartScene() {
        this.sceneLoader(Scene.INTRODUCTION);
        final var timer = new Timer(Constants.INTRODUCTION_SCENE_TIME, e -> this.controller.progressView(GameState.GAME));
        timer.setRepeats(false);
        timer.start();
        this.controller.progressGame(GameState.GAME);
    }

    @Override
    public void showNextRoomScene() {
        final var audio = SoundLocators.getAudio(DOOR_SOUND_URL, VOLUME);
        audio.start();
        final var timer = new Timer(ROOM_TRANSITION_DELAY, e -> {
            audio.close();
            this.controller.progressView(GameState.GAME);
        });
        timer.setRepeats(false);
        timer.start();
        this.controller.progressGame(GameState.GAME);

        this.removeAll();
        this.repaint();
        this.validate();
    }

    @Override
    public void showFinalScene(final GameState state) {
        final Scene scene;
        String url = VICTORY_SOUND_URL;
        switch (state) {
            case VICTORY_GOOD -> scene = Scene.VICTORY_GOOD;
            case VICTORY_BAD -> scene = Scene.VICTORY_BAD;
            default -> {
                scene = Scene.DEFEAT;
                url = DEFEAT_SOUND_URL;
            }
        }
        this.sceneLoader(scene);
        final var audio = SoundLocators.getAudio(url, VOLUME);
        audio.start();
        final var timer = new Timer(SCENE_TRANSITION_DELAY, e -> {
            audio.close();
            this.controller.progressView(GameState.MENU);
        });
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
}
