package enemygame.entities.enemies;

import enemygame.EnemyGame;
import enemygame.data.Images;
import enemygame.entities.Entity;
import enemygame.entities.projectiles.Bubble;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.logic.EntityManager;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

public class BubbleEnemy extends Enemy {
    public BubbleEnemy(DoublePoint position, Entity target) {
        super(position, target);
        setSprite(new Sprite(position, size, DrawLayer.ENEMIES, Images.GREEN_FACE));
    }

    public void kill() {
        super.kill();
        EntityManager manager = EnemyGame.getEntityManager();
        manager.addEntity(new Bubble(new DoublePoint(position), new Vector(0, -1), target.getPosition(), false));
        manager.addEntity(new Bubble(new DoublePoint(position), new Vector(1, 1), target.getPosition(), false));
        manager.addEntity(new Bubble(new DoublePoint(position), new Vector(-1, 0), target.getPosition(), false));
        manager.addEntity(new Bubble(new DoublePoint(position), new Vector(1, 0), target.getPosition(), false));
        manager.addEntity(new Bubble(new DoublePoint(position), new Vector(1, -1), target.getPosition(), false));
        manager.addEntity(new Bubble(new DoublePoint(position), new Vector(1, 1), target.getPosition(), false));
        manager.addEntity(new Bubble(new DoublePoint(position), new Vector(-1, -1), target.getPosition(), false));
        manager.addEntity(new Bubble(new DoublePoint(position), new Vector(-1, 1), target.getPosition(), false));
    }
}
