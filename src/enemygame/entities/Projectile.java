package enemygame.entities;

import enemygame.data.SpriteAnimations;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class Projectile extends Entity {
    protected double knockbackMultiplier;
    protected int maxHits;
    protected int numHits;

    public Projectile(DoublePoint position, Vector direction, boolean fromPlayer) {
        this(position, new Dimension(30, 30), 600, direction, 20, 1, 5.0, fromPlayer);
        setSprite(new Sprite(position, size, DrawLayer.PROJECTILES, SpriteAnimations.GREEN_PROJECTILE_SPIN));
    }

    protected Projectile(DoublePoint position, Dimension size, double movementSpeed, Vector direction, double damage, int maxHits, double knockbackMultiplier, boolean fromPlayer) {
        super(position, size, movementSpeed, -1, damage, (fromPlayer) ? EntityType.PLAYER_PROJECTILE : EntityType.ENEMY_PROJECTILE);
        direction.setLength(movementSpeed);
        velocity = direction;
        this.maxHits = maxHits;
        this.knockbackMultiplier = knockbackMultiplier;
    }

    @Override
    public void tick(double frameTime) {
        applyVelocity(frameTime);
    }

    public void hit() {
        numHits++;
    }

    public boolean hasReachedHitLimit() {
        return numHits >= maxHits;
    }

    public Vector getKnockback() {
        return new Vector(velocity.getX() * knockbackMultiplier, velocity.getY() * knockbackMultiplier);
    }

    public void setKnockbackMultiplier(double knockbackMultiplier) {
        this.knockbackMultiplier = knockbackMultiplier;
    }
}
