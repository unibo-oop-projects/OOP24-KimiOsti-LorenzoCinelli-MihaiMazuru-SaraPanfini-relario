package it.unibo.oop.relario.view.api;

import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Interface of a game view component manager class.
 */
public interface GameViewComponentManager {
    /**
     * Creates an empty container for the game view.
     * @return an empty panel.
     */
    JPanel getGamePanel();

    /**
     * Resizes a given view component.
     * @param component the component to be resized.
     * @param width the component's desired width.
     * @param height the component's desired height.
     */
    void resizeComponent(JComponent component, int width, int height);

    /**
     * Creates a background tile.
     * @param texture the texture to be represented.
     * @param tileDimension the dimension to which the square texture will be scaled.
     * @return a panel rendering the given texture scaled to the right dimension.
     */
    JPanel getBackgroundTile(Image texture, int tileDimension);

    /**
     * Creates a foreground tile.
     * @param texture the texture to be represented.
     * @param tileDimension the dimension to which the square texture will be scaled.
     * @return a label rendering the given texture scaled to the right dimension.
     */
    JLabel getForegroundTile(Image texture, int tileDimension);

    /**
     * Creates a custom label, representing a text of a given size.
     * @param textSize the text desired size.
     * @param text the text to be shown.
     * @return a label containing the given text, of the given size.
     */
    JLabel getCustomLabel(float textSize, String text);
}
