package enemygame.logic;

import enemygame.graphics.DrawLayer;
import enemygame.util.interfaces.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DrawableManager {
    private final HashMap<DrawLayer, ArrayList<Drawable>> drawables;

    public DrawableManager() {
        drawables = new HashMap<>();
        for (DrawLayer layer : DrawLayer.values())
            drawables.put(layer, new ArrayList<>());
    }

    public void draw(Graphics g) {
        for (ArrayList<Drawable> layer : drawables.values())
            for (Drawable d : layer)
                d.draw(g);
    }

    public void addDrawable(Drawable d) {
        drawables.get(d.getLayer()).add(d);
    }

    public void removeDrawable(Drawable d) {
        drawables.get(d.getLayer()).remove(d);
    }
}
