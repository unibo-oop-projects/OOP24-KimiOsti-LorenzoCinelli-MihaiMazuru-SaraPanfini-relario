package it.unibo.oop.relario.model.quest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.entities.npc.Npc;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.model.map.Room;
import it.unibo.oop.relario.model.map.RoomGenerator;
import it.unibo.oop.relario.utils.impl.PositionImpl;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Test class for the {@link Quest} implementation.
 */
final class QuestTest {

    private Room room;

    @BeforeEach
    void setUp() {
        this.room = new RoomGenerator().getRoom(1).get();
    }

    /**
     * Tests the collection quest, ensuring that it is completed
     * only when the required item is added to the player's inventory.
     */
    @Test
    void collectionQuestTest() {
        final Quest quest = new QuestImpl("Trova la chiave", new CollectItemObjective(Optional.of(InventoryItemType.KEY)));
        assertNotNull(quest);
        assertEquals("Trova la chiave", quest.getDescription());
        assertFalse(quest.isCompleted(this.room));
        this.room.getPlayer().addToInventory(new InventoryItemFactoryImpl().createItem(InventoryItemType.KEY));
        assertTrue(quest.isCompleted(this.room));
        assertEquals(Optional.of(InventoryItemType.KEY), quest.getKeyEntityType());
    }

    /**
     * Tests the defeat enemy quest, ensuring that it is completed
     * only when the specified enemy is defeated and is no longer in the room.
     */
    @Test
    void defeatEnemyQuestTest() {
        final Position position = new PositionImpl(this.room.getExit().getX() - 4, this.room.getExit().getY());
        final Quest quest = new QuestImpl("Sconfiggi il nemico", new DefeatEnemyObjective(Optional.of(EnemyType.BOSS)));
        assertNotNull(quest);
        assertEquals("Sconfiggi il nemico", quest.getDescription());
        this.room.addEntity(position, new EnemyFactoryImpl().createEnemyByTypeEmpty(EnemyType.BOSS, position));
        assertFalse(quest.isCompleted(this.room));
        this.room.removeEnemy(position);
        assertTrue(quest.isCompleted(this.room));
        assertEquals(Optional.of(EnemyType.BOSS), quest.getKeyEntityType());
    }

    /**
     * Tests the Npc interaction quest, ensuring it is completed
     * only when the player has interacted with all the Npcs in the room.
     */
    @Test
    void npcInteractQuestTest() {
        final Quest quest = new QuestImpl("Interagisci con gli Npc", new NpcInteractObjective(Optional.empty()));
        assertNotNull(quest);
        assertEquals("Interagisci con gli Npc", quest.getDescription());
        assertFalse(quest.isCompleted(this.room));
        this.room.getPopulation().values().forEach(lb -> {
            if (lb instanceof Npc) {
                ((Npc) lb).interact();
            }
        });
        assertTrue(quest.isCompleted(this.room));
        assertEquals(Optional.empty(), quest.getKeyEntityType());
    }

}
