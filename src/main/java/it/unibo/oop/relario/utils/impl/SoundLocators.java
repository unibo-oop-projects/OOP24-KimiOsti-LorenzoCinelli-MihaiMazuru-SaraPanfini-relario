package it.unibo.oop.relario.utils.impl;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Utility class for the sound locator.
 */
public final class SoundLocators {
    private static final float VOLUME_CONVERSION_CONSTANT = 20f;
    private static final String AUDIO_BASE_URL = "audio/";
    private static final String AUDIO_EXTENSION = ".wav";

    private SoundLocators() { }

    /**
     * Returns a clip audio of the given name. 
     * @param name is the name of the file. The extension is .wav.
     * @param volume the volume to set the clip. 1.0 corresponds to 100%.
     * @return a reproducible Clip.
     */
    public static Clip getAudio(final String name, final double volume) {
        final AudioInputStream audioInputStream;
        Clip clip;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(
                new File(Constants.RESOURCES_FOLDER_URL + AUDIO_BASE_URL + name + AUDIO_EXTENSION).getAbsoluteFile()
            );
            clip = AudioSystem.getClip();
            setVolume(clip, volume);
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            clip = null;
        }
        return clip;
    }

    private static void setVolume(final Clip clip, final double volume) {
        if (volume < 0f || volume > 1f) {
            throw new IllegalArgumentException("Volume not valid: " + volume);
        }
        final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(VOLUME_CONVERSION_CONSTANT * (float) Math.log10(volume));
    }
}
