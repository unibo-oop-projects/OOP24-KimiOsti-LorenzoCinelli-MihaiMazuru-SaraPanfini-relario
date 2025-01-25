package it.unibo.oop.relario.model.quest;

import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.map.Room;

/**
 * Implementation for a room's quest.
 */
public final class QuestImpl implements Quest {

    private final String description;
    private final ObjectiveStrategy objective;

    /**
     * Instantiates the room's quest.
     * @param description a description of the quest.
     * @param objective the objective to be satisfied.
     */
    public QuestImpl(final String description, final ObjectiveStrategy objective) {
        this.description = description;
        this.objective = objective;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isCompleted(final Room room) {
        return this.objective.check(room);
    }

    @Override
    public Optional<? extends GameEntityType> getKeyEntityType() {
        return this.objective.getKeyEntityType();
    }

}
