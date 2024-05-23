package enemygame.entities.projectiles;

import enemygame.data.Images;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class Bubble extends Projectile {
    private final double timeToTargeting;
    private double timeSinceSpawn;
    private boolean isTargeting;
    private final DoublePoint targetPos;

    public Bubble(DoublePoint position, Vector direction, DoublePoint targetPos, boolean fromPlayer) {
        super(position, new Dimension(33, 33), 200, direction, 15, 1, 0.7, fromPlayer);
        this.targetPos = targetPos;
        timeToTargeting = 1;
        timeSinceSpawn = 0;
        isTargeting = false;
        setSprite(new Sprite(position, size, DrawLayer.PROJECTILES, Images.GREEN_BUBBLE_DEACTIVATED));
    }

    @Override
    public void tick(double frameTime) {
        timeSinceSpawn += frameTime;
        if (timeSinceSpawn < timeToTargeting) {
            movementSpeed -= 4;
            velocity.setLength(movementSpeed);
        } else if (!isTargeting) {
            velocity = new Vector(position, targetPos);
            velocity.setLength(1500);
            setSprite(new Sprite(position, size, DrawLayer.PROJECTILES, Images.GREEN_BUBBLE_TARGETING));
            isTargeting = true;
        }
        applyVelocity(frameTime);
    }
}
