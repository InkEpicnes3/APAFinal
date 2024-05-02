package enemygame.logic;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
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
        double totalLength = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        x /= totalLength;
        y /= totalLength;
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
