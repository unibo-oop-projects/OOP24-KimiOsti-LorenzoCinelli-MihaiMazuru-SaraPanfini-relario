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
    private final Room room;

    /**
     * Instantiates the room's quest.
     * @param description a description of the quest.
     * @param room the room in which the quest has to be achieved.s
     * @param objective the objective to be satisfied.
     */
    public QuestImpl(final String description, final Room room, final ObjectiveStrategy objective) {
        this.description = description;
        this.room = room;
        this.objective = objective;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isCompleted() {
        return this.objective.check(this.room);
    }

    @Override
    public Optional<? extends GameEntityType> getKeyEntityType() {
        return this.objective.getKeyEntityType();
    }

    @Override
    public Room getRoom() {
        return this.room;
    }

}
