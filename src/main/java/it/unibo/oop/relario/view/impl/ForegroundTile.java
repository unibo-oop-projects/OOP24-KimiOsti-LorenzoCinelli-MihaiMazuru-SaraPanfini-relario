package it.unibo.oop.relario.view.impl;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The foreground tiles in the main scene.
 */
public class ForegroundTile extends JLabel {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new ForegroundTile.
     * @param scaledInstance
     */
    public ForegroundTile(final Image scaledInstance) {
        super(new ImageIcon(scaledInstance));
    }

}
