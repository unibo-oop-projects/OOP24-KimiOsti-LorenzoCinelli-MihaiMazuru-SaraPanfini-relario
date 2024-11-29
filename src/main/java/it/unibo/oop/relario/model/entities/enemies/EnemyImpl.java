package it.unibo.oop.relario.model.entities.enemies;

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
    private final InventoryItem reward;
    private final boolean merciful;

    /**
     * Constructs a new instance of enemy.
     * @param name of the enemy
     * @param description of the enemy
     * @param position where the enemy has to be set
     * @param difficulty of the enemy
     * @param reward dropped by the enemy after death
     * @param merciful whether the enemy is merciful
     */
    public EnemyImpl(final String name, final String description, final Position position,
        final DifficultyLevel difficulty, final InventoryItem reward, final boolean merciful) {
        super(name, position);
        this.description = description;
        this.difficulty = difficulty;
        this.life = difficulty.getLife();
        this.reward = reward;
        this.merciful = merciful;
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
    public InventoryItem getReward() {
        return this.reward;
    }

    @Override
    public boolean isMerciful() {
        return this.merciful;
    }

    @Override
    public boolean attacked(final int playerDamage) {
        this.life -= playerDamage;
        return this.life > 0;
    }

}
