package it.unibo.oop.relario.model.quest;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.map.Room;

/**
 * 
 */
public final class QuestImpl implements Quest {

    private final String name;
    private final String description;
    private final ObjectiveStrategy objective;
    private final Room room;

    /**
     * 
     * @param name
     * @param description
     * @param objective
     */
    public QuestImpl(final String name, final String description, final Room room, final ObjectiveStrategy objective) {
        this.name = name;
        this.description = description;
        this.room = room;
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
        return this.objective.check(this.room);
    }

    @Override
    public GameEntityType getKeyEntityType() {
        return this.objective.getKeyEntityType();
    }

    @Override
    public Room getRoom() {
        return this.room;
    }

}
