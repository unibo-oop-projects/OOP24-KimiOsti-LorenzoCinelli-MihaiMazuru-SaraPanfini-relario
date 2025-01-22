package it.unibo.oop.relario.utils.impl;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Locale;

import it.unibo.oop.relario.model.entities.enemies.Enemy;

/**
 * A locator for combat scene textures.
 */
public final class CombatTexturesLocator {

    private CombatTexturesLocator() { }

    /**
     * A method to retrieve an enemy's texture in a combat scenario.
     * @param enemy the enemy whose texture must be retrieved.
     * @return the texture representing the given enemy.
     */
    public static Image getTexture(final Enemy enemy) {
        return Toolkit.getDefaultToolkit().getImage(
            new StringBuilder(Constants.COMBAT_TEXTURES_URL)
            .append(enemy.getName().toLowerCase(Locale.ITALIAN))
            .append(Constants.TEXTURES_EXTENSION)
            .toString()
        );
    }
}
