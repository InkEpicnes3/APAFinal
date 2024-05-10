package enemygame.gui;

import enemygame.EnemyGame;

import java.awt.*;

public interface Drawable {
    void draw(Graphics g);

    static void register(Drawable d) {
        EnemyGame.getGamePanel().getDrawableManager().add(d);
    }

    static void deregister(Drawable d) {
        EnemyGame.getGamePanel().getDrawableManager().remove(d);
    }
}
