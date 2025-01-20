package it.unibo.oop.relario.controller.impl;

import java.util.Map;

import it.unibo.oop.relario.controller.api.GameController;
import it.unibo.oop.relario.controller.api.InteractionsHandler;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.Event;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;
import it.unibo.oop.relario.view.impl.GameView;

/**
 * Implementation for the Game Controller.
 */
public final class GameControllerImpl implements GameController {

    private final InteractionsHandler interactionsHandler;
    private final Map<Event, Direction> inputToDirection;
    private final MainController controller;
    private final MainView view;
    private Thread gameLoop;

    /**
     * Constructor for the game controller.
     * @param controller its own container class, used to access the Model.
     * @param view the main View of the application, used to access the Game View.
     */
    public GameControllerImpl(final MainController controller, final MainView view) {
        this.controller = controller;
        this.view = view;
        this.interactionsHandler = new InteractionsHandlerImpl(this.controller, this.view);
        this.inputToDirection = Map.of(
            Event.MOVE_UP, Direction.UP,
            Event.MOVE_DOWN, Direction.DOWN,
            Event.MOVE_RIGHT, Direction.RIGHT,
            Event.MOVE_LEFT, Direction.LEFT
        );
    }

    @Override
    public void run() {
        this.controller.moveToNextRoom();
        if (this.controller.getCurRoom().isPresent()) {
            this.startGameLoop();
        }
    }

    @Override
    public void resume(final boolean isExploring) {
        if (!isExploring) {
            this.view.showPanel(GameState.GAME_OVER);
        } else {
            this.startGameLoop();
        }
    }

    @Override
    public void notify(final Event e) {
        switch (e) {
            case INTERACT -> {
                this.gameLoop.interrupt();
                this.interactionsHandler.handleInteraction(
                    this.controller.getCurRoom().get()
                );
            }
            case INVENTORY -> this.changeGameState(GameState.INVENTORY);
            case ESCAPE -> this.changeGameState(GameState.MENU_IN_GAME);
            default -> this.handleMovement(e);
        }
    }

    private void startGameLoop() {
        this.view.showPanel(GameState.GAME);
        this.gameLoop = new GameLoop(
            (GameView) this.view.getPanel(GameState.GAME),
            this.controller.getCurRoom().get()
        );
        this.gameLoop.start();
    }

    private void changeGameState(final GameState state) {
        this.gameLoop.interrupt();
        this.view.showPanel(state.getState());
    }

    private void handleMovement(final Event e) {
        if (e.equals(Event.RELEASED)) {
            this.controller.getCurRoom().get().getPlayer().stop();
        } else {
            this.controller.getCurRoom().get().getPlayer().setMovement(
                this.inputToDirection.get(e)
            );
        }
    }
}
