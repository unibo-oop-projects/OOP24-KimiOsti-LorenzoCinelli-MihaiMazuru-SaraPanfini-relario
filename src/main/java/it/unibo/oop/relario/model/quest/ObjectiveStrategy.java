package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;

public interface ObjectiveStrategy {
    boolean check();

    Entity getKeyItem();
}
