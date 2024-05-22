package enemygame.entities;

import enemygame.data.Images;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class Enemy extends Entity {
    private final Entity target;

    public Enemy(DoublePoint position, Entity target) {
        super(position, new Dimension(50, 50), 200, 100, 20, EntityType.ENEMY);
        setSprite(new Sprite(position, size, DrawLayer.ENEMIES, Images.RED_FACE));
        this.target = target;
    }

    @Override
    public void tick(double frameTime) {
        applyVelocity(frameTime);
        velocity = new Vector(this.position, target.getPosition());
        velocity.setLength(movementSpeed);
    }
}
