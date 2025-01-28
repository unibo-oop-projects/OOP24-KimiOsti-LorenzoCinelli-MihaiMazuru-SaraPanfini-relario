package it.unibo.oop.relario.view.impl;

import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibo.oop.relario.utils.impl.Constants;

/**
 * A {@link JFrame} containing the user guide.
 */
public final class UserGuide extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String FRAME_NAME = "Guida Utente";
    private static final String GUIDE_TITLE = "GUIDA UTENTE";
    private static final double SCREEN_TO_GUIDE_RATIO = 2;
    private static final float TITLE_FONT_SIZE = 20f;
    private static final float TEXT_FONT_SIZE = 16f;

    private final JPanel guideContainer;

    /**
     * Instantiates and shows a user guide frame.
     */
    public UserGuide() {
        super(FRAME_NAME);

        this.guideContainer = new JPanel();
        this.guideContainer.setLayout(new BoxLayout(this.guideContainer, BoxLayout.Y_AXIS));
        this.guideContainer.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        this.addGuideToContainer();

        this.add(new JScrollPane(this.guideContainer));
        this.setSize(
            (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / SCREEN_TO_GUIDE_RATIO),
            (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / SCREEN_TO_GUIDE_RATIO)
        );
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void addGuideToContainer() {
        this.guideContainer.add(this.formatAsTitle(GUIDE_TITLE));
        final var reader = new BufferedReader(new InputStreamReader(
            ClassLoader.getSystemResourceAsStream(Constants.USER_GUIDE_URL)
        ));
        reader.lines().forEach(line -> this.guideContainer.add(this.formatAsText(line)));
    }

    private JLabel formatAsTitle(final String text) {
        return this.getCustomLabel(
            text,
            Constants.FONT.deriveFont(Font.BOLD).deriveFont(TITLE_FONT_SIZE)
        );
    }

    private JLabel formatAsText(final String text) {
        return this.getCustomLabel(
            text,
            Constants.FONT.deriveFont(TEXT_FONT_SIZE)
        );
    }

    private JLabel getCustomLabel(final String text, final Font font) {
        final var label = new JLabel();
        label.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        label.setForeground(Constants.TEXT_SCENE_COLOR);
        label.setFont(font);
        label.setText(text);
        return label;
    }
}
