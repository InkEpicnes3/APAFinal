package enemygame;

import enemygame.gui.DrawableManager;

import javax.swing.*;
import java.awt.*;

public class EnemyGamePanel extends JPanel {
    private final DrawableManager drawables;

    public EnemyGamePanel(Dimension size) {
        super();
        setPreferredSize(size);
        drawables = new DrawableManager();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawables.drawAll(g);

        Toolkit.getDefaultToolkit().sync();
    }

    public DrawableManager getDrawableManager() {
        return drawables;
    }
}
