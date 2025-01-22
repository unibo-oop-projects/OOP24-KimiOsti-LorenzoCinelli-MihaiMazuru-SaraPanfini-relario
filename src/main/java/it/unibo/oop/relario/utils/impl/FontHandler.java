package it.unibo.oop.relario.utils.impl;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

/**
 * Handler for the game's fonts.
 */
public final class FontHandler {

    private static float DEFAULT_FONT_SIZE = 10f;

    private FontHandler() { }

    /**
     * Returns the desired font from the game resources.
     * @param fontName the name of the font.
     * @return the desired font.
     */
    public static Font getFont(final String fontName) {
        Font font;

        try {
            /*font = Font.createFont(
                Font.TRUETYPE_FONT,
                FontHandler.class.getResourceAsStream(
                    new StringBuilder(Constants.FONT_URL)
                    .append(fontName.toLowerCase(Locale.ROOT))
                    .append(Constants.FONT_EXTENSION)
                    .toString()
                )
            );*/
            font = Font.createFont(
                Font.TRUETYPE_FONT,
                ClassLoader.getSystemResourceAsStream(
                    new StringBuilder(Constants.FONT_URL)
                    .append(Constants.MONOSPACE_FONT)
                    .append(Constants.FONT_EXTENSION)
                    .toString()
                )
            ).deriveFont(DEFAULT_FONT_SIZE);
        } catch (FontFormatException | IOException e) {
            font = null;
        }

        return font;
    }

    /**
     * Derives a scaled instance of the given font.
     * @param font a base font.
     * @param size the desired size.
     * @return an instance of the base font, of the desired size.
     */
    public static Font getSizedFont(final Font font, final float size) {
        return font.deriveFont(size);
    }
}
