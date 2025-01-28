package it.unibo.oop.relario.view.impl;

import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import org.apache.commons.lang3.RandomUtils;

import it.unibo.oop.relario.utils.impl.AttackDirection;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.ImageLocators;
import it.unibo.oop.relario.utils.impl.SoundLocators;
import it.unibo.oop.relario.view.api.CombatAnimation;

/**
 * Implementation of {@link CombatAnimation}.
 */
public final class CombatAnimationImpl extends JLabel implements CombatAnimation {
    private static final long serialVersionUID = 1L;
    private static final double VOLUME = 1.0;
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
        "combat/Strong_Jab"
    );

    private transient Clip clip;
    private final ImageIcon icon;

    /**
     * Creates a new label with the attack animation.
     * @param direction is the direction of the attack.
     */
    public CombatAnimationImpl(final AttackDirection direction) {
        switch (direction) {
            case FROM_ENEMY_TO_PLAYER -> this.icon = ImageLocators.getGif(ATTACKED_ANIMATION);
            case FROM_PLAYER_TO_ENEMY -> this.icon = ImageLocators.getGif(ATTACK_ANIMATION);
            default -> this.icon = null;
        }
    }

    @Override
    public void start() {
        final Timer timer = new Timer(Constants.COMBAT_ANIMATION_TIME, e -> this.clip.close());
        timer.setRepeats(false);
        this.clip = SoundLocators.getAudio(
            ATTACK_AUDIO.get(RandomUtils.nextInt(0, ATTACK_AUDIO.size())),
            VOLUME);
        this.clip.start();
        timer.start();
        this.setIcon(this.icon);
        this.validate();
    }
}
