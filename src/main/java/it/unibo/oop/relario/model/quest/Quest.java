package it.unibo.oop.relario.model.quest;

public interface Quest {
    String getName();

    String getDescription();

    void addObjective(ObjectiveStrategy objective);

    boolean isCompleted();
}
