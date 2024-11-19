package it.unibo.oop.relario.view.api;

/**
 * Interface for View factory.
 */
public interface ViewFactory {
    /**
     * Creates a View for the main menu.
     * @return a View suitable for the main menu.
     */
    View createMainMenuView();

    /**
     * Creates the core game View.
     * @return the View that will run during the game.
     */
    View createGameView();

    /**
     * Creates an inventory menu View.
     * @return the View used in the inventory menu.
     */
    View createInventoryMenuView();

    /**
     * Creates the combat View.
     * @return the View used during combat.
     */
    View createCombatView();
}
