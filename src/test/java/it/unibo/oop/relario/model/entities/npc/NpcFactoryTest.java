package it.unibo.oop.relario.model.entities.npc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Test class for {@link NpcFactory}.
 */
class NpcFactoryTest {
    private static final String TEST_QUEST_DESCRIPTION = "Stay determined...";

    private final NpcFactory factory;
    private final SecureRandom randomGenerator;

    NpcFactoryTest() {
        this.factory = new NpcFactoryImpl();
        this.randomGenerator = new SecureRandom();
    }

    @Test
    void createDefaultNpc() {
        final var testX = randomGenerator.nextInt();
        final var testY = randomGenerator.nextInt();
        final var defaultNpc = this.factory.createDefaultNpc(
            new PositionImpl(testX, testY)
        );
        assertTrue(defaultNpc.getPosition().isPresent());
        assertEquals(new PositionImpl(testX, testY), defaultNpc.getPosition().get());
        assertTrue(defaultNpc.interact().getLoot().isEmpty());
    }

    @Test
    void createInteractiveNpc() {
        final var testX = this.randomGenerator.nextInt();
        final var testY = this.randomGenerator.nextInt();
        final var interactiveNpc = this.factory.createInteractiveNpc(
            new PositionImpl(testX, testY)
        );
        assertTrue(interactiveNpc.getPosition().isPresent());
        assertEquals(new PositionImpl(testX, testY), interactiveNpc.getPosition().get());
        assertTrue(interactiveNpc.interact().getLoot().isPresent());
    }

    @Test
    void createNpcWithLoot() {
        final var testX = this.randomGenerator.nextInt();
        final var testY = this.randomGenerator.nextInt();
        final var lootNpc = this.factory.createNpcWithLoot(
            new PositionImpl(testX, testY),
            InventoryItemType.COIN
        );
        assertTrue(lootNpc.getPosition().isPresent());
        assertEquals(new PositionImpl(testX, testY), lootNpc.getPosition().get());
        assertTrue(lootNpc.interact().getLoot().isPresent());
        assertEquals(InventoryItemType.COIN, lootNpc.interact().getLoot().get().getType());
        lootNpc.confirmLootTaken();
        assertTrue(lootNpc.interact().getLoot().isEmpty());
    }

    @Test
    void createQuestNpc() {
        final var testX = this.randomGenerator.nextInt();
        final var testY = this.randomGenerator.nextInt();
        final var questNpc = this.factory.createQuestNpc(
            new PositionImpl(testX, testY),
            TEST_QUEST_DESCRIPTION
        );
        assertTrue(questNpc.getPosition().isPresent());
        assertEquals(new PositionImpl(testX, testY), questNpc.getPosition().get());
        assertTrue(questNpc.interact().getLoot().isEmpty());
        assertEquals(TEST_QUEST_DESCRIPTION, questNpc.interact().getDialogue());
    }
}
