package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.impl.CombatAction;
import it.unibo.oop.relario.controller.impl.CombatControllerImpl;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.model.entities.enemies.DifficultyLevel;
import it.unibo.oop.relario.model.entities.enemies.Enemy;
import it.unibo.oop.relario.model.entities.enemies.EnemyImpl;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.entities.furniture.api.WalkableFurniture;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureFactoryImpl;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * Used to avoid CheckStyle violations for magic numbers, here used for test scenarios. 
 */
/**
 * Test class for {@link CombatControllerImpl} class.
 */
class CombatControllerTest {

    private static final Integer DELAY_TRANSITION = 2000;

    /**
     * Test combat getters and its method to handle combat.
     */
    @Test
    void testCombat() {
        final Position hostilePos = new PositionImpl(1, 0);
        final Position mercifulPos = new PositionImpl(1, 2);
        final Position walkPos = new PositionImpl(2, 1);
        final InventoryItem item = new InventoryItemFactoryImpl().createRandomItem();
        final MainController mainController = new MainControllerImpl();
        final CombatController controller = mainController.getCombatController();
        mainController.moveToNextRoom();
        final MainCharacter chara = mainController.getCurRoom().get().getPlayer();
        final Enemy hostileEnemy = new EnemyImpl(EnemyType.SOLDIER.getName(), 
            "Sono un soldato", hostilePos, 
            DifficultyLevel.EASY, Optional.of(item), false, EnemyType.SOLDIER);
        final Enemy mercifulEnemy = new EnemyImpl(EnemyType.WIZARD.getName(), 
            "Sono un mago", mercifulPos, 
            DifficultyLevel.HARD, Optional.of(item), true, EnemyType.WIZARD);
        final WalkableFurniture enemFurniture = (WalkableFurniture) new FurnitureFactoryImpl()
            .createRandomWalkableFurniture(walkPos);

        chara.setPosition(new PositionImpl(1, 1));
        chara.setMovement(Direction.UP);
        mainController.getCurRoom().get().addEntity(hostilePos, hostileEnemy);
        controller.initializeCombat();
        assertEquals(controller.getDifficultyLevel(), hostileEnemy.getDifficulty());
        assertEquals(controller.getEnemyLife(), hostileEnemy.getLife());
        assertEquals(controller.getEnemyName(), hostileEnemy.getName());
        assertEquals(controller.getPlayerLife(), chara.getLife());

        final int initCharaLife = chara.getLife();
        final int initEnemyLife = hostileEnemy.getLife();

        controller.handleAction(CombatAction.MERCY);
        try {
            Thread.sleep(DELAY_TRANSITION);
        } catch (InterruptedException e) {
            assertEquals(chara.getLife(), initCharaLife - hostileEnemy.getDamage());
        }
        controller.handleAction(CombatAction.ATTACK);
        try {
            Thread.sleep(DELAY_TRANSITION);
        } catch (InterruptedException e) {
            assertEquals(hostileEnemy.getLife(), initEnemyLife - Constants.PLAYER_ATK);
            assertEquals(chara.getLife(), initCharaLife - 2 * hostileEnemy.getDamage());
        }
        controller.handleAction(CombatAction.ATTACK);
        try {
            Thread.sleep(DELAY_TRANSITION);
        } catch (InterruptedException e) {
            assertTrue(hostileEnemy.getLife() < 0);
            assertEquals(chara.getItems().get(0), item);
            assertEquals(controller.getCombatState(), "Hai vinto il combattimento!");
        }
        chara.setMovement(Direction.RIGHT);
        mainController.getCurRoom().get().addEntity(walkPos, enemFurniture);
        chara.setMovement(Direction.DOWN);
        mainController.getCurRoom().get().addEntity(mercifulPos, mercifulEnemy);
        controller.initializeCombat();
        controller.handleAction(CombatAction.MERCY);
        assertEquals(controller.getCombatState(), "Sei stato risparmiato.");
        controller.initializeCombat();
        for (int i = 0; i < 4; i++) {
            controller.handleAction(CombatAction.ATTACK);
        }
        try {
            Thread.sleep(DELAY_TRANSITION);
        } catch (InterruptedException e) {
            assertTrue(chara.getLife() <= 0);
        }
    }
}
