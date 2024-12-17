package it.unibo.oop.relario.model.entities.living;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;

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
    }

    /**
     * A method to test the player's movement mechanisms.
     */
    @Test
    public void testMovement() {
        final MainCharacter chara = new MainCharacterImpl();
        chara.setMovement(Direction.RIGHT);
        for(int i=0; i<5; i++) {chara.update();}

        chara.stop();
        for(int i=0; i<5; i++) {chara.update();}

        assertTrue(chara.getDirection() == Direction.RIGHT);

        chara.setMovement(Direction.DOWN);
        for(int i=0; i<5; i++) {chara.update();}

        assertTrue(
            chara.getPosition().get().getX() == 5
            && chara.getPosition().get().getY()== 5
        );
    }

    /**
     * A method to test the player's behavior in combat scenarios.
     */
    @Test
    public void testCombatScenarios() {
    }
}
