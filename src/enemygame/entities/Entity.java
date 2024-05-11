package enemygame.entities;

import enemygame.EnemyGame;
import enemygame.graphics.sprite.Sprite;
import enemygame.interfaces.Collision;
import enemygame.interfaces.GameTick;
import enemygame.util.DoublePoint;
import enemygame.util.HealthComponent;
import enemygame.util.Vector;

import java.awt.*;

public abstract class Entity implements GameTick, Collision {
    protected DoublePoint position;
    protected Dimension size;
    protected Vector velocity;
    protected int speed;
    protected HealthComponent health;
    protected int damage;
    protected boolean isAlive;

    public Entity(DoublePoint position, Dimension size, int speed, int maxHealth) {
        this.position = position;
        this.size = size;
        this.velocity = new Vector();
        this.speed = speed;
        this.health = new HealthComponent(maxHealth);
        this.damage = 0;
        this.isAlive = false;
    }

    abstract void kill();

    public void applyVelocity(double frameTime) {
        position.translate(velocity.getX() * frameTime, velocity.getY() * frameTime);
    }

    public boolean isVisible() {
        Dimension screenSize = EnemyGame.getGamePanel().getSize();
        return (position.getX() + size.getWidth() > 0 && position.getX() < screenSize.getWidth()) &&
                (position.getY() + size.getHeight() > 0 && position.getY() < screenSize.getHeight());
    }

    public DoublePoint getPosition() {
        return position;
    }

    public void setPosition(DoublePoint position) {
        this.position = position;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public HealthComponent getHealthComponent() {
        return health;
    }

    @Override
    public Rectangle getCollision() {
        return new Rectangle(position.asAWTPoint(), size);
    }
}
