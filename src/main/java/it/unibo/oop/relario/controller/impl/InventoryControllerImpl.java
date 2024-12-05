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
 * 
 */
public final class InventoryControllerImpl implements InventoryController {

    private final MainController mainController;
    private final MainView mainView;
    private final MainCharacter player;
    private List<InventoryItem> inventory;

    /**
     * @param mainController
     * @param mainView
     * @param player
     */
    public InventoryControllerImpl(final MainController mainController, final MainView mainView) {
        this.mainController = mainController;
        this.mainView = mainView;
        this.player = mainController.getRoom();
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
        //return Stream.iterate(1, i -> i + 1).map(i -> i+"\n").collect(Collectors.toList());
        List<String> temp = new ArrayList<>();
        for (var item : inventory) {
            temp.add(item.getName());
        }
        return temp;    //usa gli stream
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
        //tutta la roba nel foglio
    }
}
