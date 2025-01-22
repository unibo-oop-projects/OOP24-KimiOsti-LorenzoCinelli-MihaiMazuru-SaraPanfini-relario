package it.unibo.oop.relario.utils.impl;

/**
 * Utility static class containing all of the games' constants.
 */
public final class Constants {
    /**
     * Default player's initial life.
     */
    public static final int DEFAULT_PLAYER_LIFE = 80;
    /**
     * Default player's base attack.
     */
    public static final int DEFAULT_PLAYER_ATK = 20;

    /**
     * Game engine refresh time.
     */
    public static final int REFRESH_TIME = 125;

    /**
     * Game font base URL.
     */
    public static final String FONT_URL = "font/";
    /**
     * Game's monospace font name.
     */
    public static final String MONOSPACE_FONT = "monogram";
    /**
     * Font file extension.
     */
    public static final String FONT_EXTENSION = ".ttf";

    private Constants() {
        throw new UnsupportedOperationException();
    }
}
