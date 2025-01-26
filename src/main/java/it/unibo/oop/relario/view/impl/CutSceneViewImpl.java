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
        VICTORY,
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
    private static final String DOOR_SOUND_URL = "door_sound";
    private static final Map<Scene, String> MESSAGES = Map.of(
        Scene.INTRODUCTION, """
        <html>Il vecchio re di Relario sentiva vicino il momento della sua fine,<br>
        ma sapeva di non aver lasciato eredi al trono.<br>
        Per questo ha deciso che chiunque sarebbe riuscito a superare tutte le prove<br>
        del suo castello avrebbe ereditato il suo titolo.<br>
        Relano decide di tentare nell'impresa.</html>
        """,
        Scene.VICTORY, """
        <html><center>HAI VINTO<br>
        Hai ufficialmente ereditato il trono di Relario!</center></html>
        """,
        Scene.DEFEAT, "<html><center>HAI PERSO</center></html>"
    );
    private static final Map<Scene, String> URL = Map.of(
        Scene.INTRODUCTION, "cutscene/castle_zoom",
        Scene.VICTORY, "cutscene/throne",
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
    public void showVictoryScene() {
        this.sceneLoader(Scene.VICTORY);
        final var timer = new Timer(SCENE_TRANSITION_DELAY, e -> this.controller.progressView(GameState.MENU));
        timer.setRepeats(false);
        timer.start();
        this.controller.progressGame(GameState.MENU);
    }

    @Override
    public void showDefeatScene() {
        this.sceneLoader(Scene.DEFEAT);
        final var timer = new Timer(SCENE_TRANSITION_DELAY, e -> this.controller.progressView(GameState.MENU));
        timer.setRepeats(false);
        timer.start();
        this.controller.progressGame(GameState.MENU);
    }

    private void sceneLoader(final Scene scene) {
        this.removeAll();
        final var image = ImageLocators.getFixedSizeImage(URL.get(scene), Constants.IMAGE_EXTENSION, SCENE_RATIO, SCENE_RATIO);
        this.add(new JLabel(image));

        final var labelConstraints = new GridBagConstraints();
        labelConstraints.gridy = 1;
        labelConstraints.insets = new Insets(INSETS, NO_INSETS, NO_INSETS, NO_INSETS);

        final var label = new JLabel();
        if (scene == Scene.INTRODUCTION) {
            final var playerImage = ImageLocators.getFixedSizeImage(CHARACTER_IMAGE_URL, Constants.IMAGE_EXTENSION,
                CHARACTER_RATIO, CHARACTER_RATIO);
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
