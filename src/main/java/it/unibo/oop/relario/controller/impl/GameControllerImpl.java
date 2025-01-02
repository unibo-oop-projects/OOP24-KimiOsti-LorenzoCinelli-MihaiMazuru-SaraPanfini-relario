package it.unibo.oop.relario.controller.impl;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import it.unibo.oop.relario.controller.api.GameController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.utils.impl.ResourceLocator;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation for the Game Controller.
 */
public final class GameControllerImpl implements GameController {

    private final MainController controller;
    private final MainView view;
    private final Thread mainLoop;

    /**
     * Constructor for the game controller.
     * @param controller its own container class, used to access the Model.
     * @param view the main View of the application, used to access the Game View.
     */
    public GameControllerImpl(final MainController controller, final MainView view) {
        this.controller = controller;
        this.view = view;
        this.mainLoop = new Thread(this::mainLoop);
    }

    @Override
    public void run() {
        if (this.progressRoom()) {
            this.mainLoop.start();
        }
    }

    @Override
    public void resume(boolean isExploring) {
        if (!isExploring) {
            this.view.showPanel(GameState.GAME_OVER);
        } else {
            this.mainLoop.start();
        }
    }

    private void mainLoop() {
        final var gamePanel = this.view.getPanel(GameState.GAME);
        long prevCycleTime = System.currentTimeMillis();
        this.view.showPanel(GameState.GAME);

        gamePanel.renderFloor(this.controller.getCurRoom().get().getDimension());
        gamePanel.renderFurniture(this.processElems(this.controller.getCurRoom().get().getFurniture()));

        while (true) {
            final long thisCycleTime = System.currentTimeMillis();
            if (thisCycleTime - prevCycleTime >= Constants.REFRESH_TIME) {
                prevCycleTime = thisCycleTime;
                this.controller.getCurRoom().get().update();
                gamePanel.update(processElems(this.controller.getCurRoom().get().getPopulation()));
            } else {
                try {
                    Thread.sleep(Constants.REFRESH_TIME - System.currentTimeMillis());
                } catch (InterruptedException e) { 
                    /**
                     * Exception is ignored, since interrupting the Thread would mean stopping the main loop execution.
                     */
                }
            }
        }
    }

    private Map<Position, ImageIcon> processElems(Map<Position, ? extends Entity> model) {
        final var res = new HashMap<Position, ImageIcon>();
        for (var elem : model.entrySet()) {
            res.put(elem.getKey(), ResourceLocator.getTexture(elem.getValue()));
        }
        return Map.copyOf(res);
    }

    private boolean progressRoom() {
        this.controller.moveToNextRoom();
        return this.controller.getCurRoom().isPresent();
    }
}
