package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;

public final class QuestImpl implements Quest {

    private final String name;
    private final String description;
    private final ObjectiveStrategy objective;

    public QuestImpl(final String name, final String description, final ObjectiveStrategy objective) {
        this.name = name;
        this.description = description;
        this.objective = objective; 
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isCompleted() {
        return this.objective.check();
    }

    @Override
    public Entity getKeyItem() {
        return this.objective.getKeyItem();
    }

}
