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
 * Locator class for game state textures.
 */
public class GameTexturesLocator {
    
    private GameTexturesLocator() { }

    /**
     * Returns the floor's texture.
     * @return an image representing the floor texture.
     */
    public static Image getFloorTexture() {
        return Toolkit.getDefaultToolkit().getImage(
            new StringBuilder(Constants.GAME_TEXTURES_URL)
                .append(Constants.FURNITURE_TEXTURES_URL)
                .append("floor")
                .append(Constants.TEXTURES_EXTENSION)
                .toString()
        );
    }

    /**
     * Returns a map containing the textures to be represented and their position on the map.
     * @param model the model entities to be processed.
     * @return a map containing textures and their position.
     */
    public static Map<Position, Image> processModel(final Map<Position, ? extends Entity> model) {
        final var res = new HashMap<Position, Image>();
        model.forEach((k, v) -> res.put(k, getTexture(v)));
        return Map.copyOf(res);
    }

    private static Image getTexture(final Entity entity) {
        if (entity instanceof Furniture) {
            return getFurnitureTexture((Furniture) entity);
        } else if (entity instanceof LivingBeing) {
            return getLivingBeingTexture((LivingBeing) entity, ((LivingBeing) entity).getDirection());
        } else {
            return getFloorTexture();
        }
    }

    private static Image getFurnitureTexture(final Furniture furnitureItem) {
        final StringBuilder imgURL = new StringBuilder(Constants.GAME_TEXTURES_URL);
        imgURL.append(Constants.FURNITURE_TEXTURES_URL);

        imgURL
            .append(furnitureItem.getType().getName().toLowerCase(Locale.ENGLISH))
            .append(Constants.TEXTURES_EXTENSION);

        return Toolkit.getDefaultToolkit().getImage(imgURL.toString());
    }

    private static Image getLivingBeingTexture(final LivingBeing livingBeing, final Direction direction) {
        final StringBuilder imgURL = new StringBuilder(Constants.GAME_TEXTURES_URL);
        imgURL.append(Constants.LIVING_TEXTURES_URL);

        if (livingBeing instanceof MainCharacter) {
            imgURL.append("chara");
        } else if (livingBeing instanceof Enemy) {
            imgURL.append(((Enemy) livingBeing).getType().getName().toLowerCase(Locale.ITALIAN));
        } else {
            imgURL.append("npc");
        }

        switch (direction) {
            case UP -> imgURL.append("-up");
            case DOWN -> imgURL.append("-down");
            case LEFT -> imgURL.append("-left");
            default -> imgURL.append("-right");
        }
        imgURL.append(Constants.TEXTURES_EXTENSION);

        return Toolkit.getDefaultToolkit().getImage(imgURL.toString());
    }
}
