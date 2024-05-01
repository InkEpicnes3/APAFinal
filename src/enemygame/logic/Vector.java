package enemygame.logic;

public class Vector {
    private double x, y;

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
}
