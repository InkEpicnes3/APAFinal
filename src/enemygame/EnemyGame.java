package enemygame;

import enemygame.entities.Enemy;
import enemygame.entities.Player;
import enemygame.entities.Projectile;
import enemygame.logic.EnemyManager;
import enemygame.logic.InputManager;
import enemygame.logic.ProjectileManager;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class EnemyGame implements Runnable {
    private static JFrame window;
    private static EnemyGamePanel gamePanel;

    private static InputManager input;
    private static EnemyManager enemyManager;
    private static ProjectileManager projectileManager;

    private static Player player;
    private final Thread gameThread;

    public EnemyGame() {
        window = new JFrame("Enemy Game");
        window.setPreferredSize(new Dimension(1366, 768));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // https://stackoverflow.com/questions/1984071/how-to-hide-cursor-in-a-swing-application
        window.setCursor(window.getToolkit().createCustomCursor(new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));

        gamePanel = new EnemyGamePanel(window.getPreferredSize());
        window.add(gamePanel);
        window.pack();

        gameThread = new Thread(this);
        input = new InputManager(window);

        enemyManager = new EnemyManager(window.getPreferredSize(), 300);

        projectileManager = new ProjectileManager();

        player = new Player(new DoublePoint(500, 500));

        gameThread.start();
        window.setVisible(true);
    }

    @Override
    public void run() {
        while (!input.isKeyPressed(KeyEvent.VK_F8)) {
            enemyManager.trySpawnEnemy();
            enemyManager.tick();
            projectileManager.tick();
            player.tick();
            gamePanel.repaint();

            for (Enemy e : enemyManager.getEnemies())
                for (Projectile p : projectileManager.getProjectiles())
                    if (p.collidesWith(e)) {
                        e.getVelocity().add(new Vector(p.getVelocity().getX() * 5, p.getVelocity().getY() * 5));
                        e.move();
                        e.damage(p.getDamage());
                        projectileManager.queueForRemoval(p);
                    }

            try {
                gameThread.sleep(16);
            }
            catch (InterruptedException ignored) {
            }
        }
        // https://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    public static JFrame getWindow() {
        return window;
    }

    public static EnemyGamePanel getGamePanel() {
        return gamePanel;
    }

    public static InputManager getInput() {
        return input;
    }

    public static Player getPlayer() {
        return player;
    }

    public static EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public static ProjectileManager getProjectileManager() {
        return projectileManager;
    }

    public static void main(String[] args) {
        new EnemyGame();
    }
}