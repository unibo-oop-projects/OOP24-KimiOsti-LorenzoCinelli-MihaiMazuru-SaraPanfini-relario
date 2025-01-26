package it.unibo.oop.relario.view.api;

/**
 * Interface for handling sounds in the game.
 */
public interface SoundHandler {

    /**
     * Starts to reproduce the sound with the given name.
     * @param name the name of the sound.
     * @param volume the volume to set to the clip. 1.0 corresponds to 100%.
     */
    public void start(String name, double volume);

    /**
     * Starts to reproduce in loop the sound with the given name.
     * @param name the name of the sound.
     * @param volume the volume to set to the clip. 1.0 corresponds to 100%.
     */
    public void startInLoop(String name, double volume);

    /**
     * Stops the reproduction of the sound with the given name.
     * @param name the name of the sound.
     */
    public void stop(String name);
}
