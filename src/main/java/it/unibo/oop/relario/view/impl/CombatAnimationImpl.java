package it.unibo.oop.relario.view.impl;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import org.apache.commons.lang3.RandomUtils;

import it.unibo.oop.relario.utils.impl.AttackDirection;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.ImageLocators;
import it.unibo.oop.relario.view.api.CombatAnimation;
import it.unibo.oop.relario.view.api.SoundHandler;

/**
 * Implementation of {@link CombatAnimation}.
 */
public final class CombatAnimationImpl extends JLabel implements CombatAnimation {
    private static final long serialVersionUID = 1L;
    private static final int ANIMATION_DURATION = 850;
    private static final double VOLUME = 1.0;
    private static final double RATIO = 0.6;
    private static final String ATTACK_ANIMATION = "combat/attack_effect";
    private static final String ATTACKED_ANIMATION = "combat/attacked_effect";
    private static final List<String> ATTACK_AUDIO = List.of(
        "combat/Great_Heave",
        "combat/Heavy_Slash",
        "combat/Piercing_Thrust",
        "combat/Quick_Swing",
        "combat/Quick_Throw",
        "combat/Side_Cleave",
        "combat/Side_Swing",
        "combat/Slash",
        "combat/String_Jab"
    );

    private final transient SoundHandler soundHandler;
    private final ImageIcon icon;
    private String currentSound;

    /**
     * Creates a new label with the attack animation.
     * @param direction is the direction of the attack.
     * @param soundHandler the soundHandler of the game.
     */
    public CombatAnimationImpl(final AttackDirection direction, final SoundHandler soundHandler) {
        this.soundHandler = soundHandler;
        switch (direction) {
            case FROM_ENEMY_TO_PLAYER -> this.icon = ImageLocators.getFixedSizeImage(
                ATTACKED_ANIMATION, Constants.GIF_EXTENSION, RATIO, RATIO);
            case FROM_PLAYER_TO_ENEMY -> this.icon = ImageLocators.getFixedSizeImage(
                ATTACK_ANIMATION, Constants.GIF_EXTENSION, RATIO, RATIO);
            default -> this.icon = null;
        }
    }

    @Override
    public void start() {
        final Timer timer = new Timer(ANIMATION_DURATION, e -> this.stop());
        timer.setRepeats(false);
        this.currentSound = ATTACK_AUDIO.get(RandomUtils.nextInt(0, ATTACK_AUDIO.size()));
        this.soundHandler.start(this.currentSound, VOLUME);
        timer.start();
        this.setIcon(this.icon);
        this.validate();
    }

    private void stop() {
        this.removeAll();
        this.repaint();
        this.validate();
        this.soundHandler.stop(this.currentSound);
    }
}
