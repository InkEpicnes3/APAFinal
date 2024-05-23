package enemygame.entities.enemies;

import enemygame.EnemyGame;
import enemygame.data.Images;
import enemygame.entities.Coin;
import enemygame.entities.Entity;
import enemygame.entities.EntityType;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class Enemy extends Entity {
    protected final Entity target;

    public Enemy(DoublePoint position, Entity target) {
        super(position, new Dimension(50, 50), 100, 100, 20, EntityType.ENEMY);
        setSprite(new Sprite(position, size, DrawLayer.ENEMIES, Images.RED_FACE));
        this.target = target;
    }

    @Override
    public void tick(double frameTime) {
        applyVelocity(frameTime);
        velocity = new Vector(this.position, target.getPosition());
        velocity.setLength(movementSpeed);
    }

    public void kill() {
        super.kill();
        if (Math.random() < 0.35d)
            EnemyGame.getEntityManager().addEntity(new Coin(new DoublePoint(position)));
    }
}
