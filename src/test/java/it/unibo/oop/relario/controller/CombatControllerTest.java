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
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.entities.living.MainCharacterImpl;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.PositionImpl;
import it.unibo.oop.relario.view.api.MainView;
import it.unibo.oop.relario.view.impl.MainViewImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * Used to avoid CheckStyle violations for magic numbers, here used for test scenarios. 
 */
/**
 * Test class for {@link CombatControllerImpl} class.
 */
class CombatControllerTest {

    /**
     * Test combat getters and its method to handle combat.
     */
    @Test
    void testCombat() {
        final MainController mainController = new MainControllerImpl();
        final MainView view = new MainViewImpl(mainController);
        view.panelsSetup();
        final Position pos = new PositionImpl(0, 0);
        final InventoryItem item = new InventoryItemFactoryImpl().createRandomItem();

        final CombatController controller = new CombatControllerImpl(mainController);
        final MainCharacter chara = new MainCharacterImpl();
        final Enemy hostileEnemy = new EnemyImpl(EnemyType.SOLDIER.getName(), 
        "Sono un soldato", pos, DifficultyLevel.EASY, Optional.of(item), false, EnemyType.SOLDIER);
        final Enemy mercifulEnemy = new EnemyImpl(EnemyType.WIZARD.getName(), 
        "Sono un mago", pos, DifficultyLevel.HARD, Optional.of(item), true, EnemyType.WIZARD);

        mainController.moveToNextRoom();

        assertEquals(controller.getCombatState(), "");
        controller.initializeCombat(hostileEnemy);
        assertEquals(controller.getDifficultyLevel(), hostileEnemy.getDifficulty());
        assertEquals(controller.getEnemyLife(), hostileEnemy.getLife());
        assertEquals(controller.getEnemyName(), hostileEnemy.getName());
        assertEquals(controller.getPlayerLife(), chara.getLife());

        final int initCharaLife = Constants.PLAYER_LIFE;
        final int initEnemyLife = hostileEnemy.getLife();

        controller.handleAction(CombatAction.MERCY);
        assertEquals(chara.getLife(), initCharaLife - hostileEnemy.getDamage());
        controller.handleAction(CombatAction.ATTACK);
        assertEquals(hostileEnemy.getLife(), initEnemyLife - Constants.PLAYER_ATK);
        assertEquals(chara.getLife(), initCharaLife - 2 * hostileEnemy.getDamage());
        controller.handleAction(CombatAction.ATTACK);
        assertTrue(hostileEnemy.getLife() < 0);
        assertEquals(chara.getItems().get(0), item);
        assertEquals(controller.getCombatState(), chara.getName() + " hai vinto il combattimento");

        controller.initializeCombat(mercifulEnemy);
        controller.handleAction(CombatAction.MERCY);
        assertEquals(controller.getCombatState(), controller.getEnemyName()
            + " ha accettato la tua richiesta." + "\nSei libero di andare");

        final int initMerciLife = mercifulEnemy.getLife();
        controller.initializeCombat(mercifulEnemy);
        for (int i = 0; i < 4; i++) {
            assertEquals(mercifulEnemy.getLife(), initMerciLife - i * Constants.PLAYER_ATK);
            controller.handleAction(CombatAction.ATTACK);
        }
        assertTrue(chara.getLife() <= 0);
        assertEquals(mercifulEnemy.getLife(), 10);

        controller.handleAction(CombatAction.OPEN_INVENTORY);
    }
}
