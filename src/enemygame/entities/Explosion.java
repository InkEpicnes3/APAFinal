package enemygame.entities;

import enemygame.data.SpriteAnimations;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class Explosion extends Projectile {
    private int maxDamage;

    public Explosion(DoublePoint position, boolean fromPlayer) {
        super(position, new Vector(), fromPlayer);
        setSprite(new Sprite(position, new Dimension(85, 85), DrawLayer.TOP, SpriteAnimations.YELLOW_EXPLOSION));

        this.maxHits = Integer.MAX_VALUE;
        maxDamage = 90;
    }

    public void tick(double frameTime) {
        if (numHits != 0)
            numHits = 0;
        damage = maxDamage * sprite.getAnimationProgress();
        if (sprite.isCurrentAnimationFinished())
            maxHits = -1;
    }
}
