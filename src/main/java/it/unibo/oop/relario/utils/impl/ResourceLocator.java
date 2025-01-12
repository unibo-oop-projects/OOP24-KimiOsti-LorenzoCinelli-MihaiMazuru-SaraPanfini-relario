package it.unibo.oop.relario.utils.impl;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.furniture.api.Furniture;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.utils.api.Position;

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
     * Returns a map containing the textures to be represented and their position on the map.
     * @param model the model entities to be processed.
     * @return a map containing textures and their position.
     */
    public static Map<Position, Image> processModel(Map<Position, ? extends Entity> model) {
        final var res = new HashMap<Position, Image>();
        model.forEach((k, v) -> {
            res.put(k, ResourceLocator.getTexture(v));
        });
        return Map.copyOf(res);
    }

    private static Image getTexture(final Entity entity) {
        if (entity instanceof Furniture) {
            return ResourceLocator.getFurnitureTexture((Furniture) entity);
        } else if (entity instanceof LivingBeing) {
            return ResourceLocator.getLivingBeingTexture((LivingBeing) entity, ((LivingBeing) entity).getDirection());
        } else {
            return Toolkit.getDefaultToolkit().getImage(
                new StringBuilder(TEXTURES_URL)
                    .append(FURNITURE_TEXTURE_URL)
                    .append("floor")
                    .append(FILE_EXTENSION)
                    .toString()   
            );
        }
    }

    private static Image getFurnitureTexture(final Furniture furnitureItem) {
        final StringBuilder imgURL = new StringBuilder(TEXTURES_URL);
        imgURL.append(FURNITURE_TEXTURE_URL);

        imgURL
            .append(furnitureItem.getType().getName().toLowerCase(Locale.ENGLISH))
            .append(FILE_EXTENSION);

        return Toolkit.getDefaultToolkit().getImage(imgURL.toString());
    }

    private static Image getLivingBeingTexture(final LivingBeing livingBeing, final Direction direction) {
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

        return Toolkit.getDefaultToolkit().getImage(imgURL.toString());
    }
}
