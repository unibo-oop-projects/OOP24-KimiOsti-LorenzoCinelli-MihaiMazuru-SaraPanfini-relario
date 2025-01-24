package it.unibo.oop.relario.view.impl;

import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.ImageLocators;
import it.unibo.oop.relario.utils.impl.SoundLocators;
import it.unibo.oop.relario.view.api.CombatAnimation;

/**
 * Implementation of {@link CombatAnimation}.
 */
public final class CombatAnimationImpl extends JLabel implements CombatAnimation {
    /** The attack is from the player to the enemy. */
    public static final boolean FROM_PLAYER_TO_ENEMY = false;
    /** The attack is from the enemy to the player. */
    public static final boolean FROM_ENEMY_TO_PLAYER = true;

    private static final int ANIMATION_DURATION = 850;
    private static final double RATIO = 0.6;
    private static final String ATTACK_ANIMATION = "";
    private static final String ATTACKED_ANIMATION = "";
    private static final List<String> ATTACK_AUDIO = List.of();

    final private Clip clip;
    final private ImageIcon icon;

    /**
     * Creates a new label with the attack animation.
     * @param direction is the direction of the attack.
     */
    public CombatAnimationImpl(final boolean direction) {
        this.clip = SoundLocators.getAudio(ATTACK_AUDIO.get(0)); //TODO
        this.icon = ImageLocators.getFixedSizeImage(
            direction ? ATTACKED_ANIMATION : ATTACK_ANIMATION,
            Constants.GIF_EXTENSION, RATIO, RATIO);
    }

    @Override
    public void start() {
        final Timer timer = new Timer(ANIMATION_DURATION, e -> this.stop());
        timer.setRepeats(false);
        this.clip.start();
        timer.start();
        this.setIcon(this.icon);
        this.validate();
    }

    private void stop() {
        this.removeAll();
        this.repaint();
        this.validate();
        this.clip.close();
    }
}
