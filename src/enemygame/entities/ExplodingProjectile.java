package enemygame.entities;

import enemygame.EnemyGame;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.graphics.SpriteAnimation;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class ExplodingProjectile extends Projectile {
    private double flightTime, lifeTime;

    public ExplodingProjectile(DoublePoint position, Vector direction, boolean fromPlayer, SpriteAnimation animation) {
        super(position, new Dimension(36, 36), 150, direction, 0, 1, 0, fromPlayer);
        setSprite(new Sprite(position, size, DrawLayer.PROJECTILES, animation));
        lifeTime = 2.5;
        flightTime = 0;
    }

    public void tick(double frameTime) {
        super.tick(frameTime);
        flightTime += frameTime;
        if (flightTime > lifeTime)
            numHits = Integer.MAX_VALUE;
    }

    public void kill() {
        super.kill();
        if (isVisible())
            EnemyGame.getEntityManager().addEntity(new Explosion(new DoublePoint(position), type == EntityType.PLAYER_PROJECTILE));
    }
}
