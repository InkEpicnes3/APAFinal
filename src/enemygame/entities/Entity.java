package enemygame.entities;

import enemygame.EnemyGame;
import enemygame.gui.Drawable;
import enemygame.logic.Collision;
import enemygame.logic.GameTick;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public abstract class Entity implements GameTick, Collision {
    protected DoublePoint position;
    protected Dimension size;
    protected Vector velocity;
    protected double speed;
    protected int health;

    public Entity(DoublePoint position, Dimension size, double speed) {
        this.position = position;
        this.size = size;
        this.speed = speed;
        this.velocity = new Vector();
        this.health = 100;

        //EnemyGame.getGamePanel().getDrawableManager().add(this);
    }

    public Entity() {
        this(new DoublePoint(0, 0), new Dimension(100, 100), 10.0);
    }

    public void move() {
        position.translate(velocity.getX(), velocity.getY());
    }

    @Override
    public Rectangle getCollision() {
        return new Rectangle(position.asAWTPoint(), size);
    }

    public double getSpeed() {
        return speed;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Dimension getSize() {
        return size;
    }

    public DoublePoint getPosition() {
        return position;
    }

    public void setPosition(DoublePoint position) {
        this.position = position;
    }

    public int getHealth() {
        return health;
    }

    public void damage(int amount) {
        this.health -= amount;
    }
}
