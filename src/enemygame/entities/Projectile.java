package enemygame.entities;

import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class Projectile extends Entity {
    private int damage;

    public Projectile(DoublePoint position, Vector velocity, int damage) {
        super(position, new Dimension(60, 60), 8);
        velocity.setLength(speed);
        this.velocity = velocity;
        this.damage = damage;
    }

    @Override
    public void tick() {
        move();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight());
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
