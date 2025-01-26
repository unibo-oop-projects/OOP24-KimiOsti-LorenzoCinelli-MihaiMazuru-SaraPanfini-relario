package it.unibo.oop.relario.model.entities.npc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Test class for {@link Npc}.
 */
class NpcTest {

    private static final String NPC_NAME = "sans";
    private static final Position POSITION = new PositionImpl(5, 5);
    private static final Optional<InventoryItem> ITEM = Optional.of(
        new InventoryItemFactoryImpl().createRandomItem()
    );
    private static final String DIALOGUE = """
        Knock knock
        Who's there?
        Dishes
        Dishes who?
        Dishes a very bad joke...
    """;

    @Test
    void testDefaultNpcWithLoot() {
        final var defaultNpc = new NpcImpl(
            NPC_NAME,
            POSITION,
            ITEM,
            new DefaultBehavior(new DialoguesGenerator())
        );
        assertFalse(defaultNpc.hasInteracted());
        assertTrue(defaultNpc.interact().getLoot().isPresent());
        assertTrue(defaultNpc.hasInteracted());
        defaultNpc.confirmLootTaken();

        this.testDefaultNpcWithNoLoot(defaultNpc);
    }

    @Test
    void testQuestNpc() {
        final var questNpc = new NpcImpl(
            NPC_NAME,
            POSITION,
            Optional.empty(),
            new QuestBehavior(DIALOGUE)
        );
        assertFalse(questNpc.hasInteracted());
        assertTrue(questNpc.interact().getLoot().isEmpty());
        assertTrue(questNpc.hasInteracted());
        assertEquals(DIALOGUE, questNpc.interact().getDialogue());
    }

    @Test
    void testLootNpc() {
        final var lootNpc = new NpcImpl(
            NPC_NAME,
            POSITION,
            ITEM,
            new LootBehavior(new DialoguesGenerator())
        );
        assertFalse(lootNpc.hasInteracted());
        assertTrue(lootNpc.interact().getLoot().isPresent());
        assertTrue(lootNpc.hasInteracted());
        lootNpc.confirmLootTaken();
        assertTrue(lootNpc.interact().getLoot().isEmpty());
    }

    private void testDefaultNpcWithNoLoot(final Npc npc) {
        assertTrue(npc.interact().getLoot().isEmpty());
    }
}
