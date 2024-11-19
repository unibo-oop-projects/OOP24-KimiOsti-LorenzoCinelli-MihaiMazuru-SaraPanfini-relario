package it.unibo.oop.relario.model.entities.enemies;

public enum DifficultyLevel {

    EASY(30, 5), MEDIUM(60, 10), HARD(90, 20);

    private final int life;
    private final int damage;

    DifficultyLevel(final int life, final int damage) {
        this.life = life;
        this.damage = damage;
    }

    public int getLife() {
        return this.life;
    }

    public int getDamage() {
        return this.damage;
    }

}
