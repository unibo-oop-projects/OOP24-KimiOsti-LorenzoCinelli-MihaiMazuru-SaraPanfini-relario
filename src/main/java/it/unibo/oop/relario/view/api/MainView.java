package it.unibo.oop.relario.view.api;

/**
 * Interface for the main container for View.
 */
public interface MainView {

    /**
     * Set visibile the combat panel.
     */
    void showCombatView();

    /**
     * Set visible the game panel.
     */
    void showGameView();

    /**
     * Set visible the inventory panel.
     */
    void showInventoryView();

    /**
     * Set visible the main menu panel. 
     */
    void showMainMenuView();
}
