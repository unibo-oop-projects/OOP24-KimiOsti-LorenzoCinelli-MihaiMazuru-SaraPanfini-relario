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

    private final String name;
    private final int atk;
    private final Inventory inventory;
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
    public MainCharacterImpl(final Position initialPosition) {
        this.name = "Relano";
        this.life = Constants.DEFAULT_PLAYER_LIFE;
        this.atk = Constants.DEFAULT_PLAYER_ATK;
        this.inventory = new InventoryImpl();
        this.position = new PositionImpl(initialPosition.getX(), initialPosition.getY());
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
        moving = true;
        this.direction = direction;
    }

    @Override
    public void stop() {
        moving = false;
    }

    @Override
    public void update() {
        if (moving) {
            this.position = direction.move(position);
        }
    }

    @Override
    public boolean attacked(final int damage) {
        if (damage >= life) {
            life = 0;
            return false;
        } else {
            life = life - damage;
            return true;
        }
    }

    @Override
    public int getAttack() {
        return this.atk;
    }

    @Override
    public List<InventoryItem> getItems() {
        return this.inventory.getItemsList();
    }

    @Override
    public boolean useItem(final InventoryItem item) {
        if (this.inventory.useItem(item)) {
            if (item instanceof EquippableItem) {
                this.equip((EquippableItem) item);
            } else {
                if (item.getEffect() == EffectType.HEALING) {
                    if (this.life + item.getIntensity() >= Constants.DEFAULT_PLAYER_LIFE) {
                        this.life = Constants.DEFAULT_PLAYER_LIFE;
                    } else {
                        this.life += item.getIntensity();
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean discardItem(final InventoryItem item) {
        return this.inventory.dropItem(item);
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
