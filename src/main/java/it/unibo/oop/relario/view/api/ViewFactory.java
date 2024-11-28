package it.unibo.oop.relario.view.api;

import it.unibo.oop.relario.view.impl.CombatView;
import it.unibo.oop.relario.view.impl.GameView;
import it.unibo.oop.relario.view.impl.InventoryView;
import it.unibo.oop.relario.view.impl.MainMenuView;
import it.unibo.oop.relario.view.impl.MainView;

/**
 * Interface for View factory.
 */
public interface ViewFactory {
    /**
     * Creates the main View.
     * @return the main View for the game.
     */
    MainView createMainView();

    /**
     * Creates a View for the main menu.
     * @return a View suitable for the main menu.
     */
    MainMenuView createMainMenuView();

    /**
     * Creates the core game View.
     * @return the View that will run during the game.
     */
    GameView createGameView();

    /**
     * Creates an inventory menu View.
     * @return the View used in the inventory menu.
     */
    InventoryView createInventoryMenuView();

    /**
     * Creates the combat View.
     * @return the View used during combat.
     */
    CombatView createCombatView();
}
