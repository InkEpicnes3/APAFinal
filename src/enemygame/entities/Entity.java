package enemygame.entities;

import enemygame.gui.Drawable;
import enemygame.logic.Collision;
import enemygame.logic.GameTick;

import java.awt.*;

public abstract class Entity implements GameTick, Collision, Drawable {
    protected Point position;
    protected Dimension size;

    public Entity(Point position, Dimension size, double speed) {
        this.position = position;
        this.size = size;
    }

    public Entity() {
        this(new Point(0, 0), new Dimension(100, 100), 10.0);
    }
}
