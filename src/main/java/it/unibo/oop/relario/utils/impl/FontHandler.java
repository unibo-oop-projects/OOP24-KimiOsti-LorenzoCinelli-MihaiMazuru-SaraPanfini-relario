package it.unibo.oop.relario.utils.impl;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

/**
 * Handler for the game's fonts.
 */
public class FontHandler {
    
    private FontHandler() { }

    /**
     * Returns the desired font from the game resources.
     * @param fontName the name of the font.
     * @return the desired font.
     */
    public static Font getFont(final String fontName) {
        Font font;

        try {
            font = Font.createFont(
                Font.TRUETYPE_FONT,
                ResourceLocator.class.getResourceAsStream(
                    new StringBuilder(Constants.FONT_URL)
                    .append(fontName.toLowerCase())
                    .append(Constants.FONT_EXTENSION)
                    .toString()
                )
            );
        } catch (FontFormatException | IOException e) {
            font = null;
        }

        return font;
    }

    public static Font getSizedFont(final Font font, final float size) {
        return font.deriveFont(size);
    }
}
