package it.unibo.oop.relario.model.entities.npc;

import java.util.Random;

import it.unibo.oop.relario.model.inventory.InventoryItemFactory;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Implementation of the NPCs' factory, that creates different types of NPCs.
 */
public final class NpcFactoryImpl implements NpcFactory {

    private final DialoguesGenerator dialoguesGenerator = new DialoguesGenerator();
    private final Random random = new Random();
    private final InventoryItemFactory inventoryItemFactory = new InventoryItemFactoryImpl();

    public Npc createDefaultNpc(final Position position) {
        return this.createNotInteractiveNpc(position, new DefaultBehavior(dialoguesGenerator));
    }

    public Npc createQuestNpc(final Position position, final String questDescription) {
        return this.createNotInteractiveNpc(position, new QuestBehavior(questDescription));
    }

    public Npc createInteractiveNpc(final Position position) {
        return this.createNpcWithLoot(position, this.inventoryItemFactory.createRandomItem().getType());
    }

    public Npc createRandomNpc(final Position position) {
        return random.nextBoolean() ? this.createDefaultNpc(position) : this.createInteractiveNpc(position);
    }

    public Npc createNpcWithLoot(final Position position, final InventoryItemType itemType) {
        return new InteractiveNpc("Npc", position, inventoryItemFactory.createItem(itemType), dialoguesGenerator);
    }

    private Npc createNotInteractiveNpc(final Position position, final NpcBehavior behavior) {
        return new NotInteractiveNpc("Npc", position, behavior);
    }

}
