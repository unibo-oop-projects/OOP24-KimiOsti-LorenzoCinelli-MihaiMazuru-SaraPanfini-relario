package it.unibo.oop.relario.model.entities.npc;

import java.util.Random;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;

public class NpcFactoryImpl implements NpcFactory {

    private final DialoguesGenerator dialoguesGenerator = new DialoguesGenerator();

    @Override
    public Npc createRandomNpc(Position position) {
        return new Random().nextBoolean() ? this.createInteractiveNpc(position) : this.createNotInteractiveNpc(position);
    }

    @Override
    public Npc createNotInteractiveNpc(Position position) {
        return new NotInteractiveNpc("", position, this.dialoguesGenerator);
    }

    @Override
    public Npc createInteractiveNpc(Position position) {
        return this.createNpcWithLoot(position, new InventoryItemFactoryImpl().createRandomItem());
    }

    @Override
    public Npc createNpcWithLoot(Position position, InventoryItem loot) {
        return new InteractiveNpc("", position, loot, dialoguesGenerator);
    }
    
}
