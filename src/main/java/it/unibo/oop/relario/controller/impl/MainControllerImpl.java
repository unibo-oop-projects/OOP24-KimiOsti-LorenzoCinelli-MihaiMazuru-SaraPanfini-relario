package it.unibo.oop.relario.controller.impl;

import java.util.Optional;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.controller.api.CutSceneController;
import it.unibo.oop.relario.controller.api.GameController;
import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.api.MenuController;
import it.unibo.oop.relario.model.map.Room;
import it.unibo.oop.relario.model.map.RoomGenerator;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;
import it.unibo.oop.relario.view.impl.MainViewImpl;

/**
 * Implementation for the Controller container class.
 */
public final class MainControllerImpl implements MainController {

    private final CombatController combat;
    private final GameController game;
    private final InventoryController inventory;
    private final MenuController mainMenu;
    private final CutSceneController cutScene;
    private final MainView view;
    private RoomGenerator roomGenerator;
    private Optional<Room> curRoom;
    private int roomIndex;

    /**
     * Initializes all the controllers and the main view.
     */
    public MainControllerImpl() {
        this.view = new MainViewImpl(this);
        this.startNewGame();
        this.combat = new CombatControllerImpl(this);
        this.game = new GameControllerImpl(this);
        this.inventory = new InventoryControllerImpl(this);
        this.mainMenu = new MenuControllerImpl(this);
        this.cutScene = new CutSceneControllerImpl(this);
        this.view.panelsSetup();
        this.view.showPanel(GameState.MENU);
    }

    @Override
    public void startNewGame() {
        this.roomGenerator = new RoomGenerator();
        this.roomIndex = 0;
        this.curRoom = Optional.empty();
    }

    @Override
    public MainView getMainView() {
        return this.view;
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
    public MenuController getMenuController() {
        return this.mainMenu;
    }

    @Override
    public CutSceneController getCutSceneController() {
        return this.cutScene;
    }

    @Override
    public Optional<Room> getCurRoom() {
        return this.curRoom;
    }

    @Override
    public void moveToNextRoom() {
        this.roomIndex++;
        this.curRoom = this.roomGenerator.getRoom(roomIndex);
    }
}
