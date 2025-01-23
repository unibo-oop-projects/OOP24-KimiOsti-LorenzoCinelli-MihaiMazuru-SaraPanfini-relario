package it.unibo.oop.relario.view.impl;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The foreground tiles in the main scene.
 */
public class ForegroundTile extends JLabel {

    /**
     * Creates new ForegroundTile.
     * @param scaledInstance
     */
    public ForegroundTile(Image scaledInstance) {
        super(new ImageIcon(scaledInstance));
    }

    private static final long serialVersionUID = 1L;

}
