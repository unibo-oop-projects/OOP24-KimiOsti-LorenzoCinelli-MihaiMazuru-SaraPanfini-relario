package it.unibo.oop.relario.model.entities.enemies;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;

/**
 * This class implements the Enemy interface and represents an enemy, providing its details.
 */

public final class EnemyImpl implements Enemy {

    /** The number of updates after which the enemy changes direction. */
    public static final int DIRECTION_RANGE = 2;

    private final String name;
    private final String description;
    private Position position;
    private int life;
    private final DifficultyLevel difficulty;
    private final InventoryItem reward;
    private final boolean merciful;
    private Direction direction;
    private int counter = 0;

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
        this.name = name;
        this.description = description;
        this.position = position;
        this.difficulty = difficulty;
        this.life = difficulty.getLife();
        this.reward = reward;
        this.merciful = merciful;
        this.direction = Direction.RIGHT;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Position getPosition() {
        return this.position;
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

    @Override
    public void update() {
        counter++;
        if (counter > DIRECTION_RANGE) {
            this.direction = this.direction.equals(Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT;
            counter = 1;
        } 
        setPosition(this.direction.move(position));
    }

    private void setPosition(final Position position) {
        this.position = position;
    }

}
