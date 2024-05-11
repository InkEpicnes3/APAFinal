package enemygame;

import enemygame.entities.Player;
import enemygame.graphics.GamePanel;
import enemygame.logic.EnemySpawner;
import enemygame.managers.AnimatedSpriteManager;
import enemygame.managers.EntityManager;
import enemygame.managers.InputManager;
import enemygame.util.DoublePoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class EnemyGame implements Runnable {
    private static JFrame window;
    private static GamePanel gamePanel;
    private static Thread gameThread;

    private static InputManager input;
    private static EntityManager entityManager;
    private static AnimatedSpriteManager spriteManager;
    private static EnemySpawner spawner;

    public EnemyGame() {
        window = new JFrame("Enemy Game");
        window.setPreferredSize(new Dimension(1366, 768));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setResizable(false);
        // https://stackoverflow.com/questions/1984071/how-to-hide-cursor-in-a-swing-application
        window.setCursor(window.getToolkit()
                .createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));

        gamePanel = new GamePanel(window.getPreferredSize());
        window.add(gamePanel);
        window.pack();

        input = new InputManager(window);
        entityManager = new EntityManager();
        entityManager.setPlayer(new Player(new DoublePoint(500, 500)));
        spriteManager = new AnimatedSpriteManager();
        spawner = new EnemySpawner();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2
        // Construct the second game loop (delta)
        int targetFPS = 60;
        long loopStartTime, nextLoopTime;
        double frameTime = 0;

        while (!input.isKeyPressed(KeyEvent.VK_F8)) {
            loopStartTime = System.nanoTime();
            nextLoopTime = loopStartTime + (int) (1d / targetFPS * 1_000_000_000);

            window.repaint();
            entityManager.tick(frameTime);
            spriteManager.tick(frameTime);
            spawner.tick(frameTime);
            spawner.spawnEnemy();

            long remainingTimeMs = (nextLoopTime - System.nanoTime()) / 1_000_000;
            if (remainingTimeMs < 0)
                remainingTimeMs = 0;
            frameTime = remainingTimeMs / 1000d;

            try {
                Thread.sleep(remainingTimeMs);
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

    public static GamePanel getGamePanel() {
        return gamePanel;
    }

    public static InputManager getInput() {
        return input;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static AnimatedSpriteManager getSpriteManager() {
        return spriteManager;
    }

    public static void main(String[] args) {
        new EnemyGame();
    }
}
