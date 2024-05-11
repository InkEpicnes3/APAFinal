package enemygame.entities;

import enemygame.EnemyGame;
import enemygame.graphics.GamePanel;
import enemygame.graphics.sprite.AnimatedSprite;
import enemygame.graphics.sprite.SpriteAnimation;
import enemygame.managers.ImageManager;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class Projectile extends Entity {
    private final AnimatedSprite sprite;
    private boolean hasCollided;

    public Projectile(DoublePoint position, Vector direction, int damage) {
        super(position, new Dimension(30, 30), 600, -1);
        direction.setLength(speed);
        this.velocity = direction;
        this.damage = damage;
        sprite = new AnimatedSprite(position, size, GamePanel.LAYER_PROJECTILE,
                new SpriteAnimation("BlueProjectileSpin", 6, true,
                        ImageManager.PROJECTILE_BLUE_SPIN_1,
                        ImageManager.PROJECTILE_BLUE_SPIN_4,
                        ImageManager.PROJECTILE_BLUE_SPIN_2,
                        ImageManager.PROJECTILE_BLUE_SPIN_3));
        hasCollided = false;
    }

    @Override
    public void tick(double frameTime) {
        applyVelocity(frameTime);
    }

    public void kill() {
        EnemyGame.getSpriteManager().removeAnimatedSprite(sprite);
    }

    public AnimatedSprite getSprite() {
        return sprite;
    }

    public void markCollided() {
        hasCollided = true;
    }

    public boolean hasCollided() {
        return hasCollided;
    }
}
