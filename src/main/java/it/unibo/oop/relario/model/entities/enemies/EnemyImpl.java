package it.unibo.oop.relario.model.entities.enemies;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.LivingBeingImpl;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;

/**
 * This class implements the Enemy interface and represents an enemy, providing its details.
 */

public final class EnemyImpl extends LivingBeingImpl implements Enemy {

    private final String description;
    private int life;
    private final DifficultyLevel difficulty;
    private final Optional<InventoryItem> reward;
    private final boolean merciful;
    private final EnemyType type;

    /**
     * Constructs a new instance of enemy.
     * @param name of the enemy
     * @param description of the enemy
     * @param position where the enemy has to be set
     * @param difficulty of the enemy
     * @param reward dropped by the enemy after death
     * @param merciful whether the enemy is merciful
     * @param type of the enemy
     */
    public EnemyImpl(final String name, final String description, final Position position,
        final DifficultyLevel difficulty, final Optional<InventoryItem> reward, final boolean merciful, final EnemyType type) {
        super(name, position);
        this.description = description;
        this.difficulty = difficulty;
        this.life = difficulty.getLife();
        this.reward = reward;
        this.merciful = merciful;
        this.type = type;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public int getDamage() {
        return this.difficulty.getDamage();
    }

    @Override
    public DifficultyLevel getDifficulty() {
        return this.difficulty;
    }

    @Override
    public Optional<InventoryItem> getReward() {
        return this.reward;
    }

    @Override
    public boolean isMerciful() {
        return this.merciful;
    }

    @Override
    public void attacked(final int playerDamage) {
        this.life -= playerDamage;
    }

    @Override
    public EnemyType getType() {
        return this.type;
    }

}
