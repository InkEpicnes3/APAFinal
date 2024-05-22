package enemygame.util;

import java.awt.*;

public class Hitbox extends GameObject {
    private double damage;

    public Hitbox(DoublePoint position, Dimension size, double damage) {
        super(position, size);
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
