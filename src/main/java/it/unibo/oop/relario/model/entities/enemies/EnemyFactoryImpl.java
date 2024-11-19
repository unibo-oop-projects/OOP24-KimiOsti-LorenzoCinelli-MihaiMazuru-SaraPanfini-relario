package it.unibo.oop.relario.model.entities.enemies;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemFactory;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;

/**
 * Implementation of the enemy factory.
 * Provides methods to create enemies with different configurations.
 * It uses a Random object to generate random enemies and a map to associate the enemies' types with their specific attributes.
 * It has an inventory item factory that creates inventory items, used as rewards.
 */

public final class EnemyFactoryImpl implements EnemyFactory {

    private final Map<EnemyType, EnemyConfig> enemiesData = new EnumMap<>(EnemyType.class);
    private final Random random = new Random();
    private final InventoryItemFactory itemFactory;

    /**
     * Constructor of the enemy factory.
     * It initializes the enemies' configurations.
     */
    public EnemyFactoryImpl() {
        enemiesData.put(EnemyType.THIEF, new EnemyConfig("Ladro",
        "Il ladro è un nemico furtivo e abile, specializzato nel rubare oggetti e attaccare di sorpresa",
        DifficultyLevel.MEDIUM));
        enemiesData.put(EnemyType.SOLDIER, new EnemyConfig("Soldato", 
        "Il soldato è un nemico ben addestrato e dotato di armatura leggera", DifficultyLevel.EASY));
        enemiesData.put(EnemyType.WIZARD, new EnemyConfig("Mago", 
        "Il mago è un nemico abile nelle arti arcane, capace di infliggere danni con incantesimi potenti", DifficultyLevel.HARD));
        enemiesData.put(EnemyType.KNIGHT, new EnemyConfig("Cavaliere", 
        "Il cavaliere è un nemico potente, caratterizzato da un'armatura pesante e uno scudo resistente", DifficultyLevel.HARD));
        this.itemFactory = new InventoryItemFactoryImpl();
    }

    @Override
    public Enemy createRandomEnemy(final Position position) {
        final EnemyType randomType = EnemyType.values()[random.nextInt(EnemyType.values().length)];
        final InventoryItem randomReward = itemFactory.createRandomItemByEffect(randomType.getEffect());
        return createEnemy(position, randomType, randomReward);
    }

    @Override
    public Enemy createEnemyWithReward(final Position position, final InventoryItem reward) {
        final List<EnemyType> matchingTypes = enemiesData.keySet().stream()
        .filter(type -> type.getEffect().equals(reward.getEffect())).toList();
        return createEnemy(position, matchingTypes.get(random.nextInt(matchingTypes.size())), reward);
    }

    @Override
    public Enemy createEnemyByType(final EnemyType type, final Position position) {
        return createEnemy(position, type, itemFactory.createRandomItemByEffect(type.getEffect()));
    }

    private Enemy createEnemy(final Position position, final EnemyType type, final InventoryItem reward) {
        final EnemyConfig config = enemiesData.get(type);
        return new EnemyImpl(config.name(), config.description(), position, config.difficulty, reward, random.nextBoolean());
    }

    private record EnemyConfig(String name, String description, DifficultyLevel difficulty) { }

}
