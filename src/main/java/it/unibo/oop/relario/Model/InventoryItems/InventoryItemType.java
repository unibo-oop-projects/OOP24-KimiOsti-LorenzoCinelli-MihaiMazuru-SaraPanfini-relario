package it.unibo.oop.relario.Model.InventoryItems;

public enum InventoryItemType {
    SWORD(EffectType.DAMAGE), BOW(EffectType.DAMAGE), DAGGER(EffectType.DAMAGE), HAMMER(EffectType.DAMAGE),
    SHIELD(EffectType.PROTECTION), BASICARMOR(EffectType.PROTECTION),
    POTION(EffectType.HEALING), APPLE(EffectType.HEALING), AMULET(EffectType.HEALING),
    COIN(EffectType.NONE), GEMSTONE(EffectType.NONE);

    private final EffectType effect;

    InventoryItemType(EffectType effect) {
        this.effect = effect;
    }

    public EffectType getEffect() {
        return this.effect;
    }
}
