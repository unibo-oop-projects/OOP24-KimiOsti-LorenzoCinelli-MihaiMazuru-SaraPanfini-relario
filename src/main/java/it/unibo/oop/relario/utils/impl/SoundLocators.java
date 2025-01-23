package it.unibo.oop.relario.utils.impl;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Utility class for the sound locator.
 */
public final class SoundLocators {
    private static final String AUDIO_BASE_URL = "audio/";
    private static final String AUDIO_EXTENSION = ".wav";

    private SoundLocators() { }

    /**
     * Returns a clip audio of the given name. 
     * @param name is the name of the file. The extension is .wav,
     * @return a reproducible Clip.
     */
    public static Clip getAudio(final String name) {
        final AudioInputStream audioInputStream;
        Clip clip;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(
                new File(Constants.RESOURCES_FOLDER_URL + AUDIO_BASE_URL + name + AUDIO_EXTENSION).getAbsoluteFile()
            );
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            clip = null;
        }
        return clip;
    }
}
