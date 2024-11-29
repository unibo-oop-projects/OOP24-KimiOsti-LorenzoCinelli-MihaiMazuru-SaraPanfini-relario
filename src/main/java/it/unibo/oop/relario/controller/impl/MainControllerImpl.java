package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.api.GameController;
import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.api.MainMenuController;
import it.unibo.oop.relario.model.map.Room;
import it.unibo.oop.relario.view.api.MainView;
import it.unibo.oop.relario.view.impl.MainViewImpl;

/**
 * Implementation for the Controller container class.
 */
public final class MainControllerImpl implements MainController {

    private final CombatController combat;
    private final GameController game;
    private final InventoryController inventory;
    private final MainMenuController mainMenu;
    private final MainView view;
    private Room curRoom;

    /**
     * Initialises the Controller container class.
     */
    public MainControllerImpl() {
        this.view = new MainViewImpl(this);
        //[TODO] - room initialisation
        this.combat = null;
        this.game = new GameControllerImpl(this, this.view);
        this.inventory = null;
        this.mainMenu = null;
    }

    @Override
    public CombatController getCombatController() {
        return this.combat;
    }

    @Override
    public GameController getGameController() {
        return this.game;
    }

    @Override
    public InventoryController getInventoryController() {
        return this.inventory;
    }

    @Override
    public MainMenuController getMainMenuController() {
        return this.mainMenu;
    }

    @Override
    public Room getCurRoom() {
        return this.curRoom;
    }

    @Override
    public Room moveToNextRoom() {
        //[TODO] - moves to the next room.
        return this.curRoom;
    }
}
