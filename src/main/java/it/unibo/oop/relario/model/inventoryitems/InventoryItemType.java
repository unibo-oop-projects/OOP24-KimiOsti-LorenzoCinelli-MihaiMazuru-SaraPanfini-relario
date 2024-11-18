package it.unibo.oop.relario.model.inventoryitems;

public enum InventoryItemType {
    SWORD(EffectType.DAMAGE), BOW(EffectType.DAMAGE), DAGGER(EffectType.DAMAGE), HAMMER(EffectType.DAMAGE),
    SHIELD(EffectType.PROTECTION), BASICARMOR(EffectType.PROTECTION),
    POTION(EffectType.HEALING), APPLE(EffectType.HEALING), AMULET(EffectType.HEALING),
    COIN(EffectType.NONE), GEMSTONE(EffectType.NONE);

    private final EffectType effect;

    InventoryItemType(final EffectType effect) {
        this.effect = effect;
    }

    public EffectType getEffect() {
        return this.effect;
    }
}
