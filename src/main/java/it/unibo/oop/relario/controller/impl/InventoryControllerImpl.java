package it.unibo.oop.relario.controller.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.EffectType;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Implementation of the inventory controller.
 */
public final class InventoryControllerImpl implements InventoryController {

    private final MainView mainView;
    private final MainCharacter player;
    private List<InventoryItem> inventory;

    /**
     * Creates a new view for the inventory of the player.
     * @param mainController the main controller of the game.
     * @param mainView the main view of the game.
     */
    public InventoryControllerImpl(final MainController mainController, final MainView mainView) {
        this.mainView = mainView;
        this.player = mainController.getCurrentRoom().getPlayer();
        this.inventory = player.getItems();
    }

    private String getIntensity(InventoryItem item) {
        if (item.getEffect() == EffectType.NONE) {
            return "";
        } else {
            return " " + item.getIntensity();
        }
    }

    @Override
    public List<String> getItemsNames() {
        List<String> temp = new ArrayList<>();
        for (var item : inventory) {
            temp.add(item.getName());
        }
        return temp;
    }

    @Override
    public String getItemFullDescription(final int index) {
        final InventoryItem item = inventory.get(index);
        return item.getDescription()
        + ",\nEffect: " + item.getEffect().toString()
        + this.getIntensity(item);
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
        mainView.showPreviousPanel();
    }
}
