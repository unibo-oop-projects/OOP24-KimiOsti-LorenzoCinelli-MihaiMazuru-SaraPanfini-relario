package it.unibo.oop.relario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.CombatController;
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
import it.unibo.oop.relario.utils.impl.GameState;
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

    private MainView view;

    /**
     * Sets up the view needed to create the combat controller.
     */
    @BeforeEach
    void setUp() {
        view = new MainViewImpl(new MainControllerImpl());
        view.panelsSetup();
    }

    /**
     * Test combat getters and its method to handle combat.
     */
    @Test
    void testCombat() {
        final CombatController controller = new CombatControllerImpl(view);
        final MainCharacter chara = new MainCharacterImpl();
        final Position pos = new PositionImpl(0, 0);
        final InventoryItem item = new InventoryItemFactoryImpl().createRandomItem();
        final Enemy hostileEnemy = new EnemyImpl(EnemyType.SOLDIER.getName(), 
        "Sono un soldato", pos, DifficultyLevel.EASY, item, false, EnemyType.SOLDIER);
        final Enemy mercifulEnemy = new EnemyImpl(EnemyType.WIZARD.getName(), 
        "Sono un mago", pos, DifficultyLevel.HARD, item, true, EnemyType.WIZARD);

        assertEquals(controller.getCombatState(), "");
        controller.initializeCombat(chara, hostileEnemy);
        assertEquals(view.getCurrentPanel(), GameState.COMBAT);
        assertEquals(controller.getDifficultyLevel(), hostileEnemy.getDifficulty());
        assertEquals(controller.getEnemyLife(), hostileEnemy.getLife());
        assertEquals(controller.getEnemyName(), hostileEnemy.getName());
        assertEquals(controller.getPlayerLife(), chara.getLife());

        final int initCharaLife = Constants.DEFAULT_PLAYER_LIFE;
        final int initEnemyLife = hostileEnemy.getLife();
        controller.handleCombatAction(true);
        assertEquals(chara.getLife(), initCharaLife - hostileEnemy.getDamage());
        controller.handleCombatAction(false);
        assertEquals(hostileEnemy.getLife(), initEnemyLife - Constants.DEFAULT_PLAYER_ATK);
        assertEquals(chara.getLife(), initCharaLife - 2 * hostileEnemy.getDamage());
        controller.handleCombatAction(false);
        assertTrue(hostileEnemy.getLife() < 0);
        assertEquals(chara.getItems().get(0), item);
        assertEquals(controller.getCombatState(), chara.getName() + "You've won the combat");

        controller.initializeCombat(chara, mercifulEnemy);
        controller.handleCombatAction(true);
        assertEquals(controller.getCombatState(), controller.getEnemyName() + "accepted your mercy request. /n"
        + "You are free to go.");

        final int initMerciLife = mercifulEnemy.getLife();
        controller.initializeCombat(chara, mercifulEnemy);
        for (int i = 0; i < 4; i++) {
            assertEquals(mercifulEnemy.getLife(), initMerciLife - i * Constants.DEFAULT_PLAYER_ATK);
            controller.handleCombatAction(false);
        }
        assertTrue(chara.getLife() <= 0);
        assertEquals(mercifulEnemy.getLife(), 10);
    }
}
