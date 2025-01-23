package it.unibo.oop.relario.utils.impl;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

/**
 * Locator class for images.
 */
public final class ImageLocators {
    private static final String IMAGE_BASE_URL = "src/main/resources/img/";
    private static final String IMAGE_EXTENSION = ".png";

    private ImageLocators() { }

    /**
     * Returns an image icon with fixed dimension to screen size.
     * @param path the relative path of the resource.
     * @param horizontalRatio is the horizontal ratio to screen size.
     * @param verticalRatio is the vertical ratio to screen size.
     * @return the image icon of the resource in path.
     */
    public static ImageIcon getFixedSizeImage(final String path, final double horizontalRatio, final double verticalRatio) {
        final String url = IMAGE_BASE_URL + path + IMAGE_EXTENSION;
        final var toolKit = Toolkit.getDefaultToolkit();
        final Image img = toolKit.getImage(url).getScaledInstance(
            (int) (toolKit.getScreenSize().getWidth() * horizontalRatio),
            (int) (toolKit.getScreenSize().getHeight() * verticalRatio),
            Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
