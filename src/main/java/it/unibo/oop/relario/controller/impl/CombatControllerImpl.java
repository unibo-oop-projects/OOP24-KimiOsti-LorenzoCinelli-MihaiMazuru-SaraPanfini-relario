package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.api.MainController;
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
    private final MainController controller;
    private MainCharacter player;
    private Enemy enemy;
    private String combatState;

    /**
     * Saves reference to main view and main controller.
     * @param view is the main view.
     * @param controller is the main controller.
     */
    public CombatControllerImpl(final MainView view, final MainController controller) {
        this.view = view;
        this.controller = controller;
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
            combatState = this.player.getName() + "You've won the combat";
            //this.view.getPanel(this.view.getCurrentPanel()).draw();
            /* [TODO]: gestire transizione tramite controller */
        } else if (player.getLife() <= 0) {
            //this.view.showPanel(GameState.GAME_OVER);
            /* [TODO]: gestire transizione tramite controller */
        } else if (isPlayerAttacking) {
            this.attack(false);
        }

    }

    private void mercyRequest() {
        if (enemy.isMerciful()) {
            combatState = this.enemy.getName() + "accepted your mercy request. /n"
            + "You are free to go.";
            //this.view.getPanel(this.view.getCurrentPanel()).draw();
            /* [TODO]: gestire transizione tramite controller */
        } else {
            //player's skips his turn, he used his turn to ask for mercy
            this.attack(false);
        }
    }

}