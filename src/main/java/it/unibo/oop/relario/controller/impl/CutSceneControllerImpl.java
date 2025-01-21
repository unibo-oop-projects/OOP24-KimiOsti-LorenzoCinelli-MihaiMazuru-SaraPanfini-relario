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
    public void show(final GameState state) {
        switch (state) {
            case MENU -> this.cutSceneView.showStartScene();
            case GAME -> this.cutSceneView.showNextRoomScene();
            case VICTORY -> this.cutSceneView.showVictoryScene();
            case GAME_OVER -> this.cutSceneView.showDefeatScene();
            default -> { }
        }
        this.view.showPanel(GameState.CUT_SCENE);
    }
}
