package it.unibo.oop.relario.controller.impl;

import java.util.List;

import it.unibo.oop.relario.controller.api.GameController;
import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.view.impl.MainView;

/**
 * 
 */
public final class InventoryControllerImpl implements InventoryController {

    private final GameController gameController;
    private final MainView mainView;
    private final MainCharacter player;
    private List<InventoryItem> inventory;

    /**
     * @param gameController
     * @param mainView
     * @param player
     */
    public InventoryControllerImpl(final GameController gameController, final MainView mainView, final MainCharacter player) {
        this.gameController = gameController;
        this.mainView = mainView;
        this.player = player;
        this.inventory = player.getItems();
        //mainView.add(new InventoryViewImpl(this, mainView));
    }

    @Override
    public List<String> getItemsNames() {
        return List.of(); //usa gli stream
    }

    @Override
    public String getItemFullDescription(final int index) {
        return "";
    }

    @Override
    public void useItem(final int index) {
        player.useItem(inventory.get(index));
        inventory = player.getItems();
    }

    @Override
    public void discardItem(final int index) {
        player.discardItem(inventory.get(index));
        inventory = player.getItems();
    }

    @Override
    public void regress() {
        //tutta la roba nel foglio
    }

}
