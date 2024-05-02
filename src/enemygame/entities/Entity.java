package enemygame.entities;

import enemygame.gui.Drawable;
import enemygame.logic.Collision;
import enemygame.logic.GameTick;
import enemygame.logic.Vector;

import java.awt.*;

public abstract class Entity implements GameTick, Collision, Drawable {
    protected Point position;
    protected Dimension size;
    protected Vector velocity;
    protected double speed;

    public Entity(Point position, Dimension size, double speed) {
        this.position = position;
        this.size = size;
        this.speed = speed;
        this.velocity = new Vector();
    }

    public Entity() {
        this(new Point(0, 0), new Dimension(100, 100), 10.0);
    }

    protected void move() {
        position.translate((int) velocity.getX(), (int) velocity.getY());
    }
}
