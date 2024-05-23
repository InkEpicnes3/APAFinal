package enemygame.entities.enemies;

import enemygame.EnemyGame;
import enemygame.data.Images;
import enemygame.data.SpriteAnimations;
import enemygame.entities.Entity;
import enemygame.entities.projectiles.ExplodingProjectile;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.logic.EntityManager;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

public class RocketEnemy extends Enemy {
    public RocketEnemy(DoublePoint position, Entity target) {
        super(position, target);
        movementSpeed = 150;
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
