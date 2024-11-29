package it.unibo.oop.relario.model.entities.npc;

import java.util.Random;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;

public final class NpcFactoryImpl implements NpcFactory {

    private final DialoguesGenerator dialoguesGenerator = new DialoguesGenerator();
    private final Random random = new Random();

    @Override
    public Npc createRandomNpc(final Position position) {
        return random.nextBoolean() ? this.createInteractiveNpc(position) : this.createNotInteractiveNpc(position);
    }

    @Override
    public Npc createNotInteractiveNpc(final Position position) {
        return new NotInteractiveNpc("", position, this.dialoguesGenerator);
    }

    @Override
    public Npc createInteractiveNpc(final Position position) {
        return this.createNpcWithLoot(position, new InventoryItemFactoryImpl().createRandomItem());
    }

    @Override
    public Npc createNpcWithLoot(final Position position, final InventoryItem loot) {
        return new InteractiveNpc("", position, loot, dialoguesGenerator);
    }

}
