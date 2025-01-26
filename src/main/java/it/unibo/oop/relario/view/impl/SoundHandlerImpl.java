package it.unibo.oop.relario.view.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.view.api.SoundHandler;

/**
 * Implementation of {@link SoundHandler}.
 */
public class SoundHandlerImpl implements SoundHandler {
    private static final float VOLUME_CONVERSION_CONSTANT = 20f;
    private static final String AUDIO_BASE_URL = "audio/";
    private static final String AUDIO_EXTENSION = ".wav";

    private final Map<String, Clip> sounds;
    
    public SoundHandlerImpl() {
        this.sounds = new HashMap<>();
    }
    
    
    @Override
    public void start(final String name, final double volume) {
        this.addAudio(name, volume);
        sounds.get(name).start();
    }

    @Override
    public void startInLoop(final String name, final double volume) {
        this.addAudio(name, volume);
        sounds.get(name).loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void stop(String name) {
        this.sounds.get(name).close();
        this.sounds.remove(name);
    }

    private void addAudio(final String name, final double volume) {
        final AudioInputStream audioInputStream;
        Clip clip;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(
                new File(Constants.RESOURCES_FOLDER_URL + AUDIO_BASE_URL + name + AUDIO_EXTENSION).getAbsoluteFile()
            );
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            setVolume(clip, volume);
            this.sounds.put(name, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            clip = null;
        }
    }

    private void setVolume(final Clip clip, final double volume) {
        if (volume < 0f || volume > 1f) {
            throw new IllegalArgumentException("Volume not valid: " + volume);
        }
        final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(VOLUME_CONVERSION_CONSTANT * (float) Math.log10(volume));
    }
    
}
