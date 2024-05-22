package enemygame.entities;

import enemygame.data.SpriteAnimations;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class Projectile extends Entity {
    private double knockbackMultiplier;
    private final int maxHits;
    private int numHits;

    public Projectile(DoublePoint position, Vector direction, boolean fromPlayer) {
        super(position, new Dimension(30, 30), 600, -1, 20, (fromPlayer) ? EntityType.PLAYER_PROJECTILE : EntityType.ENEMY_PROJECTILE);
        direction.setLength(movementSpeed);
        velocity = direction;
        setSprite(new Sprite(position, size, DrawLayer.PROJECTILES, SpriteAnimations.BLUE_PROJECTILE_SPIN));
        knockbackMultiplier = 5.0;
        numHits = 0;
        maxHits = 1;
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
