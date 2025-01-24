package it.unibo.oop.relario.utils.impl;

import java.awt.Color;
import java.awt.Font;

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
     * The game's resources container folder.
     */
    public static final String RESOURCES_FOLDER_URL = "src/main/resources/";

    /**
     * Game font base URL.
     */
    public static final String FONT_URL = "font/";

    /**
     * Font files extension.
     */
    public static final String FONT_EXTENSION = ".ttf";

    /**
     * Image files extension.
     */
    public static final String IMAGE_EXTENSION = ".png";

    /**
     * Gif files extension.
     */
    public static final String GIF_EXTENSION = ".gif";

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
     * The base folder for combat state enemy textures.
     */
    public static final String COMBAT_TEXTURES_URL = "img/combat/";

    /**
     * Texture files extension.
     */
    public static final String TEXTURES_EXTENSION = ".png";

    /**
     * The base color of the background in view's scenes.
     */
    public static final Color BACKGROUND_SCENE_COLOR = Color.BLACK;

    /**
     * The base color of the text in view's scenes.
     */
    public static final Color TEXT_SCENE_COLOR = Color.WHITE;

    /**
     * The font of the game view.
     */
    public static final Font FONT = new Font(Font.MONOSPACED, Font.TRUETYPE_FONT, 16);

    private Constants() {
        throw new UnsupportedOperationException();
    }
}
