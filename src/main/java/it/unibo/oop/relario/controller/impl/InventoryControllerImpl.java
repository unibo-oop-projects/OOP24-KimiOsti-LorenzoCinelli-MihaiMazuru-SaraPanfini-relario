package it.unibo.oop.relario.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.EffectType;
import it.unibo.oop.relario.model.inventory.EquippableItem;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.impl.Event;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;
import it.unibo.oop.relario.view.impl.InventoryView;

/**
 * Implementation of the inventory controller.
 */
public final class InventoryControllerImpl implements InventoryController {

    private final MainController mainController;
    private final MainView mainView;
    private final InventoryView inventoryView;
    private final MainCharacter player;
    private List<InventoryItem> inventory;
    private Optional<EquippableItem> equippedArmor;
    private Optional<EquippableItem> equippedWeapon;

    /**
     * Creates a new view for the inventory of the player.
     * @param mainController the main controller of the game.
     * @param mainView the main view of the game.
     */
    public InventoryControllerImpl(final MainController mainController, final MainView mainView) {
        this.mainController = mainController;
        this.mainView = mainView;
        this.inventoryView = (InventoryView) mainView.getPanel(GameState.INVENTORY.getState());
        if (mainController.getCurRoom().isPresent()) {
            this.player = mainController.getCurRoom().get().getPlayer();
            updateInventory();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private void updateInventory() {
        this.inventory = player.getItems();
        this.equippedArmor = player.getEquippedArmor();
        this.equippedWeapon = player.getEquippedWeapon();
    }

    private String getFullDescription(final InventoryItem item) {
        return item.getDescription()
        + ",\nEffetto: " + item.getEffect().toString()
        + this.getIntensity(item);
    }

    private String getIntensity(final InventoryItem item) {
        if (item.getEffect() == EffectType.NONE) {
            return "";
        } else {
            return " " + item.getIntensity();
        }
    }

    private String getEquippedItem(final Optional<EquippableItem> item) {
        if (item.isPresent()) {
            final var equippedItem = item.get();
            return equippedItem.getName() + "\n" + getFullDescription(equippedItem)
            + "\nDurabilità: " + equippedItem.getDurability();
        } else {
            return "";
        }
    }

    private void refresh() {
        inventory = player.getItems();
        updateInventory();
        inventoryView.refresh();
    }

    private void regress() {
        this.mainController.getGameController().resume(true);
        this.mainView.showPreviousPanel();
    }

    @Override
    public List<String> getItemsNames() {
        updateInventory();
        final List<String> temp = new ArrayList<>();
        for (final var item : inventory) {
            temp.add(item.getName());
        }
        return temp;
    }

    @Override
    public String getItemFullDescription(final int index) {
        if (index <= inventory.size()) {
            final InventoryItem item = inventory.get(index);
            return getFullDescription(item);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String getEquippedArmor() {
        return getEquippedItem(equippedArmor);
    }

    @Override
    public String getEquippedWeapon() {
        return getEquippedItem(equippedWeapon);
    }

    @Override
    public void notify(final Event event) {
        switch (event) {
            case USE_ITEM -> {
                player.useItem(inventory.get(0));
                refresh();
            }
            case DISCARD_ITEM -> {
                player.discardItem(inventory.get(0));
                refresh();
            }
            case INVENTORY -> regress();
            default -> { }
        }
    }
}
