package it.unibo.oop.relario.utils.impl;

/**
 * Utility static class containing all the game phases.
 */
public enum GameState {

    /** Menu phase. */
    MENU("Menu"),

    /** Exploring phase. */
    GAME("Game"),

    /** Inventory phase. */
    INVENTORY("Inventory"),

    /** Combat phase. */
    COMBAT("Combat"),

    /** Game over. */
    GAME_OVER("GameOver"),

    /** Initial phase when application is lauched. */
    NONE(null);

    private final String state;

    /**
     * Creates a new game state.
     * @param state is the new game state to create.
     */
    GameState(final String state) {
        this.state = state;
    }

    /**
     * Retrieves a game state.
     * @return the game state associated.
     */
    public String getState() {
        return this.state;
    }

}
