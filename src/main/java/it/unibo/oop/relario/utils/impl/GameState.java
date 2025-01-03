package it.unibo.oop.relario.utils.impl;

/**
 * Utility static class containing all the game phases.
 */
public final class GameState {

    /** Start menu phase. */
    public static final String MENU = "Menu";

    /** In game menu phase */
    public static final String MENU_IN_GAME = "Menu";

    /** Exploring phase. */
    public static final String GAME = "Game";

    /** Inventory phase. */
    public static final String INVENTORY = "Inventory";

    /** Combat phase. */
    public static final String COMBAT = "Combat";

    /** Game over. */
    public static final String GAME_OVER = "GameOver";

    /** End game. */
    public static final String VICTORY = "Victory";

    /** Initial phase when application is lauched. */
    public static final String NONE = null;

    private GameState() {
        throw new UnsupportedOperationException();
    }

}
