package it.unibo.oop.relario.utils.impl;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Locale;

import it.unibo.oop.relario.model.entities.enemies.Enemy;

public final class CombatTexturesLocator {

    private CombatTexturesLocator() { }

    public static Image getTexture(final Enemy enemy) {
        return Toolkit.getDefaultToolkit().getImage(
            new StringBuilder(Constants.COMBAT_TEXTURES_URL)
            .append(enemy.getName().toLowerCase(Locale.ITALIAN))
            .append(Constants.TEXTURES_EXTENSION)
            .toString()
        );
    }
}
