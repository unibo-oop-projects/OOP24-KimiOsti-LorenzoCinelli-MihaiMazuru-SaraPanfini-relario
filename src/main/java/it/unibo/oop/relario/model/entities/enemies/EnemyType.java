package it.unibo.oop.relario.model.entities.enemies;

import it.unibo.oop.relario.model.inventory.EffectType;

public enum EnemyType {
    THIEF(EffectType.NONE),
    SOLDIER(EffectType.DAMAGE),
    KNIGHT(EffectType.PROTECTION),
    WIZARD(EffectType.HEALING);

    private final EffectType effect;

    EnemyType(final EffectType effect) {
        this.effect = effect;
    }

    public EffectType getEffect() {
        return this.effect;
    }
}
