package it.unibo.oop.relario.controller.impl;

import javax.swing.Timer;

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

    private static final Integer DELAY_TRANSITION = 4000;
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
    public String getCombatState() {
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
        return this.player.getLife();
    }

    @Override
    public DifficultyLevel getDifficultyLevel() {
        return this.enemy.getDifficulty();
    }

    @Override
    public void handleCombatAction(final boolean askingMercy) {
        if (askingMercy) {
            this.mercyRequest();
        } else {
            this.attack(true);
        }
    }

    private void attack(final boolean isPlayerAttacking) {
        if (isPlayerAttacking) {
            this.enemy.attacked(this.player.attack());
        } else {
            this.player.attacked(this.enemy.getDamage());
        }
        //this.view.getPanel(this.view.getCurrentPanel()).draw(); catch the exception

        if (enemy.getLife() <= 0) {
            player.addToInventory(enemy.getReward());
            combatState = this.player.getName() + "You've won the combat";
            //this.view.getPanel(this.view.getCurrentPanel()).draw(); catch the exception
            final Timer timer = new Timer(DELAY_TRANSITION, e -> this.view.showPreviousPanel());
            timer.setRepeats(false);
            timer.start();
        } else if (player.getLife() <= 0) {
            this.view.showPanel(GameState.GAME_OVER);
            final Timer timer = new Timer(DELAY_TRANSITION, e -> this.view.showPanel(GameState.MENU));
            timer.setRepeats(false);
            timer.start();
        } else if (isPlayerAttacking) {
            this.attack(false);
        }

    }

    private void mercyRequest() {
        if (enemy.isMerciful()) {
            combatState = this.enemy.getName() + "accepted your mercy request. /n"
            + "You are free to go.";
            //this.view.getPanel(this.view.getCurrentPanel()).draw(); catch the exception
            final Timer timer = new Timer(DELAY_TRANSITION, e -> this.view.showPreviousPanel());
            timer.setRepeats(false);
            timer.start();
        } else {
            //player's skips his turn, he used his turn to ask for mercy
            this.attack(false);
        }
    }

}
