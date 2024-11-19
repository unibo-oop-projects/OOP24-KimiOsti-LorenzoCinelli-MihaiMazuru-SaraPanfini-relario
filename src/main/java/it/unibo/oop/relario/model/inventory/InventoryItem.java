package it.unibo.oop.relario.model.inventory;

/**
 * An interface representing an inventory item.
 */

public interface InventoryItem {

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

}
