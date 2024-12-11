package it.unibo.oop.relario.model.inventory;

import it.unibo.oop.relario.model.entities.Entity;

/**
 * An interface representing an inventory item.
 */

public interface InventoryItem extends Entity {

    /**
     * Retrieves the name of the inventory item.
     * @return the name of the item
     */
    String getName();

    /**
     * Retrieves the description of the inventory item.
     * @return the description of the item
     */
    String getDescription();

    /**
     * Retrieves the effect of the inventory item.
     * @return the EffectType associated with the item
     */
    EffectType getEffect();

    /**
     * Retrieves the intensity of the equippable item.
     * @return the intensity of the item
     */
    int getIntensity();

}
