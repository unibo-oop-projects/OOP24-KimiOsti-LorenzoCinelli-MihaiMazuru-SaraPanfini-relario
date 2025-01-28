package it.unibo.oop.relario.controller.impl;

import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.model.entities.enemies.DifficultyLevel;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
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
public final class CombatControllerImpl implements CombatController {

    private static final Integer DELAY_TRANSITION = 2000;
    private final MainView view;
    private final MainController controller;
    private CombatView combatView;
    private MainCharacter player;
    private Enemy enemy;
    private String combatState;
    private boolean isFighting;

    /**
     * Saves reference to main controller.
     * @param controller is the main controller.
     */
    public CombatControllerImpl(final MainController controller) {
        this.controller = controller;
        this.view = this.controller.getMainView();
        this.player = null;
        this.enemy = null;
        this.combatView = null;
        this.combatState = "";
        this.isFighting = false;
    }

    @Override
    public void initializeCombat() {
        if (this.controller.getCurRoom().isPresent()) {
            this.player = this.controller.getCurRoom().get().getPlayer();
            final var entity = this.controller.getCurRoom().get().getCellContent(
                this.player.getDirection().move(this.player.getPosition().get())
            );
            this.enemy = entity.get() instanceof Enemy ? (Enemy) entity.get() 
                : entity.get() instanceof WalkableFurniture 
                ? ((WalkableFurniture) entity.get()).removeEnemy() : null;
            final var tempView = this.view.getPanel(GameState.COMBAT);
            if (tempView instanceof CombatView) {
                this.combatView = (CombatView) tempView;
            }
            this.isFighting = true;
            this.combatState = "Clicca un bottone per fare una mossa.";
            SwingUtilities.invokeLater(this::drawNone);
            this.combatView.startSoundTrack(this.enemy.getType());
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
            ? this.player.getEquippedWeapon().get().getName()
            : "None";
    }

    @Override
    public String getArmor() {
        return this.player.getEquippedArmor().isPresent() 
            ? this.player.getEquippedArmor().get().getName()
            : "None";
    }

    @Override
    public Image getEnemyTexture() {
        return CombatTexturesLocator.getTexture(enemy);
    }

    @Override
    public void resumeCombat() {
        isFighting = true;
        SwingUtilities.invokeLater(this::drawNone);
        this.view.showPanel(GameState.COMBAT);
    }

    @Override
    public void handleAction(final CombatAction combat) {
        if (isFighting) {
            isFighting = false;
            switch (combat) {
                case ATTACK -> this.attack(true);
                case MERCY -> this.mercyRequest();
                case OPEN_INVENTORY -> 
                    this.controller.getInventoryController().init(GameState.COMBAT);
                default -> { }
            }
        }
    }

    private void attack(final boolean isPlayerAttacking) {
        if (isPlayerAttacking) {
            this.enemy.attacked(this.player.attack());
            this.combatState = "Colpo andato a segno.";
            SwingUtilities.invokeLater(this::drawFromPlayerToEnemy);
        } else {
            this.player.attacked(this.enemy.getDamage());
            this.combatState = "<html>Il nemico ha fatto la sua mossa.<br> E' di nuovo il tuo turno.<html>";
            SwingUtilities.invokeLater(this::drawFromEnemyToPlayer);
        }

        if (enemy.getLife() <= 0) {
            if (enemy.getReward().isPresent()) {
                player.addToInventory(enemy.getReward().get());
            }
            combatState = "Hai vinto il combattimento";

            if (enemy.getType().equals(EnemyType.BOSS)) {
                this.timer(e -> {
                    this.combatView.stopSoundTrack();
                    this.controller.getCutSceneController().show(GameState.VICTORY);
                });
            } else {
                this.controller.getCurRoom().get().removeEnemy(this.enemy.getPosition().get());
                this.timer(e -> {
                    this.combatView.stopSoundTrack();
                    this.controller.getGameController().run(true);
                });
            }
        } else if (player.getLife() <= 0) {
            this.timer(e -> {
                this.combatView.stopSoundTrack();
                this.controller.getCutSceneController().show(GameState.GAME_OVER);
            });
        } else if (isPlayerAttacking) {
            this.timer(e -> {
                this.attack(false);
            });
        } else {
            isFighting = true;
        }
    }

    private void mercyRequest() {
        if (this.enemy.isMerciful()) {
            combatState = "Sei stato risparmiato.";
            SwingUtilities.invokeLater(this::drawNone);
            this.timer(e -> {
                this.combatView.stopSoundTrack();
                this.controller.getGameController().run(true);
            });
        } else {
            //player's skips his turn, he used his turn to ask for mercy
            combatState = "Combatti codardo!";
            SwingUtilities.invokeLater(this::drawNone);
            this.timer(e -> {
                this.attack(false);
            });
        }
    }

    private void timer(final ActionListener e) {
        final var timer = new Timer(DELAY_TRANSITION, e);
        timer.setRepeats(false);
        timer.start();
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
