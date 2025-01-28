package it.unibo.oop.relario.model.entities.enemies;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemFactory;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
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
        enemiesData.put(EnemyType.THIEF, new EnemyConfig(EnemyType.THIEF.getName(),
        "Il ladro è un nemico furtivo e abile, specializzato nel rubare oggetti e attaccare di sorpresa", 
        DifficultyLevel.EASY));
        enemiesData.put(EnemyType.SOLDIER, new EnemyConfig(EnemyType.SOLDIER.getName(),
        "Il soldato è un nemico ben addestrato e dotato di armatura leggera", 
        DifficultyLevel.EASY));
        enemiesData.put(EnemyType.WIZARD, new EnemyConfig(EnemyType.WIZARD.getName(),
        "Il mago è un nemico abile nelle arti arcane, capace di infliggere danni con incantesimi potenti", 
        DifficultyLevel.MEDIUM));
        enemiesData.put(EnemyType.KNIGHT, new EnemyConfig(EnemyType.KNIGHT.getName(), 
        "Il cavaliere è un nemico potente, caratterizzato da un'armatura pesante e uno scudo resistente", 
        DifficultyLevel.MEDIUM));
        enemiesData.put(EnemyType.BOSS, new EnemyConfig(EnemyType.BOSS.getName(), 
        "Il boss è un nemico temibile, che solo i più forti riescono a sconfiggere", DifficultyLevel.HARD));
        this.itemFactory = new InventoryItemFactoryImpl();
    }

    @Override
    public Enemy createRandomEnemy(final Position position) {
        final List<EnemyType> availableTypes = Stream.of(EnemyType.values())
        .filter(t -> !t.equals(EnemyType.BOSS)).collect(Collectors.toList());
        return this.createEnemyByType(availableTypes.get(random.nextInt(availableTypes.size())), position);
    }

    @Override
    public Enemy createEnemyWithReward(final Position position, final InventoryItemType reward) {
        final List<EnemyType> matchingTypes = enemiesData.keySet().stream()
        .filter(type -> type.getEffect().equals(reward.getEffect())).toList();
        if (matchingTypes.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return this.createEnemy(position, matchingTypes.get(random.nextInt(matchingTypes.size())), 
        Optional.of(this.itemFactory.createItem(reward)));
    }

    @Override
    public Enemy createEnemyByType(final EnemyType type, final Position position) {
        this.validateEnemyType(type);
        return this.createEnemy(position, type, Optional.of(this.itemFactory.createRandomItemByEffect(type.getEffect())));
    }

    @Override
    public Enemy createEnemyByTypeEmpty(final EnemyType type, final Position position) {
        this.validateEnemyType(type);
        return this.createEnemy(position, type, Optional.empty());
    }

    private Enemy createEnemy(final Position position, final EnemyType type, final Optional<InventoryItem> reward) {
        final EnemyConfig config = enemiesData.get(type);
        final boolean isMerciful; 
        if (type.equals(EnemyType.BOSS)) {
            isMerciful = false;
        } else {
            isMerciful = random.nextBoolean();
        }
        return new EnemyImpl(config.name(), config.description(), position, 
        config.difficulty, reward, isMerciful, type);
    }

    private void validateEnemyType(final EnemyType type) {
        if (!this.enemiesData.containsKey(type)) {
            throw new IllegalArgumentException();
        }
    }

    private record EnemyConfig(String name, String description, DifficultyLevel difficulty) { }

}
