package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.api.GameController;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.view.impl.MainView;

/**
 * Implementatio of the combat controller.
 */
public final class CombatControllerImpl implements CombatController {

    private final MainView myFrame;
    private final GameController controller;
    private final Enemy enemy;
    private final MainCharacter player;

    /**
     * Create a new combat view.
     * @param myFrame is the main frame where the new view will be added.
     * @param controller is the controller of the main game.
     * @param enemy is the of the two fughters.
     * @param player is the other one of the two fighters.
     */
    public CombatControllerImpl(final MainView myFrame, final GameController controller, 
    final Enemy enemy, final MainCharacter player) {
        this.myFrame = myFrame;
        this.controller = controller;
        this.enemy = enemy;
        this.player = player;

        // new CombatView(MainView, this);
    }

    @Override
    public void endTurn() {
    }

    @Override
    public void stop() {
    }
}
