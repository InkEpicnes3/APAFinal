package enemygame.graphics;

import enemygame.EnemyGame;
import enemygame.managers.DrawableManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final DrawableManager drawableManager = new DrawableManager();

    private static final Color backgroundColor = new Color(11, 11, 11);
    private static final Font scoreFont = new Font(Font.SANS_SERIF, Font.BOLD, 60);

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawableManager.draw(g);

        g.setColor(Color.WHITE);
        g.setFont(scoreFont);
        g.drawString("Score: " + EnemyGame.getGameScore(), getWidth() / 2 - 180, 70);

        Toolkit.getDefaultToolkit().sync();
    }

    public static DrawableManager getDrawableManager() {
        return drawableManager;
    }
}
