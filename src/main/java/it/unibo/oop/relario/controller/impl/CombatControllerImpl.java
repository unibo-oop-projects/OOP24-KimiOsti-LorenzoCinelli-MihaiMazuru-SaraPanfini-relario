package it.unibo.oop.relario.controller.impl;

import java.awt.Image;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.entities.enemies.DifficultyLevel;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.furniture.api.WalkableFurniture;
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
    private CombatView combatView;
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
        this.combatView = null;
        this.combatState = "";
    }

    @Override
    public void initializeCombat() {
        if (this.controller.getCurRoom().isPresent()) {
            final var curRoom = this.controller.getCurRoom().get();
            this.player = curRoom.getPlayer();
            final var entity = curRoom.getCellContent(
                this.player.getDirection().move(this.player.getPosition().get()));
            if (entity.get() instanceof Enemy) {
                this.enemy = (Enemy) entity.get();
            } else if (entity.get() instanceof WalkableFurniture) {
                this.enemy = ((WalkableFurniture) entity.get()).removeEnemy();
            }
            this.combatView = (CombatView) this.view.getPanel(GameState.COMBAT);
            SwingUtilities.invokeLater(this::drawNone);
            this.view.showPanel(GameState.COMBAT);
        }
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
        return this.player.getEquippedWeapon().isPresent()
            ? this.player.getEquippedArmor().get().getName()
            : "Nessuna arma equipaggiata";
    }

    @Override
    public String getArmor() {
        return this.player.getEquippedArmor().isPresent() 
            ? this.player.getEquippedArmor().get().getName()
            : "Nessuna armatura equipaggiata";
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
            SwingUtilities.invokeLater(this::drawFromPlayerToEnemy);
        } else {
            this.player.attacked(this.enemy.getDamage());
            SwingUtilities.invokeLater(this::drawFromEnemyToPlayer);
        }

        if (enemy.getLife() <= 0) {
            if (enemy.getReward().isPresent()) {
                player.addToInventory(enemy.getReward().get());
            }
            combatState = this.player.getName() + " hai vinto il combattimento";

            SwingUtilities.invokeLater(this::drawNone);
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
        if (this.enemy.isMerciful()) {
            combatState = this.enemy.getName() + " ha accettato la tua richiesta."
            + "\nSei libero di andare";

            SwingUtilities.invokeLater(this::drawNone);
            final var timer = new Timer(DELAY_TRANSITION, 
                e -> this.controller.getGameController().run(true));
            timer.setRepeats(false);
            timer.start();
        } else {
            //player's skips his turn, he used his turn to ask for mercy
            this.attack(false);
        }
    }

    private void drawNone() {
        this.combatView.update(AttackDirection.NONE);
    }

    private void drawFromPlayerToEnemy() {
        this.combatView.update(AttackDirection.FROM_PLAYER_TO_ENEMY);
    }

    private void drawFromEnemyToPlayer() {
        this.combatView.update(AttackDirection.FROM_ENEMY_TO_PLAYER);
    }

}
