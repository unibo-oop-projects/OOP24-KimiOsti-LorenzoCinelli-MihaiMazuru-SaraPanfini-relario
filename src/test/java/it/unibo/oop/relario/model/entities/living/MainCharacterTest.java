package it.unibo.oop.relario.model.entities.living;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.model.inventory.EffectType;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemFactory;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.Direction;

/*
 * CHECKSTYLE: MagicNumber OFF
 * Used to avoid CheckStyle violations for magic numbers, here used for test scenarios. 
 */

/**
 * The test class for the Main Character class.
 */
public class MainCharacterTest {
    
    /**
     * A method to test the player's inventory.
     */
    @Test
    public void testInventory() {
        final MainCharacter chara = new MainCharacterImpl();
        final InventoryItemFactory itemFactory = new InventoryItemFactoryImpl();
        final InventoryItem healing = itemFactory.createRandomItemByEffect(EffectType.HEALING);
        final InventoryItem weapon = itemFactory.createRandomItemByEffect(EffectType.DAMAGE);
        final InventoryItem armor = itemFactory.createRandomItemByEffect(EffectType.PROTECTION);

        assertTrue(chara.addToInventory(healing));
        assertTrue(chara.addToInventory(weapon));
        assertTrue(chara.addToInventory(armor));
        assertEquals(
            List.of(healing, weapon, armor), 
            chara.getItems()
        );

        for (int i=0; i<5; i++) {
            assertTrue(chara.addToInventory(itemFactory.createRandomItem()));
        }
        assertFalse(chara.addToInventory(itemFactory.createRandomItem()));

        final int baseAtk = chara.attack();
        assertTrue(chara.useItem(weapon));
        assertTrue(chara.attack() == (baseAtk + weapon.getIntensity()));
        assertTrue(chara.addToInventory(itemFactory.createRandomItem()));

        assertTrue(chara.useItem(armor));
        assertTrue(chara.attacked(Constants.DEFAULT_PLAYER_LIFE));
        assertEquals(armor.getIntensity(), chara.getLife());

        /* [TODO] - modify this test (eventually Chara class) to check proper healing */

        assertTrue(chara.discardItem(chara.getItems().getFirst()));
    }

    /**
     * A method to test the player's movement mechanisms.
     */
    @Test
    public void testMovement() {
        final MainCharacter chara = new MainCharacterImpl();
        for(int i=0; i<5; i++) {chara.update();}

        chara.setMovement(Direction.RIGHT);
        for(int i=0; i<5; i++) {chara.update();}

        chara.stop();
        for(int i=0; i<5; i++) {chara.update();}

        assertTrue(chara.getDirection() == Direction.RIGHT);

        chara.setMovement(Direction.DOWN);
        for(int i=0; i<5; i++) {chara.update();}

        assertTrue(
            chara.getPosition().get().getX() == 5
            && chara.getPosition().get().getY() == 5
        );
    }

    /**
     * A method to test the player's behavior in combat scenarios.
     */
    @Test
    public void testCombatScenarios() {
    }
}
