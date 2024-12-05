package it.unibo.oop.relario.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.entities.living.MainCharacterImpl;
import it.unibo.oop.relario.model.quest.Quest;
import it.unibo.oop.relario.model.quest.QuestFactory;
import it.unibo.oop.relario.model.quest.QuestFactoryImpl;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * This class creates new rooms and handles the generation of furniture and living beings.
 */
public final class RoomGenerator {

    /** The default dimension of a room. */
    public static final Dimension DEFAULT_DIMENSION = new DimensionImpl(0, 0);

    public static final int ROOMS_NUMBER = 5;

    private final Position default_entry;
    private final Position default_exit;
    private final Dimension dimension;
    private final FurnitureGenerator furnitureGenerator = new FurnitureGenerator();
    private final LivingBeingsGenerator livingBeingsGenerator = new LivingBeingsGenerator();
    private final MainCharacter player;
    private final Map<Integer, Optional<Quest>> questMap = new HashMap<>();
    private final QuestFactory questFactory = new QuestFactoryImpl();

    /*public RoomGenerator(final Dimension dimension) {
        this.dimension = dimension;
        this.default_entry = new PositionImpl(this.dimension.getHeight() / 2, 0);
        this.default_exit = new PositionImpl(this.dimension.getWidth() - 1, this.dimension.getHeight() / 2);
        this.player = new MainCharacterImpl();
    }*/

    /**
     * Constructs a new RoomGenerator instance.
     */
    public RoomGenerator() {
        this.dimension = DEFAULT_DIMENSION;
        this.player = new MainCharacterImpl();
        this.default_entry = new PositionImpl(this.dimension.getHeight() / 2, 0);
        this.default_exit = new PositionImpl(this.dimension.getWidth() - 1, this.dimension.getHeight() / 2);
        initializeQuestMap();
    }

    private void initializeQuestMap() {
        this.questMap.put(1, Optional.empty());
        this.questMap.put(2, Optional.of(this.questFactory.createCollectItemQuest(null, player)));
        // this.questFactory.createCollectItemQuest(InventoryItemType.values()[random.nextInt(InventoryItemType.values().length)]
        this.questMap.put(3, Optional.of(this.questFactory.createSolvePuzzleQuest()));
        this.questMap.put(4, Optional.of(this.questFactory.createDefeatEnemyQuest()));
        this.questMap.put(5, Optional.empty());
    }

    /**
     * Creates a new room with furniture and living beings.
     * @param player the main character that is placed in the new room
     * @return a new room
     */
    private Room createNewRoom(final Optional<Quest> quest) {
        final Room newRoom = new RoomImpl(this.player, this.dimension, default_entry, default_exit, quest);
        // newRoom.add(quest.getKeyItem());
        this.furnitureGenerator.generateFurniture(newRoom);
        this.livingBeingsGenerator.generateLivingBeings(newRoom);
        return newRoom;
    }

    public Optional<Room> getNextRoom(final int indexRoom) {
        final Optional<Quest> quest = this.questMap.get(indexRoom);
        return indexRoom <= ROOMS_NUMBER ? Optional.of(createNewRoom(quest)) : Optional.empty();
    }

}
