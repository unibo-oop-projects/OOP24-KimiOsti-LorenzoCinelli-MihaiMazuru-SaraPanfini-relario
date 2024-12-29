package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.model.entities.enemies.DifficultyLevel;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation of the combat controller.
 */
public final class CombatControllerImpl implements CombatController {

    private final MainView view;
    private MainCharacter player;
    private Enemy enemy;
    private String combatState;

    /**
     * Saves reference to main view.
     * @param view is the main view.
     */
    public CombatControllerImpl(final MainView view) {
        this.view = view;
        this.player = null;
        this.enemy = null;
        this.combatState = "";
    }

    @Override
    public void initializeCombat(final MainCharacter player, final Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.view.showPanel(GameState.COMBAT);
    }

    @Override
    public String getGombatState() {
        return this.combatState;
    }

    @Override
    public String getEnemyName() {
        return this.enemy.getName();
    }

    @Override
    public int getEnemyLife() {
        return this.enemy.getLife();
    }

    @Override
    public int getPlayerLife() {
        return 0;
        //return this.player.getLife();
    }

    @Override
    public DifficultyLevel getDifficultyLevel() {
        return this.enemy.getDifficulty();
    }

}