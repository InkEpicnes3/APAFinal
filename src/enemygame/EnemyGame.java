package enemygame;

import enemygame.entities.Player;
import enemygame.logic.GameTickManager;

import javax.swing.*;
import java.awt.*;

public class EnemyGame implements Runnable {
    private static JFrame window;
    private static EnemyGamePanel gamePanel;
    private static GameTickManager tickManager;

    private static Player player;

    private final boolean gameQuit;
    private final Thread thread;

    public EnemyGame() {
        window = new JFrame("Enemy Game");
        window.setSize(new Dimension(1366, 768));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        gamePanel = new EnemyGamePanel(window.getPreferredSize());
        window.add(gamePanel);
        window.pack();

        gameQuit = false;
        thread = new Thread(this);
        tickManager = new GameTickManager();

        player = new Player();
        tickManager.add(player);

        thread.start();
        window.setVisible(true);
    }

    @Override
    public void run() {
        while (!gameQuit) {
            gamePanel.repaint();

            try {
                Thread.sleep(16);
            }
            catch (InterruptedException ignored) {
            }
        }
    }

    public static JFrame getWindow() {
        return window;
    }

    public static EnemyGamePanel getGamePanel() {
        return gamePanel;
    }

    public static Player getPlayer() {
        return player;
    }

    public static GameTickManager getTickManager() {
        return tickManager;
    }

    public static void main(String[] args) {
        new EnemyGame();
    }
}