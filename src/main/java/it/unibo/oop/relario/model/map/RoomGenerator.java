package it.unibo.oop.relario.model.map;

import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.utils.api.Dimension;
import it.unibo.oop.relario.utils.impl.DimensionImpl;

public class RoomGenerator {

    public static final Dimension DEFAULT_DIMENSION = new DimensionImpl(0, 0);

    private final FurnitureGenerator furnitureGenerator;
    private final LivingBeingsGenerator livingBeingsGenerator;

    public RoomGenerator() {
        this.furnitureGenerator = new FurnitureGenerator();
        this.livingBeingsGenerator = new LivingBeingsGenerator();
    }
    
    public Room createNewRoom(MainCharacter player) {
        Room newRoom = new RoomImpl(DEFAULT_DIMENSION, player);
        this.furnitureGenerator.generateFurniture(newRoom);
        this.livingBeingsGenerator.generateLivingBeings(newRoom);
        return newRoom;
    }

}
