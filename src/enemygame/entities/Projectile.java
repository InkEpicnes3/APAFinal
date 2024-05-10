package enemygame.entities;

import enemygame.gui.AnimatedSprite;
import enemygame.util.DoublePoint;
import enemygame.util.ImageLoader;
import enemygame.util.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity {
    private int damage;
    private AnimatedSprite sprite;

    public Projectile(DoublePoint position, Vector velocity, int damage) {
        super(position, new Dimension(30, 30), 8);
        velocity.setLength(speed);
        this.velocity = velocity;
        this.damage = damage;
        sprite = new AnimatedSprite(position, size, new BufferedImage[] {
                ImageLoader.load("projectile_red_spin_1.png"),
                ImageLoader.load("projectile_red_spin_4.png"),
                ImageLoader.load("projectile_red_spin_2.png"),
                ImageLoader.load("projectile_red_spin_3.png")
        }, 5);
    }

    @Override
    public void tick() {
        move();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public AnimatedSprite getSprite() {
        return sprite;
    }
}
