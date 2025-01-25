package it.unibo.oop.relario.view.api;

import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JPanel;

import it.unibo.oop.relario.view.impl.BackgroundTile;
import it.unibo.oop.relario.view.impl.ForegroundTile;

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
     * Shows some text in a given component.
     * @param component the component in which text has to be displayed.
     * @param text the text to be displayed.
     */
    void showText(JComponent component, String text);

    /**
     * Creates a background tile.
     * @param texture the texture to be represented.
     * @param tileDimension the dimension to which the square texture will be scaled.
     * @return a panel rendering the given texture scaled to the right dimension.
     */
    BackgroundTile getBackgroundTile(Image texture, int tileDimension);

    /**
     * Creates a foreground tile.
     * @param texture the texture to be represented.
     * @param tileDimension the dimension to which the square texture will be scaled.
     * @return a label rendering the given texture scaled to the right dimension.
     */
    ForegroundTile getForegroundTile(Image texture, int tileDimension);

}
