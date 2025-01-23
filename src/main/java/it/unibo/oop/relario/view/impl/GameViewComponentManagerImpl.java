package it.unibo.oop.relario.view.impl;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.view.api.GameViewComponentManager;

/**
 * Implementation for the manager of game state view components.
 */
public final class GameViewComponentManagerImpl implements GameViewComponentManager {

    private static final float TEXT_TO_COMPONENT_RATIO = 0.33f;

    @Override
    public JPanel getGamePanel() {
        final var panel = new JPanel();
        panel.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        return panel;
    }

    @Override
    public void resizeComponent(final JComponent component, final int width, final int height) {
        component.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void showText(final JComponent component, final String text) {
        component.add(this.getCustomLabel(
            component.getHeight() * TEXT_TO_COMPONENT_RATIO,
            text
        ));
        component.revalidate();
        component.repaint();
    }

    @Override
    public BackgroundTile getBackgroundTile(final Image texture, final int tileDimension) {
        return new BackgroundTile(texture.getScaledInstance(
            tileDimension,
            tileDimension,
            Image.SCALE_SMOOTH 
        ));
    }

    @Override
    public ForegroundTile getForegroundTile(final Image texture, final int tileDimension) {
        return new ForegroundTile(texture.getScaledInstance(
            tileDimension,
            tileDimension,
            Image.SCALE_SMOOTH
        ));
    }

    private JLabel getCustomLabel(final float textSize, final String text) {
        final var label = new JLabel();
        label.setFont(Constants.FONT.deriveFont(textSize));
        label.setBackground(Constants.BACKGROUND_SCENE_COLOR);
        label.setForeground(Constants.TEXT_SCENE_COLOR);
        label.setText(text);
        return label;
    }

}
