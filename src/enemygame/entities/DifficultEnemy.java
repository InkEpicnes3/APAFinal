package enemygame.entities;

import enemygame.EnemyGame;
import enemygame.data.Images;
import enemygame.data.SpriteAnimations;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.managers.EntityManager;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

public class DifficultEnemy extends Enemy {
    public DifficultEnemy(DoublePoint position, Entity target) {
        super(position, target);
        setSprite(new Sprite(position, size, DrawLayer.ENEMIES, Images.LIGHT_BLUE_FACE));
    }

    public void kill() {
        super.kill();
        EntityManager entityManager = EnemyGame.getEntityManager();
        entityManager.addEntity(new ExplodingProjectile(new DoublePoint(position), new Vector(0, -1), false, SpriteAnimations.RED_ROCKET_UP));
        entityManager.addEntity(new ExplodingProjectile(new DoublePoint(position), new Vector(0, 1), false, SpriteAnimations.RED_ROCKET_DOWN));
        entityManager.addEntity(new ExplodingProjectile(new DoublePoint(position), new Vector(-1, 0), false, SpriteAnimations.RED_ROCKET_LEFT));
        entityManager.addEntity(new ExplodingProjectile(new DoublePoint(position), new Vector(1, 0), false, SpriteAnimations.RED_ROCKET_RIGHT));
        entityManager.addEntity(new ExplodingProjectile(new DoublePoint(position), new Vector(1, -1), false, SpriteAnimations.RED_ROCKET_UP_RIGHT));
        entityManager.addEntity(new ExplodingProjectile(new DoublePoint(position), new Vector(1, 1), false, SpriteAnimations.RED_ROCKET_DOWN_RIGHT));
        entityManager.addEntity(new ExplodingProjectile(new DoublePoint(position), new Vector(-1, -1), false, SpriteAnimations.RED_ROCKET_UP_LEFT));
        entityManager.addEntity(new ExplodingProjectile(new DoublePoint(position), new Vector(-1, 1), false, SpriteAnimations.RED_ROCKET_DOWN_LEFT));
    }
}
