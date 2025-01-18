package it.unibo.oop.relario.view.impl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.JPanel;

/**
 * The tiles containing a single frame's background.
 */
public final class BackgroundTile extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Image img;

    /**
     * Constructor for the background tiles.
     * @param img the image to be rendered in the tile.
     */
    public BackgroundTile(final Image img) {
        final var tracker = new MediaTracker(this);
        tracker.addImage(img, (int) serialVersionUID);

        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
        }

        this.img = img;
    }

    @Override
    public void paintComponent(final Graphics g) {
        if (this.img != null) {
            g.drawImage(img, 0, 0, this);
        }
    }

}
