package it.unibo.oop.relario.model.entities.enemies;

import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.model.inventory.InventoryItem;

public interface Enemy extends LivingBeing {

    String getDescription();

    int getLife();

    int getDamage();

    DifficultyLevel getDifficulty();

    InventoryItem getReward();

    boolean isMerciful();

    boolean attacked(int playerDamage);
    
}
