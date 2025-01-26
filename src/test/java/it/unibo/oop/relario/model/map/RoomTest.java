package it.unibo.oop.relario.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.furniture.impl.FurnitureFactoryImpl;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.entities.living.MainCharacterImpl;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.model.quest.QuestFactoryImpl;
import it.unibo.oop.relario.model.quest.QuestType;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/* TODO aggiungere in initialization il test sulle caselle disponibili + rivedere test update*/

/**
 * Tester class for {@link Room}.
 */
class RoomTest {

    private static final MainCharacter MAIN_CHARA = new MainCharacterImpl();
    private static final Dimension ROOM_DIMENSION = new DimensionImpl(20, 21);
    private static final Position ENTRY = new PositionImpl(0, 10);
    private static final Position EXIT = new PositionImpl(19, 10);
    private static final String QUEST_DESCRIPTION = "Quest description !";
    private static final InventoryItemType ITEM_TYPE = InventoryItemType.KEY;
    private static final QuestType QUEST_TYPE = QuestType.COLLECTION_QUEST;
    private static final int REFRESHES = 100;

    private final Room room;

    RoomTest() {
        this.room = new RoomImpl(MAIN_CHARA, ROOM_DIMENSION, ENTRY, EXIT);
    }

    @Test
    void testRoomInitialisation() {
        assertEquals(MAIN_CHARA, this.room.getPlayer());
        assertTrue(this.room.getQuest().isEmpty());
        assertEquals(ROOM_DIMENSION, this.room.getDimension());
        assertEquals(0, this.room.getPopulation().size());
        assertEquals(0, this.room.getFurniture().size());
        for (int i = 0; i < this.room.getDimension().getWidth(); i++) {
            for (int j = 0; j < this.room.getDimension().getHeight(); j++) {
                assertTrue(this.room.getCellContent(new PositionImpl(i, j)).isEmpty());
            }
        }
        this.room.setQuest(Optional.of(
            new QuestFactoryImpl().createQuestByType(
                QUEST_DESCRIPTION,
                QUEST_TYPE,
                Optional.of(ITEM_TYPE)
            )
        ));
        assertTrue(this.room.getQuest().isPresent());
        assertFalse(this.room.getQuest().get().isCompleted(this.room));
        this.room.getPlayer().addToInventory(new InventoryItemFactoryImpl().createItem(ITEM_TYPE));
        assertTrue(this.room.getQuest().get().isCompleted(this.room));
    }

    @Test
    void testRoomEntities() {
        final var x = this.room.getDimension().getWidth() / 2;
        for (int y = 0; y < this.room.getDimension().getHeight(); y++) {
            this.room.addEntity(
                new PositionImpl(x, y),
                new EnemyFactoryImpl().createRandomEnemy(new PositionImpl(x, y))
            );
            assertTrue(this.room.getCellContent(new PositionImpl(x, y)).isPresent());
        }
        assertEquals(this.room.getDimension().getHeight(), this.room.getPopulation().size());

        this.room.getPopulation().forEach((position, entity) -> {
            assertEquals(entity, this.room.getCellContent(position).get());
            assertEquals(Direction.RIGHT, entity.getDirection());
        });

        final var position = new PositionImpl(2, 5);
        this.room.addEntity(position, new EnemyFactoryImpl().createRandomEnemy(position));
        assertEquals(this.room.getDimension().getHeight() + 1, this.room.getPopulation().size());
        this.room.removeEnemy(position);
        assertEquals(this.room.getDimension().getHeight(), this.room.getPopulation().size());
    }

    @Test
    void testRoomFurniture() {
        final var x = (this.room.getDimension().getWidth() / 2) + 1;
        for (int y = 0; y < this.room.getDimension().getHeight(); y++) {
            this.room.addEntity(
                new PositionImpl(x, y), 
                new FurnitureFactoryImpl().createRandomObstructingFurniture(new PositionImpl(x, y))
            );
            assertTrue(this.room.getCellContent(new PositionImpl(x, y)).isPresent());
        }
        assertEquals(this.room.getDimension().getHeight(), this.room.getFurniture().size());

        this.room.getFurniture().forEach(
            (position, furniture) -> assertEquals(furniture, this.room.getCellContent(position).get())
        );
    }

    @Test
    void testRoomUpdate() {
        this.room.getPlayer().stop();

        for (int i = 0; i < REFRESHES; i++) {
            this.room.update();
        }

        int x = (this.room.getDimension().getWidth() / 2);
        for (int y = 0; y < this.room.getDimension().getHeight(); y++) {
            assertTrue(room.getCellContent(new PositionImpl(x, y)).isPresent());
            assertTrue(room.getCellContent(new PositionImpl(x + 1, y)).isPresent());
        }

        assertEquals(this.room.getDimension().getHeight(), this.room.getPopulation().size());
        assertEquals(this.room.getDimension().getHeight(), this.room.getFurniture().size());
    }
}
