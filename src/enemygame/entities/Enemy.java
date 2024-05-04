package enemygame.entities;

import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class Enemy extends Entity {
    private Entity target;

    public Enemy(DoublePoint position, Player target) {
        this.position = position;
        this.size = new Dimension(100, 100);
        this.speed = 5;
        this.target = target;
    }

    @Override
    public void tick() {
        velocity = new Vector(this.position, target.position);
        velocity.setLength(speed);
        move();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight());
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }
}
