package it.unibo.oop.relario.model.entities.living;

import java.util.List;
import java.util.Optional;

import it.unibo.oop.relario.model.inventory.EffectType;
import it.unibo.oop.relario.model.inventory.EquippableItem;
import it.unibo.oop.relario.model.inventory.Inventory;
import it.unibo.oop.relario.model.inventory.InventoryImpl;
import it.unibo.oop.relario.model.inventory.InventoryItem;
import it.unibo.oop.relario.utils.api.Position;
import it.unibo.oop.relario.utils.impl.Constants;
import it.unibo.oop.relario.utils.impl.Direction;
import it.unibo.oop.relario.utils.impl.PositionImpl;

/**
 * Implementation for the main character.
 */
public final class MainCharacterImpl implements MainCharacter {

    private static final int DEFAULT_INITIAL_POSITION = 0;

    private final Inventory inventory;
    private final String name;
    private final int initialLife;
    private final int atk;
    private int life;
    private Position position;
    private boolean moving;
    private Direction direction;
    private Optional<EquippableItem> armor;
    private Optional<EquippableItem> weapon;

    /**
     * Initialises the main character.
     * @param initialPosition the initial player position.
     */
    public MainCharacterImpl() {
        this.name = "Relano";
        this.initialLife = Constants.DEFAULT_PLAYER_LIFE;
        this.life = this.initialLife;
        this.atk = Constants.DEFAULT_PLAYER_ATK;
        this.inventory = new InventoryImpl();
        this.position = new PositionImpl(DEFAULT_INITIAL_POSITION, DEFAULT_INITIAL_POSITION);
        this.armor = Optional.empty();
        this.weapon = Optional.empty();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Position getPosition() {
        return new PositionImpl(this.position.getX(), this.position.getY());
    }

    @Override
    public void setPosition(final Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public boolean isMoving() {
        return this.moving;
    }

    @Override
    public void setMovement(final Direction direction) {
        this.moving = true;
        this.direction = direction;
    }

    @Override
    public void stop() {
        this.moving = false;
    }

    @Override
    public void update() {
        if (this.moving) {
            this.position = direction.move(position);
        }
    }

    @Override
    public boolean attacked(final int damage) {
        this.life = this.life + (this.armor.isEmpty() ? 0 : this.armor.get().getIntensity()) >= damage
            ? this.life - damage + (this.armor.isEmpty() ? 0 : this.armor.get().getIntensity()) : 0;
        if (!this.armor.isEmpty()) {
            this.armor.get().decreaseDurability();
        }
        return this.life > 0;
    }

    @Override
    public int getAttack() {
        return this.weapon.isEmpty() ? this.atk : this.weapon.get().getIntensity() + this.atk;
    }

    @Override
    public List<InventoryItem> getItems() {
        return this.inventory.getItemsList();
    }

    @Override
    public boolean useItem(final InventoryItem item) {
        if (this.inventory.removeItem(item)) {
            if (item instanceof EquippableItem) {
                this.equip((EquippableItem) item);
            } else {
                if (item.getEffect() == EffectType.HEALING) {
                    this.life = 
                        item.getIntensity() + this.life >= this.initialLife 
                        ? this.initialLife
                        : this.life + item.getIntensity();
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean discardItem(final InventoryItem item) {
        return this.inventory.removeItem(item);
    }

    @Override
    public boolean addToInventory(final InventoryItem item) {
        return this.inventory.addItem(item);
    }

    private void equip(final EquippableItem item) {
        if (item.getEffect() == EffectType.DAMAGE) {
            if (!weapon.isEmpty()) {
                inventory.addItem(weapon.get());
            }
            weapon = Optional.of(item);
        } else {
            if (!armor.isEmpty()) {
                inventory.addItem(armor.get());
            }
            armor = Optional.of(item);
        }
    }
}
