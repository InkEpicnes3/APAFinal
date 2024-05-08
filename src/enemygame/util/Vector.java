package enemygame.util;

public class Vector {
    private double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(DoublePoint p1, DoublePoint p2) {
        this.x = p2.getX() - p1.getX();
        this.y = p2.getY() - p1.getY();
    }

    public Vector() {
        this(0.0, 0.0);
    }

    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void setLength(double newLength) {
        normalize();
        x *= newLength;
        y *= newLength;
    }

    public void normalize() {
        double totalLength = Math.hypot(x, y);
        if (x != 0) x /= totalLength;
        if (y != 0) y /= totalLength;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}
