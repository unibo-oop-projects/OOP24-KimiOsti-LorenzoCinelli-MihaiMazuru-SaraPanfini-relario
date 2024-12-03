package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.living.MainCharacter;

/**
 * Implementatio of the combat controller.
 */
public final class CombatControllerImpl implements CombatController {

    private final MainController controller;
    private final Enemy enemy;
    private final MainCharacter player;

    /**
     * Create a new combat view.
     * @param controller is the controller of the main game.
     * @param enemy is the of the two fughters.
     * @param player is the other one of the two fighters.
     */
    public CombatControllerImpl(final MainController controller, 
    final Enemy enemy, final MainCharacter player) {
        this.controller = controller;
        this.enemy = enemy;
        this.player = player;

        // new CombatView(this);
    }

    @Override
    public void endTurn() {
    }

    @Override
    public void stop() {
    }
}
