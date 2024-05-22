package enemygame.util;

import enemygame.util.interfaces.Collision;

import java.awt.*;

public class Hurtbox extends GameObject implements Collision {
    private HealthComponent health;

    public Hurtbox(DoublePoint position, Dimension size) {
        super(position, size);
    }

    public void damage(double amount) {
        health.damage(amount);
    }
}
