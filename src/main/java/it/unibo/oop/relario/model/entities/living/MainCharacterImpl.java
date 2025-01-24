package it.unibo.oop.relario.model.entities.living;

import java.util.List;
import java.util.Optional;

import it.unibo.oop.relario.model.inventory.EquippableItem;
import it.unibo.oop.relario.model.inventory.Inventory;
import it.unibo.oop.relario.model.inventory.InventoryImpl;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Direction;

/**
 * Implementation for the main character.
 */
public final class MainCharacterImpl implements MainCharacter {

    private final Inventory inventory;
    private final String name;
    private final EntityMovementHandler movementHandler;

    /**
     * Initialises the main character.
     */
    public MainCharacterImpl() {
        this.name = "Relano";
        this.inventory = new InventoryImpl();
        this.movementHandler = new EntityMovementHandlerImpl();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Optional<Position> getPosition() {
        return this.movementHandler.getPosition();
    }

    @Override
    public void setPosition(final Position position) {
        this.movementHandler.setPosition(position);
    }

    @Override
    public Direction getDirection() {
        return this.movementHandler.getDirection();
    }

    @Override
    public void setMovement(final Direction direction) {
        this.movementHandler.setMovement(direction);
    }

    @Override
    public void stop() {
        this.movementHandler.stop();
    }

    @Override
    public void update() {
        this.movementHandler.move();
    }

    @Override
    public void attacked(final int damage) {
        this.inventory.attacked(damage);
    }

    @Override
    public int getLife() {
        return this.inventory.getLife();
    }

    @Override
    public int attack() {
        return this.inventory.attack();
    }

    @Override
    public List<InventoryItem> getItems() {
        return this.inventory.getItemsList();
    }

    @Override
    public Optional<EquippableItem> getEquippedWeapon() {
        return this.inventory.getEquippedWeapon();
    }

    @Override
    public Optional<EquippableItem> getEquippedArmor() {
        return this.inventory.getEquippedArmor();
    }

    @Override
    public boolean useItem(final InventoryItem item) {
        return this.inventory.useItem(item);
    }

    @Override
    public boolean discardItem(final InventoryItem item) {
        return this.inventory.discardItem(item);
    }

    @Override
    public boolean addToInventory(final InventoryItem item) {
        return this.inventory.addItem(item);
    }
}
