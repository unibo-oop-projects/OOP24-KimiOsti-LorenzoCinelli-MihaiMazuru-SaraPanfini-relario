package it.unibo.oop.relario.utils.impl;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.util.Locale;

import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.model.entities.living.MainCharacter;

/**
 * Static class for the game resource locator.
 */
public final class ResourceLocator {

    private static final String TEXTURES_URL = "resources/img/";
    private static final String FURNITURE_TEXTURE_URL = "furniture/";
    private static final String LIVING_TEXTURE_URL = "living/";
    private static final String FILE_EXTENSION = ".png";

    private ResourceLocator() { }

    /**
     * Method to bind a texture to a given furniture item.
     * @param furnitureItem a furniture item.
     * @return the texture representing @param furnitureItem
     */
    public static ImageIcon getFurnitureTexture(final Furniture furnitureItem) {
        final StringBuilder imgURL = new StringBuilder(TEXTURES_URL);
        imgURL.append(FURNITURE_TEXTURE_URL);

        imgURL
            .append(furnitureItem.getType().getName().toLowerCase(Locale.ENGLISH))
            .append(FILE_EXTENSION);

        return new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(imgURL.toString())
        );
    }

    /**
     * Method to bind a texture to a given living being.
     * @param livingBeing a living being.
     * @param direction the direction towards where it's facing.
     * @return the texture representing @param livingBeing facing @param direction
     */
    public static ImageIcon getLivingBeingTexture(final LivingBeing livingBeing, final Direction direction) {
        final StringBuilder imgURL = new StringBuilder(TEXTURES_URL);
        imgURL.append(LIVING_TEXTURE_URL);

        if (livingBeing instanceof MainCharacter) {
            imgURL.append("chara");
        } else if (livingBeing instanceof Enemy) {
            imgURL.append(((Enemy) livingBeing).getType().getName().toLowerCase(Locale.ITALIAN));
        } else {
            imgURL.append("npc");
        }

        switch (direction) {
            case UP:
                imgURL.append("-up");
                break;

            case DOWN:
                imgURL.append("-down");
                break;

            case LEFT:
                imgURL.append("-left");
                break;

            default:
                imgURL.append("-right");
                break;
        }
        imgURL.append(FILE_EXTENSION);

        return new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(imgURL.toString())
        );
    }
}
