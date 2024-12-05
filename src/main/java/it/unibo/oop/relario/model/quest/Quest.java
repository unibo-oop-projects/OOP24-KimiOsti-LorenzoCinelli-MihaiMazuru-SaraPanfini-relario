package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;

public interface Quest {
    String getName();

    String getDescription();

    boolean isCompleted();

    Entity getKeyItem();
}
