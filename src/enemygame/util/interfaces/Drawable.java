package enemygame.util.interfaces;

import enemygame.graphics.DrawLayer;

import java.awt.*;

public interface Drawable {
    void draw(Graphics g);

    DrawLayer getLayer();
}
