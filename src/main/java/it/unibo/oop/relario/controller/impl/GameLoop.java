package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.model.map.Room;
import it.unibo.oop.relario.view.impl.GameView;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.ResourceLocator;

/**
 * The thread running the game's main loop.
 */
public final class GameLoop extends Thread {

    private final GameView view;
    private final Room model;
    private boolean running; //NOPMD suppressed as it is a false positive due to multithreading.

    /**
     * Constructor for the game loop thread.
     * @param view the panel showing the game.
     * @param model the model entry point.
     */
    public GameLoop(final GameView view, final Room model) {
        this.view = view;
        this.model = model;
        this.running = false;
    }

    @Override
    public void run() {
        this.running = true;
        long prevCycleTS = System.currentTimeMillis();

        view.renderBackground(this.model.getDimension(), ResourceLocator.processModel(this.model.getFurniture()));

        while (this.running) {
            final long currCycleTS = System.currentTimeMillis();
            if (currCycleTS - prevCycleTS >= Constants.REFRESH_TIME) {
                prevCycleTS = currCycleTS;
                this.model.update();
                this.view.renderTextures(ResourceLocator.processModel(this.model.getPopulation()));
            } else {
                try {
                    sleep(Constants.REFRESH_TIME - System.currentTimeMillis() + prevCycleTS);
                } catch (InterruptedException e) {
                    if (!interrupted()) {
                        this.interrupt();
                    }
                }
            }
        }
    }

    @Override
    public void interrupt() {
        this.running = false;
    }

}
