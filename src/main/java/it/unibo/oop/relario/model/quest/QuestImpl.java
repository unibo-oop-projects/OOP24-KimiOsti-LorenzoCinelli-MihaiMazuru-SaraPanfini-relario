package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.entities.Entity;

public final class QuestImpl implements Quest {

    private final String name;
    private final String description;
    private final ObjectiveStrategy objective;
    private final Entity keyEntity;

    public QuestImpl(final String name, final String description, final ObjectiveStrategy objective, final Entity keyEntity) {
        this.name = name;
        this.description = description;
        this.objective = objective;
        this.keyEntity = keyEntity;
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
        return this.objective.check(this.keyEntity);
    }

    @Override
    public Entity getKeyEntity() {
        return this.keyEntity;
    }

}
