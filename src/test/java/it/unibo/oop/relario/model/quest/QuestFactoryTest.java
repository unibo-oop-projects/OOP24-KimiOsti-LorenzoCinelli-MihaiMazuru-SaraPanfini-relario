package it.unibo.oop.relario.model.quest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.entities.npc.Npc;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Test class for {@link QuestFactory}.
 */
class QuestFactoryTest {

    private static final String DEFAULT_QUEST_DESCRIPTION = """
        Bring some love to this wicked world.
    """;
    private static final InventoryItemType ITEM_TYPE = InventoryItemType.KEY;
    private static final EnemyType ENEMY_TYPE = EnemyType.BOSS;
    private static final Position ENEMY_POS = new PositionImpl(5, 5);

    private final QuestFactory factory;
    private final MainController controller;

    QuestFactoryTest() {
        this.factory = new QuestFactoryImpl();
        this.controller = new MainControllerImpl();
        this.controller.moveToNextRoom();
    }

    @Test
    void testCreateCollectionQuest() {
        assertTrue(this.controller.getCurRoom().isPresent());

        this.addQuest(this.getQuest(QuestType.COLLECTION_QUEST, ITEM_TYPE));

        assertFalse(this.controller.getCurRoom().get().getQuest().get().isCompleted(
            this.controller.getCurRoom().get()
        ));
        assertTrue(this.controller.getCurRoom().get().getPlayer().addToInventory(
            new InventoryItemFactoryImpl().createItem(
                ITEM_TYPE
            )
        ));
        assertTrue(this.controller.getCurRoom().get().getQuest().get().isCompleted(
            this.controller.getCurRoom().get()
        ));
    }

    @Test
    void testCreateDefeatEnemyQuest() {
        this.addQuest(this.getQuest(QuestType.DEFEAT_ENEMY_QUEST, ENEMY_TYPE));

        final var enemy = new EnemyFactoryImpl().createEnemyByType(ENEMY_TYPE, ENEMY_POS);
        assertFalse(this.controller.getCurRoom().get().getPopulation().containsValue(enemy));
        this.controller.getCurRoom().get().addEntity(ENEMY_POS, enemy);
        assertTrue(this.controller.getCurRoom().get().getPopulation().containsValue(enemy));
        assertFalse(this.controller.getCurRoom().get().getQuest().get().isCompleted(
            this.controller.getCurRoom().get()
        ));

        enemy.attacked(enemy.getLife());
        assertEquals(0, enemy.getLife());
        assertTrue(enemy.getPosition().isPresent());
        this.controller.getCurRoom().get().removeEnemy(enemy.getPosition().get());

        assertTrue(this.controller.getCurRoom().get().getQuest().get().isCompleted(
            this.controller.getCurRoom().get()
        ));
    }

    @Test
    void testCreateNpcInteractQuest() {
        this.addQuest(this.getQuest(QuestType.NPC_INTERACT_QUEST, null));

        assertFalse(this.controller.getCurRoom().get().getQuest().get().isCompleted(
            this.controller.getCurRoom().get()
        ));
        this.controller.getCurRoom()
        .get()
        .getPopulation()
        .values()
        .stream()
        .filter(being -> being instanceof Npc)
        .forEach(being -> ((Npc) being).interact());
        assertTrue(this.controller.getCurRoom().get().getQuest().get().isCompleted(
            this.controller.getCurRoom().get()
        ));
    }

    private Quest getQuest(final QuestType questType, final GameEntityType entityType) {
        return this.factory.createQuestByType(
            DEFAULT_QUEST_DESCRIPTION,
            questType,
            entityType != null ? Optional.of(entityType) : Optional.empty()
        );
    }

    private void addQuest(final Quest quest) {
        assertTrue(this.controller.getCurRoom().isPresent());
        this.controller.getCurRoom().get().setQuest(Optional.of(quest));
        assertTrue(this.controller.getCurRoom().get().getQuest().isPresent());
        assertEquals(quest, this.controller.getCurRoom().get().getQuest().get());
    }
}
