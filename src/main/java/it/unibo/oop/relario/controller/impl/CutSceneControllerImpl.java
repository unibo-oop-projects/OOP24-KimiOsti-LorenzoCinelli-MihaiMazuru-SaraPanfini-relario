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
    private CutSceneView cutSceneView;

    /**
     * Initializes the cut scene controller.
     * @param controller the main controller of the game.
     * @param view the main view of the game.
     */
    public CutSceneControllerImpl(final MainController controller, final MainView view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void show(final GameState state) {
        this.cutSceneView = (CutSceneView) this.view.getPanel(GameState.CUT_SCENE);
        switch (state) {
            case MENU -> this.cutSceneView.showStartScene();
            case GAME -> this.cutSceneView.showNextRoomScene();
            case VICTORY -> this.cutSceneView.showVictoryScene();
            case GAME_OVER -> this.cutSceneView.showDefeatScene();
            default -> { }
        }
        this.view.showPanel(GameState.CUT_SCENE);
    }

    @Override
    public void progress(final GameState nextState) {
        switch (nextState) {
            case GAME -> {
                this.controller.moveToNextRoom();
                this.controller.getGameController().run();
            }
            case MENU -> this.controller.getMenuController();
            default -> { }
        }
    }
}
