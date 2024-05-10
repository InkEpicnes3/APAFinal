package enemygame;

import enemygame.gui.DrawableManager;
import enemygame.util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyGamePanel extends JPanel {
    private final DrawableManager drawables;
    private final Color backgroundColor;
    private final BufferedImage cursorImage;

    public EnemyGamePanel(Dimension size) {
        super();
        setPreferredSize(size);
        drawables = new DrawableManager();
        backgroundColor = new Color(0x0B0B0B);
        cursorImage = ImageLoader.load("crosshair.png");
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawables.drawAll(g);
        g.drawImage(cursorImage,
                (int) EnemyGame.getInput().getMousePosition().getX() - 30,
                (int) EnemyGame.getInput().getMousePosition().getY() - 30,
                60, 60, null);

        Toolkit.getDefaultToolkit().sync();
    }

    public DrawableManager getDrawableManager() {
        return drawables;
    }
}
