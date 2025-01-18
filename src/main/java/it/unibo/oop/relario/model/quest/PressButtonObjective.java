package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;

public class PressButtonObjective implements ObjectiveStrategy {

    @Override
    public boolean check(Entity keyEntity) {
        // verificare se ho interagito con la keyEntity??
        return true;
    }
    
}
