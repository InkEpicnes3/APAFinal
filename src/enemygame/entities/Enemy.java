package enemygame.entities;

import enemygame.EnemyGame;
import enemygame.gui.Drawable;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;

public class Enemy extends Entity implements Drawable {
    private Entity target;

    public Enemy(DoublePoint position, Player target) {
        super(position, new Dimension(50, 50), 4);
        this.target = target;
        EnemyGame.getGamePanel().getDrawableManager().add(this);
    }

    @Override
    public void tick() {
        velocity = new Vector(this.position, target.position);
        velocity.setLength(speed);
        move();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight());
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }
}
