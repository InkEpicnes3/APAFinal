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
    protected double damage;

    protected final EntityType type;

    protected Entity(DoublePoint position, Dimension size, double movementSpeed, double health, double damage, EntityType type) {
        super(position, size);
        this.velocity = new Vector();
        this.movementSpeed = movementSpeed;

        this.health = new HealthComponent(health);
        this.damage = damage;

        this.type = type;
    }

    public void kill() {
        sprite.kill();
        EnemyGame.getEntityManager().getEntities(type).remove(this);
    }

    public boolean collidesWith(Entity entity) {
        return getCollisionRect().intersects(entity.getCollisionRect());
    }

    public void applyVelocity(double frameTime) {
        position.translate(velocity.getX() * frameTime, velocity.getY() * frameTime);
    }

    public boolean isVisible() {
        return getCollisionRect().intersects(new Rectangle(new Point(), EnemyGame.getWindowSize()));
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
        if (this.sprite != null)
            this.sprite.kill();
        this.sprite = sprite;
    }

    public HealthComponent getHealth() {
        return health;
    }

    public void setHealth(HealthComponent health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public EntityType getType() {
        return type;
    }
}
