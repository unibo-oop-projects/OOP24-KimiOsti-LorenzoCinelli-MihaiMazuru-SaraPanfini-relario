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
import it.unibo.oop.relario.view.api.InventoryView;

/**
 * Implementation of {@link InventoryController}.
 */
public final class InventoryControllerImpl implements InventoryController {

    private final MainController mainController;
    private final MainView mainView;
    private final MainCharacter player;
    private InventoryView inventoryView;
    private List<InventoryItem> inventory;
    private Optional<EquippableItem> equippedArmor;
    private Optional<EquippableItem> equippedWeapon;
    private int selectedItem;

    /**
     * Creates a new view for the inventory of the player.
     * @param mainController the main controller of the game.
     * @param mainView the main view of the game.
     */
    public InventoryControllerImpl(final MainController mainController, final MainView mainView) {
        this.mainController = mainController;
        this.mainView = mainView;
        this.selectedItem = 0;
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
        this.inventory = player.getItems();
        this.updateInventory();
        if (this.selectedItem >= this.inventory.size()) {
            this.selectedItem = 0;
        }
        this.inventoryView.refresh();
    }

    private void regress() {
        this.mainController.getGameController().resume(true);
        this.mainView.showPreviousPanel();
    }

    @Override
    public void init() {
        this.inventoryView = (InventoryView) mainView.getPanel(GameState.INVENTORY.getState());
    }

    @Override
    public List<String> getItemsNames() {
        this.updateInventory();
        final List<String> temp = new ArrayList<>();
        for (final var item : inventory) {
            temp.add(item.getName());
        }
        return temp;
    }

    @Override
    public String getItemFullDescription(final int index) {
        if (index >= 0 && index < inventory.size()) {
            final InventoryItem item = inventory.get(index);
            return getFullDescription(item);
        } else {
            return "";
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
        if (!inventory.isEmpty() && this.selectedItem >= 0 && this.selectedItem < inventory.size()) {
            switch (event) {
                case PREVIOUS_ITEM -> this.selectedItem = (this.selectedItem + this.inventory.size() - 1) % this.inventory.size();
                case NEXT_ITEM -> this.selectedItem = (this.selectedItem + 1) % this.inventory.size();
                case USE_ITEM -> player.useItem(inventory.get(this.selectedItem));
                case DISCARD_ITEM -> player.discardItem(inventory.get(this.selectedItem));
                case INVENTORY -> regress();
                default -> { }
            }
            this.refresh();
        }
    }

    @Override
    public int getSelectedItemIndex() {
        return selectedItem;
    }

    @Override
    public void setSelectedItemIndex(final int index) {
        if (index >= 0 && index < inventory.size()) {
            this.selectedItem = index;
            this.refresh();
        }
    }
}
