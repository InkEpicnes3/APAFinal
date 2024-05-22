package enemygame;

import enemygame.entities.Player;
import enemygame.graphics.GamePanel;
import enemygame.logic.EnemySpawner;
import enemygame.managers.EntityManager;
import enemygame.managers.InputManager;
import enemygame.managers.SpriteManager;
import enemygame.util.DoublePoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class EnemyGame implements Runnable {
    private static JFrame window;
    private static GamePanel panel;
    private static Thread gameThread;
    private static int gameScore = 0;
    public static final String resFolder = System.getProperty("user.dir") + "/res/";

    private static InputManager input;
    private static SpriteManager spriteManager;

    private static EntityManager entityManager;
    private static EnemySpawner enemySpawner;

    public EnemyGame() {
        window = new JFrame("Enemy Game");
        window.setPreferredSize(new Dimension(1366, 768));
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);

        panel = new GamePanel();
        panel.setPreferredSize(window.getSize());
        window.add(panel);
        window.pack();

        gameThread = new Thread(this);
        input = new InputManager(window);
        spriteManager = new SpriteManager();

        entityManager = new EntityManager();
        entityManager.addEntity(new Player(new DoublePoint(500, 500), input));
        enemySpawner = new EnemySpawner(0.5, 255, entityManager);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gameThread.start();
    }

    public static void main(String[] args) {
        new EnemyGame();
    }

    @Override
    public void run() {
        // https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2
        // Construct the second game loop (delta)
        // Makes the game run smoother & at a constant speed with different framerates
        double drawInterval = 1_000_000_000d / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (!input.isKeyPressed(KeyEvent.VK_F8)) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                double frameTime = drawInterval / 1_000_000_000;

                spriteManager.tick(frameTime);
                enemySpawner.tick(frameTime);
                enemySpawner.trySpawnEnemy();
                entityManager.tick(frameTime);
                window.repaint();

                delta--;
            }

            if (timer >= 1_000_000_000)
                timer = 0;
        }
        // https://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    public static void incrementGameScore() {
        gameScore++;
    }

    public static int getGameScore() {
        return gameScore;
    }

    public static SpriteManager getSpriteManager() {
        return spriteManager;
    }

    public static Dimension getWindowSize() {
        return window.getPreferredSize();
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
