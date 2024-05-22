package enemygame.util;

import enemygame.util.interfaces.Collision;

import java.awt.*;

public class GameObject implements Collision {
    protected DoublePoint position;
    protected Dimension size;

    public GameObject(DoublePoint position, Dimension size) {
        this.position = position;
        this.size = size;
    }

    @Override
    public Rectangle getCollision() {
        return new Rectangle(position.asAWTPoint(), size);
    }

    public DoublePoint getPosition() {
        return position;
    }

    public void setPosition(DoublePoint position) {
        this.position = position;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }
}
