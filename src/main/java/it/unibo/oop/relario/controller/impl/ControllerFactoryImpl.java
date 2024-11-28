package it.unibo.oop.relario.controller.impl;

import it.unibo.oop.relario.controller.api.GameController;
import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.controller.api.MainMenuController;
import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.api.ControllerFactory;

/**
 * Implementation for the controller factory.
 */
public final class ControllerFactoryImpl implements ControllerFactory {

    @Override
    public MainMenuController createMainMenuController() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMainMenuController'");
    }

    @Override
    public GameController createGameController() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createGameController'");
    }

    @Override
    public InventoryController createInventoryController() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createInventoryMenuController'");
    }

    @Override
    public CombatController createCombatController() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCombatController'");
    }
}
