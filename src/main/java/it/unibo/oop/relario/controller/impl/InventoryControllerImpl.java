package it.unibo.oop.relario.controller.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.model.entities.living.MainCharacter;
import it.unibo.oop.relario.model.inventory.EquippableItem;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.model.inventory.InventoryItems;
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
    private MainCharacter player;
    private InventoryView inventoryView;
    private List<InventoryItem> inventory;
    private Optional<EquippableItem> equippedArmor;
    private Optional<EquippableItem> equippedWeapon;
    private GameState nextState;
    private int selectedItem;

    /**
     * Creates a new controller for the inventory of the player.
     * @param controller the main controller of the game.
     */
    public InventoryControllerImpl(final MainController controller) {
        this.mainController = controller;
        this.mainView = this.mainController.getMainView();
        this.selectedItem = 0;
    }

    @Override
    public void init(final GameState prevState) {
        this.inventoryView = (InventoryView) mainView.getPanel(GameState.INVENTORY);
        this.player = mainController.getCurRoom().get().getPlayer();
        if (prevState != GameState.MENU_IN_GAME) {
            this.nextState = prevState;
        }
        updateInventory();
        this.inventoryView.init();
        this.mainView.showPanel(GameState.INVENTORY);
    }

    @Override
    public void notify(final Event event) {
        switch (event) {
            case PREVIOUS_ITEM, NEXT_ITEM -> {
                if (isValidSelection()) {
                    final int direction = (event == Event.PREVIOUS_ITEM) ? -1 : 1;
                    this.selectedItem = (this.selectedItem + direction + this.inventory.size()) % this.inventory.size();
                }
            }
            case USE_ITEM -> {
                if (isValidSelection()) {
                    player.useItem(inventory.get(this.selectedItem));
                }
            }
            case DISCARD_ITEM -> {
                if (isValidSelection()) {
                    player.discardItem(inventory.get(this.selectedItem));
                }
            }
            case INVENTORY -> regress();
            case ESCAPE -> openMenu();
            default -> { }
        }
        this.refresh();
    }

    @Override
    public List<String> getItemsNames() {
        this.updateInventory();
        return this.inventory.stream()
                .flatMap(t -> t.getName().lines())
                .collect(Collectors.toList());
    }

    @Override
    public String getItemFullDescription() {
        if (selectedItem >= 0 && selectedItem < inventory.size()) {
            final InventoryItem item = inventory.get(selectedItem);
            return InventoryItems.getFullDescription(item);
        } else {
            return "";
        }
    }

    @Override
    public String getEquippedArmor() {
        return InventoryItems.getEquippedItem(equippedArmor);
    }

    @Override
    public String getEquippedWeapon() {
        return InventoryItems.getEquippedItem(equippedWeapon);
    }

    @Override
    public int getSelectedItemIndex() {
        return selectedItem;
    }

    @Override
    public String getLife() {
        return String.valueOf(this.player.getLife());
    }

    @Override
    public void setSelectedItemIndex(final int index) {
        if (index >= 0 && index < inventory.size()) {
            this.selectedItem = index;
            this.refresh();
        }
    }

    private boolean isValidSelection() {
        return !this.inventory.isEmpty() && this.selectedItem >= 0 && this.selectedItem < inventory.size();
    }

    private void updateInventory() {
        this.inventory = this.player.getItems();
        this.equippedArmor = this.player.getEquippedArmor();
        this.equippedWeapon = this.player.getEquippedWeapon();
    }

    private void refresh() {
        this.updateInventory();
        if (this.selectedItem >= this.inventory.size()) {
            this.selectedItem = 0;
        }
        this.inventoryView.refresh();
    }

    private void regress() {
        switch (this.nextState) {
            case GAME -> this.mainController.getGameController().run(true);
            case COMBAT -> this.mainController.getCombatController().resumeCombat();
            default -> { }
        }
    }

    private void openMenu() {
        this.mainController.getMenuController().showMenu(GameState.MENU_IN_GAME, GameState.INVENTORY);
    }

}
