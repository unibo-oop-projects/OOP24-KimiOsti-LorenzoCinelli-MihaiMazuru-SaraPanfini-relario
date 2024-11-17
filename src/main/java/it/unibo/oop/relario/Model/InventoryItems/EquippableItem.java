package it.unibo.oop.relario.Model.InventoryItems;

public class EquippableItem extends InventoryItemImpl {

    private final int intensity;
    private int durability;

    public EquippableItem(final String name, final String description, final EffectType effect, final int intensity, final int durability) {
        super(name, description, effect);
        this.intensity = intensity;
        this.durability = durability;
    }

    public int getIntensity() {
        return this.intensity;
    }

    public int getDurability() {
        return this.durability;
    }

    public void setDurability(final int durability) {
        this.durability = durability;
    } 

    /*
    public boolean decreaseDurability() {
        this.durability--;
        return this.durability > 0;
    }
    */
    
}
