package it.unibo.oop.relario.view.impl;

import java.awt.Color;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.CutSceneController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.ResourceLocator;
import it.unibo.oop.relario.view.api.CutSceneView;
import it.unibo.oop.relario.view.api.MainView;

public final class CutSceneViewImpl extends JPanel implements CutSceneView {
    private static final Color BLACK_COLOR = new Color(0, 0, 0, 0);
    private static final int FADE_SPEED = 10;
    private static final int FADE_LIMIT = 256;
    
    private final CutSceneController controller;
    private final MainView mainView;
    
    public CutSceneViewImpl(final MainController controller, final MainView view) {
        this.controller = null;
        this.mainView = view;
        this.setBackground(Color.BLACK);
    }

    @Override
    public void showStartScene() {
        //panel nero
        //mostra immagini
        //mostra testo
        //mostra nuovo testo
        //passa a game controller
    }

    @Override
    public void showNextRoomScene() {
        final var audio = ResourceLocator.getAudio("door_sound");
        audio.start();
        this.fadeOutOverLastView();
        audio.close();
        
        /*this.controller.moveToNextRoom();
        this.controller.getGameController().run();*/ //TODO: questo lo deve fare il controller
    }

    @Override
    public void showVictoryScene() {
        this.fadeOutOverLastView();

        //mostra immagini
        //mostra testo
        //mostra nuovo testo
        //passa al menu
    }

    @Override
    public void showDefeatScene() {
        this.fadeOutOverLastView();
        
        //"GAME OVER"
        //passa al menu
    }

    private void fadeOutOverLastView() {
        final var pane = new JLayeredPane();
        final var panel = new JPanel();
        panel.setBackground(BLACK_COLOR);
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
