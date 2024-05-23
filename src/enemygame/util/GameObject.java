package enemygame.util;

import java.awt.*;

public class GameObject {
    protected DoublePoint position;
    protected Dimension size;

    public GameObject(DoublePoint position, Dimension size) {
        this.position = position;
        this.size = size;
    }

    public Rectangle getCollisionRect() {
        return new Rectangle((int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight());
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
