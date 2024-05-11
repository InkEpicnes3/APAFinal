package enemygame.entities;

import enemygame.EnemyGame;
import enemygame.graphics.GamePanel;
import enemygame.graphics.sprite.Sprite;
import enemygame.managers.ImageManager;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class Enemy extends Entity {
    private Entity target;
    private Sprite sprite;

    public Enemy(DoublePoint position, Entity target) {
        super(position, new Dimension(50, 50), 200, 100);
        this.damage = 10;
        this.target = target;
        this.sprite = new Sprite(position, size, GamePanel.LAYER_ENEMY, ImageManager.RED_FACE);
    }

    @Override
    public void tick(double frameTime) {
        velocity = new Vector(this.position, target.position);
        velocity.setLength(speed);
        applyVelocity(frameTime);
    }

    public void kill() {
        EnemyGame.getGamePanel().removeDrawable(sprite.getDrawLayer(), sprite);
    }

    public void setPosition(DoublePoint position) {
        super.setPosition(position);
        sprite.setPosition(position);
    }
}
