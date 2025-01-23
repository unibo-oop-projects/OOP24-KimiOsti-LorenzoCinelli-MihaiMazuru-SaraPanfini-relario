package it.unibo.oop.relario.controller.impl;

import java.awt.Image;

import javax.swing.Timer;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.entities.enemies.DifficultyLevel;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.utils.impl.CombatTexturesLocator;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation of the combat controller.
 */
public final class CombatControllerImpl implements CombatController {

    private static final Integer DELAY_TRANSITION = 4000;
    private final MainView view;
    private final MainController controller;
    private MainCharacter player;
    private Enemy enemy;
    private String combatState;

    /**
     * Saves reference to main view and main controller.
     * @param view is the main view.
     * @param controller is the main controller.
     */
    public CombatControllerImpl(final MainController controller) {
        this.controller = controller;
        this.view = this.controller.getMainView();
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
    public String getItem() {
        return this.player.getEquippedWeapon().get().getName();
    }

    @Override
    public String getArmor() {
        return this.player.getEquippedArmor().get().getName();
    }

    @Override
    public Image getEnemyTexture() {
        return CombatTexturesLocator.getTexture(enemy);
    }

    @Override
    public void resumeCombat() {
        this.view.showPanel(GameState.COMBAT);
    }

    @Override
    public void handleAction(final CombatAction combat) {
        switch (combat) {
            case ATTACK -> this.attack(true);
            case MERCY -> this.mercyRequest();
            case OPEN_INVENTORY -> 
                this.controller.getInventoryController().init(GameState.COMBAT);
        }
    }

    private void attack(final boolean isPlayerAttacking) {
        if (isPlayerAttacking) {
            this.enemy.attacked(this.player.attack());
        } else {
            this.player.attacked(this.enemy.getDamage());
        }
        //this.view.getPanel(this.view.getCurrentPanel()).draw();

        if (enemy.getLife() <= 0) {
            player.addToInventory(enemy.getReward());
            combatState = this.player.getName() + " you've won the combat";
            //this.view.getPanel(this.view.getCurrentPanel()).draw();
            final var timer = new Timer(DELAY_TRANSITION, 
                e -> this.controller.getCutSceneController().show(GameState.VICTORY));
            timer.setRepeats(false);
            timer.start();

        } else if (player.getLife() <= 0) {
            final var timer = new Timer(DELAY_TRANSITION, 
                e -> this.controller.getCutSceneController().show(GameState.GAME_OVER));
            timer.setRepeats(false);
            timer.start();

        } else if (isPlayerAttacking) {
            this.attack(false);
        }

    }

    private void mercyRequest() {
        if (enemy.isMerciful()) {
            combatState = this.enemy.getName() + " accepted your mercy request."
            + " You are free to go.";
            //this.view.getPanel(this.view.getCurrentPanel()).draw();
            final var timer = new Timer(DELAY_TRANSITION, 
                e -> this.controller.getGameController().run(true));
            timer.setRepeats(false);
            timer.start();
            
        } else {
            //player's skips his turn, he used his turn to ask for mercy
            this.attack(false);
        }
    }

}