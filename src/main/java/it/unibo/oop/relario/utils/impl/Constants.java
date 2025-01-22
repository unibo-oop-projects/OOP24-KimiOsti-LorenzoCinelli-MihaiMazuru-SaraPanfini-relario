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
     * Font files extension.
     */
    public static final String FONT_EXTENSION = ".ttf";

    /**
     * The base folder for game state textures.
     */
    public static final String GAME_TEXTURES_URL = "img/game";
    /**
     * The base folder for furniture textures.
     */
    public static final String FURNITURE_TEXTURES_URL = "furniture/";
    /**
     * The base folder for living being textures.
     */
    public static final String LIVING_TEXTURES_URL = "living/";

    /**
     * Texture files extension.
     */
    public static final String TEXTURES_EXTENSION = ".png";

    private Constants() {
        throw new UnsupportedOperationException();
    }
}
