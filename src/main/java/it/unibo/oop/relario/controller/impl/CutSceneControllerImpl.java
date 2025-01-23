package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.CutSceneController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.CutSceneView;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation of {@link CutSceneController}.
 */
public final class CutSceneControllerImpl implements CutSceneController {

    private final MainController controller;
    private final MainView view;

    /**
     * Initializes the cut scene controller.
     * @param controller the main controller of the game.
     * @param view the main view of the game.
     */
    public CutSceneControllerImpl(final MainController controller) {
        this.controller = controller;
        this.view = this.controller.getMainView();
    }

    @Override
    public void show(final GameState state) {
        final var cutSceneView = (CutSceneView) this.view.getPanel(GameState.CUT_SCENE);
        switch (state) {
            case MENU -> cutSceneView.showStartScene();
            case GAME -> cutSceneView.showNextRoomScene();
            case VICTORY -> cutSceneView.showVictoryScene();
            case GAME_OVER -> cutSceneView.showDefeatScene();
            default -> { }
        }
        this.view.showPanel(GameState.CUT_SCENE);
    }

    @Override
    public void progress(final GameState nextState) {
        switch (nextState) {
            case GAME -> {
                this.controller.moveToNextRoom();
                this.controller.getGameController().run(this.controller.getCurRoom().isPresent());
            }
            case MENU -> this.controller.getMenuController();
            default -> { }
        }
    }
}
