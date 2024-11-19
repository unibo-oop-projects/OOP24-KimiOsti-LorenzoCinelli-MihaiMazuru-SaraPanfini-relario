package it.unibo.oop.relario.model.entities.enemies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItemFactory;
import it.unibo.oop.relario.model.inventory.InventoryItemFactoryImpl;
import it.unibo.oop.relario.utils.api.Position;

public class EnemyFactoryImpl implements EnemyFactory {

    private final Map<EnemyType, EnemyConfig> enemies = new HashMap<>();
    private final Random random = new Random();
    private final InventoryItemFactory itemFactory;

    public EnemyFactoryImpl() {
        enemies.put(EnemyType.THIEF, new EnemyConfig("Ladro", "Il ladro è un nemico furtivo e abile, specializzato nel rubare oggetti e attaccare di sorpresa", DifficultyLevel.MEDIUM));
        enemies.put(EnemyType.SOLDIER, new EnemyConfig("Soldato", "Il soldato è un nemico ben addestrato e dotato di armatura leggera", DifficultyLevel.EASY));
        enemies.put(EnemyType.WIZARD, new EnemyConfig("Mago", "Il mago è un nemico abile nelle arti arcane, capace di infliggere danni con incantesimi potenti", DifficultyLevel.HARD));
        enemies.put(EnemyType.KNIGHT, new EnemyConfig("Cavaliere", "Il cavaliere è un nemico potente, caratterizzato da un'armatura pesante e uno scudo resistente", DifficultyLevel.HARD));
        this.itemFactory = new InventoryItemFactoryImpl();
    }

    @Override
    public Enemy createRandomEnemy(Position position) {
        EnemyType randomType = EnemyType.values()[random.nextInt(EnemyType.values().length)];
        InventoryItem randomReward = itemFactory.createRandomItemByEffect(randomType.getEffect());
        return createEnemy(position, randomType, randomReward);
    }

    @Override
    public Enemy createEnemyWithReward(Position position, InventoryItem reward) {
        final List<EnemyType> matchingTypes = enemies.keySet().stream().filter(type -> type.getEffect().equals(reward.getEffect())).toList();
        return createEnemy(position, matchingTypes.get(random.nextInt(matchingTypes.size())), reward);
    }

    @Override
    public Enemy createEnemyByType(EnemyType type, Position position) {
        return createEnemy(position, type, itemFactory.createRandomItemByEffect(type.getEffect()));
    }

    private Enemy createEnemy(Position position, EnemyType type, InventoryItem reward) {
        EnemyConfig config = enemies.get(type);
        return new EnemyImpl(config.name(), config.description(), position, config.difficulty, reward, random.nextBoolean());
    }

    private record EnemyConfig(String name, String description, DifficultyLevel difficulty) { }
    
}
