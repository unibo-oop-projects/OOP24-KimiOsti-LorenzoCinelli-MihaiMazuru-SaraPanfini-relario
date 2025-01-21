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
    private final CutSceneView cutSceneView;

    /**
     * 
     * @param controller
     * @param view
     */
    public CutSceneControllerImpl(final MainController controller, final MainView view) {
        this.controller = controller;
        this.view = view;
        this.cutSceneView = null;
    }

    @Override
    public void show(final GameState nextState) {
        final var currentState = this.view.getCurrentPanel();
        if (currentState == GameState.MENU) {
            this.cutSceneView.showStartScene();
        } else {
            switch (nextState) {
                case GAME -> this.cutSceneView.showNextRoomScene();
                case VICTORY -> this.cutSceneView.showVictoryScene();
                case GAME_OVER -> this.cutSceneView.showDefeatScene();
                default -> { }
            }
        }
        this.view.showPanel(GameState.CUT_SCENE);
    }
}
