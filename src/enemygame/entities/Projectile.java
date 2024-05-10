package enemygame.entities;

import enemygame.gui.sprite.AnimatedSprite;
import enemygame.gui.sprite.AnimatedSpriteFrames;
import enemygame.util.DoublePoint;
import enemygame.util.ImageLoader;
import enemygame.util.Vector;

import java.awt.*;

public class Projectile extends Entity {
    private int damage;
    private final AnimatedSprite sprite;

    public Projectile(DoublePoint position, Vector velocity, int damage) {
        super(position, new Dimension(30, 30), 8);
        velocity.setLength(speed);
        this.velocity = velocity;
        this.damage = damage;
        sprite = new AnimatedSprite(position, size,
                new AnimatedSpriteFrames("RedProjectileSpin", 6, false,
                        ImageLoader.load("projectile_red_spin_1.png"),
                        ImageLoader.load("projectile_red_spin_4.png"),
                        ImageLoader.load("projectile_red_spin_2.png"),
                        ImageLoader.load("projectile_red_spin_3.png")),
                new AnimatedSpriteFrames("GreenProjectileSpin", 6, false,
                        ImageLoader.load("projectile_green_spin_1.png"),
                        ImageLoader.load("projectile_green_spin_4.png"),
                        ImageLoader.load("projectile_green_spin_2.png"),
                        ImageLoader.load("projectile_green_spin_3.png")));
    }

    @Override
    public void tick() {
        move();
        if (sprite.isDone()) {
            if (sprite.getCurrentAnimationName().equals("RedProjectileSpin"))
                sprite.changeAnimTo("GreenProjectileSpin");
            else
                sprite.changeAnimTo("RedProjectileSpin");
        }
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
