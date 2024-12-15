package it.unibo.oop.relario.utils.impl;

import javax.swing.ImageIcon;
import java.awt.Toolkit;

import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.furniture.api.FurnitureItem;

/**
 * Static class for the game resource locator.
 */
public final class ResourceLocator {

    private static final String TEXTURES_URL = "resources/img/";
    private static final String FURNITURE_TEXTURE_URL = "furniture/";
    private static final String LIVING_TEXTURE_URL = "living/";

    private ResourceLocator() {}

    /**
     * Method to bind a texture to a given furniture item.
     * @param elem a furniture item.
     * @return the texture representing @param elem
     */
    public static ImageIcon getFurnitureTexture(final FurnitureItem elem) {
        final String imgURL = TEXTURES_URL + FURNITURE_TEXTURE_URL;

        /**
         * [TODO]: find the correct texture
         */

        return new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(imgURL)
        );
    }

    /**
     * Method to bind a texture to a given living being.
     * @param livingBeing a living being.
     * @param direction the direction towards where it's facing.
     * @return the texture representing @param livingBeing facing @param direction
     */
    public static ImageIcon getLivingBeingTexture(final LivingBeing livingBeing, final Direction direction) {
        final String imgURL = TEXTURES_URL + LIVING_TEXTURE_URL;

        /**
         * [TODO]: find the correct texture 
         */

        return new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(imgURL)
        );
    }
}
