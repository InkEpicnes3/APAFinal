package enemygame.graphics;

import enemygame.EnemyGame;
import enemygame.managers.DrawableManager;
import enemygame.util.DoublePoint;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final DrawableManager drawableManager = new DrawableManager();
    private static HealthBar playerHealthBar;

    public static final Color backgroundColor = new Color(11, 11, 11);
    private static final Font scoreFont = new Font(Font.SANS_SERIF, Font.BOLD, 60);

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawableManager.draw(g);
        playerHealthBar.draw(g);

        g.setColor(Color.WHITE);
        g.setFont(scoreFont);
        g.drawString("Score: " + EnemyGame.getGameScore(), getWidth() / 2 - 180, 70);

        Toolkit.getDefaultToolkit().sync();
    }

    public static void initPlayerHealthBar() {
        playerHealthBar = new HealthBar(new DoublePoint(EnemyGame.getWindowSize().getWidth() / 2 - 280, EnemyGame.getWindowSize().getHeight() - 100), new Dimension(560, 63), EnemyGame.getEntityManager().getPlayer().getHealth());
    }

    public static DrawableManager getDrawableManager() {
        return drawableManager;
    }
}
