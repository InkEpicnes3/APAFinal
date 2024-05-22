package enemygame.entities;

import enemygame.graphics.Sprite;
import enemygame.util.*;
import enemygame.util.interfaces.GameTick;
import enemygame.EnemyGame;

import java.awt.*;

public abstract class Entity extends GameObject implements GameTick {
    protected Vector velocity;
    protected double movementSpeed;

    protected Sprite sprite;
    protected HealthComponent health;
    protected Hurtbox hurtbox;
    protected Hitbox hitbox;

    protected final EntityType type;

    protected Entity(DoublePoint position, Dimension size, double movementSpeed, double health, double damage, EntityType type) {
        super(position, size);
        this.velocity = new Vector();
        this.movementSpeed = movementSpeed;

        this.health = new HealthComponent(health);
        this.hurtbox = new Hurtbox(position, size);
        this.hitbox = new Hitbox(position, size, damage);

        this.type = type;
    }

    public void kill() {
        sprite.kill();
        EnemyGame.getEntityManager().getEntities(type).remove(this);
    }

    public void applyVelocity(double frameTime) {
        position.translate(velocity.getX() * frameTime, velocity.getY() * frameTime);
    }

    @Override
    public void setPosition(DoublePoint position) {
        super.setPosition(position);
        sprite.setPosition(position);
    }

    @Override
    public void setSize(Dimension size) {
        super.setSize(size);
        sprite.setSize(size);
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public HealthComponent getHealth() {
        return health;
    }

    public void setHealth(HealthComponent health) {
        this.health = health;
    }

    public Hurtbox getHurtbox() {
        return hurtbox;
    }

    public void setHurtbox(Hurtbox hurtbox) {
        this.hurtbox = hurtbox;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public EntityType getType() {
        return type;
    }
}
