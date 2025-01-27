package it.unibo.oop.relario.view.impl;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.menu.Command;
import it.unibo.oop.relario.model.menu.MenuElement;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.Event;
import it.unibo.oop.relario.utils.impl.GameKeyListener;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.utils.impl.ImageLocators;
import it.unibo.oop.relario.utils.impl.SoundLocators;

/**
 * View implementation for the main menu.
 */
public final class MenuView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int INSETS = 3;
    private static final double RATIO = 0.5;
    private static final String LOGO = "logo/logo";
    private static final String SONG_URL = "soundtrack/menu";
    private static final float FONT_SIZE = 28f;
    private static final float FONT_GUIDE_SIZE = 6f;
    private static final double VOLUME = 0.5;

    private final transient MainController controller;
    private transient Clip song;

    /**
     * Initializes a new menu view.
     * @param controller is the main controller.
     * @param elements are the menu elements that need to be added to the view.
     */
    public MenuView(final MainController controller, final List<MenuElement> elements) {
        this.controller = controller;
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridc = new GridBagConstraints();
        gridc.gridy = 0;
        gridc.insets = new Insets(INSETS, INSETS, INSETS, INSETS);
        gridc.fill = GridBagConstraints.CENTER;

        if (elements.get(0).getElemCommad().equals(Command.PLAY)) {
            final ImageIcon image = ImageLocators.getFixedSizeImage(LOGO, Constants.IMAGE_EXTENSION, RATIO, RATIO);
            final JLabel logo = new JLabel(image);
            this.add(logo, gridc);
        }
        gridc.gridy++;
        gridc.fill = GridBagConstraints.CENTER;

        for (final var elem: elements) {
            this.add(createButton(elem), gridc);
            gridc.gridy++;
        }

        this.setBackground(Color.BLACK);
        this.addKeyListener(new GameKeyListener(controller.getMenuController()));
    }

    /**
     * Starts the menu sound track.
     */
    public void startSoundTrack() {
        this.song = SoundLocators.getAudio(SONG_URL, VOLUME);
        this.song.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private JButton createButton(final MenuElement elem) {
        final JButton myButton = new JButton(elem.getElemName());
        myButton.setFont(Constants.FONT.deriveFont(FONT_SIZE));
        myButton.addActionListener(e -> {
            if (e.getActionCommand().equals(Command.PLAY.getName())) {
                final Timer timer = new Timer(Constants.INTRODUCTION_SCENE_TIME, e1 -> this.song.close());
                timer.setRepeats(false);
                timer.start();
                this.controller.getCutSceneController().show(GameState.MENU);
            } else if (e.getActionCommand().equals(Command.INFO.getName())) {
                final Label l = new Label("How to play the game");
                l.setFont(Constants.FONT.deriveFont(FONT_GUIDE_SIZE));
                JOptionPane.showConfirmDialog(this, l, 
                "Guide", JOptionPane.CLOSED_OPTION);
            } else if (e.getActionCommand().equals(Command.CLOSE.getName())) {
                this.controller.getMenuController().notify(Event.ESCAPE);
            } else if (e.getActionCommand().equals(Command.QUIT.getName())) {
                final int dialogResult = JOptionPane.showConfirmDialog(this,
                    "Sei sicuro di voler abbandonare la partita?", "Attenzione",
                    JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    for (final var f : Frame.getFrames()) {
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                    }
                } else {
                    this.requestFocus(true);
                }
            }
        });
        return myButton;
    }

}
