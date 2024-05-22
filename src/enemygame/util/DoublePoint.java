package enemygame.util;

import java.awt.*;

/**
 * The AWT Point uses ints, which don't have enough precision, so this uses doubles
 */
public class DoublePoint {
    private double x, y;

    public DoublePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public DoublePoint(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    public DoublePoint(DoublePoint p) {
        this(p.x, p.y);
    }

    public DoublePoint() {
        this(0.0, 0.0);
    }

    public void translate(double distX, double distY) {
        x += distX;
        y += distY;
    }

    public Point asAWTPoint() {
        return new Point((int) x, (int) y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
