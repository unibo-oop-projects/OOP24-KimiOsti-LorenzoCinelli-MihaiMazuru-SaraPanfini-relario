package it.unibo.oop.relario.model.entities.enemies;

import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;

public class EnemyImpl implements Enemy {

    private final String name;
    private final String description;
    private final Position position;
    private int life;
    private final DifficultyLevel difficulty;
    private final InventoryItem reward;
    private final boolean merciful;

    public EnemyImpl(final String name, final String description, final Position position, final DifficultyLevel difficulty, final InventoryItem reward, final boolean merciful) {
        this.name = name;
        this.description = description;
        this.position = position;
        this.difficulty = difficulty;
        this.life = difficulty.getLife();
        this.reward = reward;
        this.merciful = merciful;
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
    public boolean attacked(int playerDamage) {
        this.life -= playerDamage;
        return this.life > 0;
    }

    @Override
    public void update() {
        
    }
    
}
