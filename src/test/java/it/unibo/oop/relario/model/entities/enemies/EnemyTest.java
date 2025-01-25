package it.unibo.oop.relario.model.entities.enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.model.inventory.EffectType;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Test class for the {@link Enemy} interface.
 */
final class EnemyTest {

    private final EnemyFactory factory = new EnemyFactoryImpl();
    private final Map<EnemyType, Enemy> fullEnemies = new EnumMap<>(EnemyType.class);
    private final Map<EnemyType, Enemy> emptyEnemies = new EnumMap<>(EnemyType.class);

    private final Map<EnemyType, String> descriptions = new EnumMap<>(EnemyType.class);
    private final Map<EnemyType, DifficultyLevel> difficulty = new EnumMap<>(EnemyType.class);
    private final Map<EnemyType, EffectType> reward = new EnumMap<>(EnemyType.class);

    /**
     * Sets up the testing.
     */
    @BeforeEach
    void setUp() {
        for (final var type : EnemyType.values()) {
            fullEnemies.put(type, this.factory.createEnemyByType(type, new PositionImpl(0, 0)));
            emptyEnemies.put(type, this.factory.createEnemyByTypeEmpty(type, new PositionImpl(0, 0)));
        }
        this.initializeMaps();
    }

    /**
     * Tests getters methods.
     */
    @Test
    void testGetters() {
        for (final var type : EnemyType.values()) {
            final var enemy = fullEnemies.get(type);
            enemyCheck(type, enemy, true);

            final var emptyEnemy = emptyEnemies.get(type);
            enemyCheck(type, emptyEnemy, false);
        }
    }

    /**
     * Tests when the enemy is attacked.
     */
    @Test
    void testAttacked() {
        int damage = 10;
        for (final var type : EnemyType.values()) {
            final var enemy = fullEnemies.get(type);
            attackCheck(type, enemy, damage);

            final var emptyEnemy = emptyEnemies.get(type);
            attackCheck(type, emptyEnemy, damage);

            damage += 10;
        }
    }

    private void initializeMaps() {
        var type = EnemyType.BOSS;
        descriptions.put(type, "Il boss è un nemico temibile, che solo i più forti riescono a sconfiggere");
        difficulty.put(type, DifficultyLevel.HARD);
        reward.put(type, EffectType.QUEST);

        type = EnemyType.KNIGHT;
        descriptions.put(type, "Il cavaliere è un nemico potente, caratterizzato da un'armatura pesante e uno scudo resistente");
        difficulty.put(type, DifficultyLevel.MEDIUM);
        reward.put(type, EffectType.PROTECTION);

        type = EnemyType.SOLDIER;
        descriptions.put(type, "Il soldato è un nemico ben addestrato e dotato di armatura leggera");
        difficulty.put(type, DifficultyLevel.EASY);
        reward.put(type, EffectType.DAMAGE);

        type = EnemyType.THIEF;
        descriptions.put(type, "Il ladro è un nemico furtivo e abile, specializzato nel rubare oggetti e attaccare di sorpresa");
        difficulty.put(type, DifficultyLevel.EASY);
        reward.put(type, EffectType.NONE);

        type = EnemyType.WIZARD;
        descriptions.put(type, "Il mago è un nemico abile nelle arti arcane, capace di infliggere danni con incantesimi potenti");
        difficulty.put(type, DifficultyLevel.MEDIUM);
        reward.put(type, EffectType.HEALING);
    }

    private void enemyCheck(final EnemyType type, final Enemy enemy, final boolean full) {
        assertEquals(descriptions.get(type), enemy.getDescription());
        assertEquals(difficulty.get(type).getLife(), enemy.getLife());
        assertEquals(difficulty.get(type).getDamage(), enemy.getDamage());
        assertEquals(difficulty.get(type), enemy.getDifficulty());
        if (full) {
            assertEquals(reward.get(type), enemy.getReward().get().getEffect());
        } else {
            assertEquals(Optional.empty(), enemy.getReward());
        }
        assertEquals(type, enemy.getType());
    }

    private void attackCheck(final EnemyType type, final Enemy enemy, final int damage) {
        assertEquals(difficulty.get(type).getLife(), enemy.getLife());
        enemy.attacked(damage);
        assertEquals(difficulty.get(type).getLife() - damage, enemy.getLife());
    }
}
