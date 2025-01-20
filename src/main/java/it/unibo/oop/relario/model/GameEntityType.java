package it.unibo.oop.relario.model;

import it.unibo.oop.relario.model.inventory.EffectType;

public interface GameEntityType {

    /**
     * Retrieves the effect associated with the game entity type.
     * @return the effect of the entity
     */
    public EffectType getEffect();

}
