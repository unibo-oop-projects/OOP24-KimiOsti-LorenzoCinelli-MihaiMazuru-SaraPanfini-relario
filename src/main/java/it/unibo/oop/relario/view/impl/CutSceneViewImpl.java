package it.unibo.oop.relario.view.impl;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import it.unibo.oop.relario.controller.api.CutSceneController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.utils.impl.ResourceLocator;
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
    private static final String DEFEAT_STRING = "GAME OVER";

    private final CutSceneController controller;
    private final MainView mainView;

    /**
     * Creates a new cutscene panel.
     * @param controller is the main controller of the game.
     * @param view is the main view of the game.
     */
    public CutSceneViewImpl(final MainController controller, final MainView view) {
        this.controller = controller.getCutSceneController();
        this.mainView = view;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);
    }

    @Override
    public void showStartScene() {
        //panel nero
        //mostra immagini
        //mostra testo
        final var timer = new Timer(TRANSITION_DELAY, e -> this.controller.progress(GameState.GAME));
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void showNextRoomScene() {
        final var audio = SoundLocators.getAudio("door_sound");
        audio.start();
        this.fadeOutOverLastView();
        audio.close();
        this.controller.progress(GameState.GAME);
    }

    @Override
    public void showVictoryScene() {
        this.fadeOutOverLastView();
        //mostra immagini
        //mostra testo
        //mostra nuovo testo
        final var timer = new Timer(TRANSITION_DELAY, e -> this.controller.progress(GameState.MENU));
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void showDefeatScene() {
        this.fadeOutOverLastView();
        final var label = new JLabel(DEFEAT_STRING);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setFont(ResourceLocator.getGameFont(Constants.MONOSPACE_FONT));
        this.add(label);
        this.validate();
        final var timer = new Timer(TRANSITION_DELAY, e -> this.controller.progress(GameState.MENU));
        timer.setRepeats(false);
        timer.start();
    }

    private void fadeOutOverLastView() {
        final var pane = new JLayeredPane();
        final var panel = new JPanel();
        panel.setBackground(TRANSPARENT_BLACK);
        pane.add(this.mainView.getPanel(this.mainView.getCurrentPanel()), JLayeredPane.DEFAULT_LAYER);
        pane.add(panel, JLayeredPane.POPUP_LAYER);
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
