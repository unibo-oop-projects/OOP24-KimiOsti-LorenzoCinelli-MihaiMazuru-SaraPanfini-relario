package it.unibo.oop.relario.model.map;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.entities.living.MainCharacterImpl;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.DimensionImpl;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * This class creates new rooms and handles the generation of furniture and living beings.
 */
public final class RoomGenerator {

    /** The default dimension of a room. */
    public static final Dimension DEFAULT_DIMENSION = new DimensionImpl(20, 13);

    /** TODO. */
    public static final int ROOMS_NUMBER = 4;

    private final Position defaultEntry;
    private final Position defaultExit;
    private final Dimension dimension;
    private final FurnitureGenerator furnitureGenerator;
    private final LivingBeingsGenerator livingBeingsGenerator;
    private final QuestManager questManager;
    private final MainCharacter player;

    /**
     * Constructs a new RoomGenerator instance.
     */
    public RoomGenerator() {
        this.dimension = DEFAULT_DIMENSION;
        this.player = new MainCharacterImpl();
        this.defaultEntry = new PositionImpl(0, (this.dimension.getHeight() - 1) / 2);
        this.defaultExit = new PositionImpl(this.dimension.getWidth() - 1, (this.dimension.getHeight() - 1) / 2);
        this.questManager = new QuestManager();
        this.furnitureGenerator = new FurnitureGenerator();
        this.livingBeingsGenerator = new LivingBeingsGenerator();
    }

    private Room createNewRoom(final int indexRoom) {
        final Room newRoom = new RoomImpl(this.player, this.dimension, defaultEntry, defaultExit);
        this.questManager.assignQuest(newRoom, indexRoom);
        this.furnitureGenerator.generateFurniture(newRoom);
        this.livingBeingsGenerator.generateLivingBeings(newRoom);
        return newRoom;
    }

    /**
     * 
     * @param indexRoom
     * @return TODO
     */
    public Optional<Room> getRoom(final int indexRoom) {
        return indexRoom <= ROOMS_NUMBER ? Optional.of(createNewRoom(indexRoom)) : Optional.empty();
    }

}
