package it.unibo.oop.relario.model.entities.npc;

import java.util.Optional;
import java.util.Random;

import it.unibo.oop.relario.model.inventory.InventoryItem;
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

    @Override
    public Npc createDefaultNpc(final Position position) {
        return this.createNpc("Default Npc", position, new DefaultBehavior(dialoguesGenerator), Optional.empty());
    }

    @Override
    public Npc createQuestNpc(final Position position, final String questDescription) {
        return this.createNpc("Quest Npc", position, new QuestBehavior(questDescription), Optional.empty());
    }

    @Override
    public Npc createInteractiveNpc(final Position position) {
        return this.createNpcWithLoot(position, this.inventoryItemFactory.createRandomItem().getType());
    }

    @Override
    public Npc createRandomNpc(final Position position) {
        return random.nextBoolean() ? this.createDefaultNpc(position) : this.createInteractiveNpc(position);
    }

    @Override
    public Npc createNpcWithLoot(final Position position, final InventoryItemType itemType) {
        return createNpc(
            "Loot Npc",
            position, 
            new LootBehavior(dialoguesGenerator),
            Optional.of(this.inventoryItemFactory.createItem(itemType))
        );
    }

    private Npc createNpc(final String name, final Position position,
        final NpcBehavior behavior, final Optional<InventoryItem> loot) {
            return new NpcImpl(name, position, loot, behavior);
    }

}
