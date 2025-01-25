package it.unibo.oop.relario.model.inventory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.oop.relario.utils.impl.Constants;

/**
 * Implementation of the player's inventory.
 */
public final class InventoryImpl implements Inventory {

    private final int initialLife;
    private final int baseAtk;
    private final List<InventoryItem> items;
    private int life;
    private Optional<EquippableItem> armor;
    private Optional<EquippableItem> weapon;


    /**
     * Instantiates a new inventory.
     */
    public InventoryImpl() {
        this.initialLife = Constants.PLAYER_LIFE;
        this.life = this.initialLife;
        this.baseAtk = Constants.PLAYER_ATK;
        this.items = new LinkedList<>();
        this.armor = Optional.empty();
        this.weapon = Optional.empty();
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public int attack() {
        return this.weapon.isPresent() ? this.baseAtk + this.weapon.get().getIntensity() : this.baseAtk;
    }

    @Override
    public void attacked(final int damage) {
        final var resistance = this.life + (this.armor.isPresent() ? this.armor.get().getIntensity() : 0);
        this.life = resistance > damage ? resistance - damage : 0;
        armor.ifPresent(EquippableItem::decreaseDurability);
    }

    @Override
    public List<InventoryItem> getItemsList() {
        return List.copyOf(this.items);
    }

    @Override
    public Optional<EquippableItem> getEquippedArmor() {
        return this.getIfEquipped(armor);
    }

    @Override
    public Optional<EquippableItem> getEquippedWeapon() {
        return this.getIfEquipped(weapon);
    }

    @Override
    public boolean useItem(final InventoryItem item) {
        if (this.items.remove(item)) {
            switch (item.getEffect()) {
                case DAMAGE -> this.equipWeapon(item);
                case PROTECTION -> this.equipArmor(item);
                case HEALING -> this.healPlayer(item.getIntensity());
                default -> { }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean discardItem(final InventoryItem item) {
        return this.items.remove(item);
    }

    @Override
    public boolean addItem(final InventoryItem item) {
        return this.items.size() < Constants.PLAYER_INVENTORY_CAPACITY && this.items.add(item);
    }

    private Optional<EquippableItem> getIfEquipped(final Optional<EquippableItem> item) {
        return item.isPresent() ? Optional.of(item.get()) : Optional.empty();
    }

    private void equipWeapon(final InventoryItem weapon) {
        if (weapon instanceof EquippableItem) {
            this.weapon.ifPresent(e -> this.addItem(e));
            this.weapon = Optional.of((EquippableItem) weapon);
        }
    }

    private void equipArmor(final InventoryItem armor) {
        if (armor instanceof EquippableItem) {
            this.armor.ifPresent(e -> this.addItem(e));
            this.armor = Optional.of((EquippableItem) armor);
        }
    }

    private void healPlayer(final int intensity) {
        this.life = this.life + intensity > this.initialLife
        ? this.initialLife
        : this.life + intensity;
    }

}
