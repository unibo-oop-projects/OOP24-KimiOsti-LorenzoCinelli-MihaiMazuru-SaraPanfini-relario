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
    public static final Dimension DEFAULT_DIMENSION = new DimensionImpl(0, 0);

    /** TODO. */
    public static final int ROOMS_NUMBER = 5;

    public static final int FIRST_ROOM = 1;
    public static final int SECOND_ROOM = 2;
    public static final int THIRD_ROOM = 3;
    public static final int FOURTH_ROOM = 4;
    public static final int FIFTH_ROOM = 5;


    private final Position defaultEntry;
    private final Position defaultExit;
    private final Dimension dimension;
    private final FurnitureGenerator furnitureGenerator = new FurnitureGenerator();
    private final LivingBeingsGenerator livingBeingsGenerator = new LivingBeingsGenerator();
    private final QuestManager questManager = new QuestManager();
    private final MainCharacter player;

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
        this.defaultEntry = new PositionImpl(this.dimension.getHeight() / 2, 0);
        this.defaultExit = new PositionImpl(this.dimension.getWidth() - 1, this.dimension.getHeight() / 2);
    }    

    /**
     * Creates a new room with furniture and living beings.
     * @param indexRoom TODO
     * @return a new room
     */
    private Room createNewRoom(final int indexRoom) {
        final Room newRoom = new RoomImpl(this.player, this.dimension, defaultEntry, defaultExit);
        this.furnitureGenerator.generateFurniture(newRoom);
        this.livingBeingsGenerator.generateLivingBeings(newRoom);
        this.questManager.assigneQuest(newRoom, indexRoom);
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
