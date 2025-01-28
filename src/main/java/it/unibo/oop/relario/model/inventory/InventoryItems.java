package it.unibo.oop.relario.model.inventory;

import java.util.Optional;

/**
 * Utility class with items information getters.
 */
public final class InventoryItems {

    private InventoryItems() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the full description of an item, composed by the description, the effect and intensity,
     * and in case of equippable item the durability.
     * @param item the item whose the description is requested.
     * @return a string comprending the full description of the item.
     */
    public static String getFullDescription(final InventoryItem item) {
        return item.getDescription()
        + "\nEffetto: " + item.getEffect().toString()
        + getIntensity(item)
        + getDurability(item);
    }

    /**
     * Returns the full description of the item if equipped, composed by the description,
     * the effect and intensity, and the durability.
     * @param item the item whose the description is requested.
     * @return a string comprending the full description of the item.
     */
    public static String getEquippedItem(final Optional<EquippableItem> item) {
        if (item.isPresent()) {
            final var equippedItem = item.get();
            return equippedItem.getName() + "\n" + getFullDescription(equippedItem);
        } else {
            return "";
        }
    }

    private static String getIntensity(final InventoryItem item) {
        if (item.getEffect() == EffectType.NONE || item.getEffect() == EffectType.QUEST) {
            return "";
        } else {
            return " " + item.getIntensity();
        }
    }

    private static String getDurability(final InventoryItem item) {
        if (item instanceof EquippableItem) {
            return "\nDurabilita\': " + ((EquippableItem) item).getDurability();
        } else {
            return "";
        }
    }
}
