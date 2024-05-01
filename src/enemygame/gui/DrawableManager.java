package enemygame.gui;

import java.awt.*;
import java.util.ArrayList;

public class DrawableManager {
    private final ArrayList<Drawable> drawables;

    public DrawableManager() {
        drawables = new ArrayList<>();
    }

    public void drawAll(Graphics g) {
        drawables.forEach(d -> d.draw(g));
    }

    public void add(Drawable d) {
        drawables.add(d);
    }

    public void remove(Drawable d) {
        drawables.remove(d);
    }
}
