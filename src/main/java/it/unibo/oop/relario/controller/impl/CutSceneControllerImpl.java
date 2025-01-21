package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.CutSceneController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

public class CutSceneControllerImpl implements CutSceneController {

    private final MainController controller;
    private final MainView view;
    
    /**
     * 
     * @param controller
     * @param view
     */
    public CutSceneControllerImpl(final MainController controller, final MainView view) {
        this.controller = controller;
        this.view = view;
    }
    
    @Override
    public void show(final GameState nextState) {
        final var currentState = this.view.getCurrentPanel();
        if (currentState == GameState.MENU) {
            //TODO mostra schermata iniziale
        } else {
            //TODO carica schermata comune
            switch (nextState) { //TODO mostra sotto schermata specifica
                case GAME -> { }
                case VICTORY -> { }
                case GAME_OVER -> { }
                default -> { }
            }
        }
        this.view.showPanel(null); //TODO
    }
}
