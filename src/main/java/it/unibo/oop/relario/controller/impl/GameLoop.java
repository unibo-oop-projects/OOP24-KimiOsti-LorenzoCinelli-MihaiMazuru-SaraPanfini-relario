package it.unibo.oop.relario.controller.impl;

import javax.swing.SwingUtilities;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.view.impl.GameView;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.utils.impl.GameTexturesLocator;

/**
 * The thread running the game's main loop.
 */
public final class GameLoop extends Thread {

    private final MainController controller;
    private boolean running; //NOPMD suppressed as it is a false positive due to multithreading.

    /**
     * Constructor for the game loop thread.
     * @param controller the main controller handling the application.
     */
    public GameLoop(final MainController controller) {
        this.controller = controller;
        this.running = false;
    }

    @Override
    public void run() {
        this.running = true;
        final var model = this.controller.getCurRoom();
        long prevCycleTS = System.currentTimeMillis();

        SwingUtilities.invokeLater(this::renderBackgroundScene);

        while (this.running && model.isPresent()) {
            final long currCycleTS = System.currentTimeMillis();
            if (currCycleTS - prevCycleTS >= Constants.REFRESH_TIME) {
                prevCycleTS = currCycleTS;
                model.get().update();
                SwingUtilities.invokeLater(this::renderForegroundScene);
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
        super.interrupt();
    }

    private void renderBackgroundScene() {
        final var gameView = this.controller.getMainView().getPanel(GameState.GAME);
        final var model = this.controller.getCurRoom();
        if (gameView instanceof GameView && model.isPresent()) {
            ((GameView) gameView).renderBackground(
                model.get().getDimension(),
                GameTexturesLocator.processModel(model.get().getFurniture())
            );
        }
    }

    private void renderForegroundScene() {
        final var gameView = this.controller.getMainView().getPanel(GameState.GAME);
        final var model = this.controller.getCurRoom();
        if (gameView instanceof GameView && model.isPresent()) {
            final var population = model.get().getPopulation();
            population.put(model.get().getPlayer().getPosition().get(), model.get().getPlayer());
            ((GameView) gameView).renderTextures(GameTexturesLocator.processModel(population));
        }
    }

}
