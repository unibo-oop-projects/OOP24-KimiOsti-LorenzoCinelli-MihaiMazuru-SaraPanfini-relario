package it.unibo.oop.relario.controller.impl;

import java.awt.Image;

import javax.swing.Timer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.entities.enemies.DifficultyLevel;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.utils.impl.AttackDirection;
import it.unibo.oop.relario.utils.impl.CombatTexturesLocator;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;
import it.unibo.oop.relario.view.impl.CombatView;

/**
 * Implementation of the combat controller.
 */
@SuppressFBWarnings(
    value = "BC_UNCONFIRMED_CAST_OF_RETURN_VALUE",
    justification = "combatView will always be an instance of CombatView"
        + "based on how MainView is implemented."
)
public final class CombatControllerImpl implements CombatController {

    private static final Integer DELAY_TRANSITION = 4000;
    private final MainView view;
    private final MainController controller;
    private final CombatView combatView;
    private MainCharacter player;
    private Enemy enemy;
    private String combatState;

    /**
     * Saves reference of main controller.
     * @param controller is the main controller.
     */
    public CombatControllerImpl(final MainController controller) {
        this.controller = controller;
        this.view = this.controller.getMainView();
        this.player = null;
        this.enemy = null;
        this.combatState = "";
        this.combatView = (CombatView) this.view.getPanel(GameState.COMBAT);
    }

    @Override
    public void initializeCombat(final MainCharacter player, final Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.view.showPanel(GameState.COMBAT);
        this.combatView.update(AttackDirection.NONE);
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
            default -> { }
        }
    }

    private void attack(final boolean isPlayerAttacking) {
        if (isPlayerAttacking) {
            this.enemy.attacked(this.player.attack());
            this.combatView.update(AttackDirection.FROM_PLAYER_TO_ENEMY);
        } else {
            this.player.attacked(this.enemy.getDamage());
            this.combatView.update(AttackDirection.FROM_ENEMY_TO_PLAYER);
        }

        if (enemy.getLife() <= 0) {
            if (enemy.getReward().isPresent()) {
                player.addToInventory(enemy.getReward().get());
            }
            combatState = this.player.getName() + " hai vinto il combattimento";
            this.combatView.update(AttackDirection.NONE);
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
            combatState = this.enemy.getName() + " ha accettato la tua richiesta."
            + "\nSei libero di andare";
            this.combatView.update(AttackDirection.NONE);
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
