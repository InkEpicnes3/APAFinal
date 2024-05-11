package enemygame.logic;

import enemygame.EnemyGame;
import enemygame.entities.Enemy;
import enemygame.interfaces.GameTick;
import enemygame.util.DoublePoint;

import java.awt.*;

public class EnemySpawner implements GameTick {
    private Dimension screenSize;
    private int offScreenSpawnRange;
    private double spawnCooldownSeconds, timeSinceSpawnSeconds;
    private int maxSpawnedEnemies;

    public EnemySpawner() {
        screenSize = EnemyGame.getWindow().getPreferredSize();
        offScreenSpawnRange = 300;
        spawnCooldownSeconds = 0.5;
        timeSinceSpawnSeconds = 0;
        maxSpawnedEnemies = 30;
    }

    @Override
    public void tick(double frameTime) {
        timeSinceSpawnSeconds += frameTime;
    }

    public void spawnEnemy() {
        if (timeSinceSpawnSeconds < spawnCooldownSeconds || EnemyGame.getEntityManager().getNumEnemies() >= maxSpawnedEnemies)
            return;

        Enemy e = new Enemy(new DoublePoint(), EnemyGame.getEntityManager().getPlayer());
        int minOnScreenXPos = (int) (-e.getSize().getWidth());
        int maxOnScreenXPos = (int) (screenSize.getWidth() + e.getSize().getWidth());

        int enemyX = (int) (Math.random() * (screenSize.getWidth() + offScreenSpawnRange * 2)) - offScreenSpawnRange;
        int enemyY;
        if (enemyX >= minOnScreenXPos && enemyX <= maxOnScreenXPos) {
            enemyY = (int) (Math.random() * offScreenSpawnRange * 2);
            if (enemyY < offScreenSpawnRange)
                enemyY -= offScreenSpawnRange;
            else
                enemyY += (int) screenSize.getHeight();
        }
        else {
            enemyY = (int) (Math.random() * (screenSize.getHeight() + offScreenSpawnRange * 2)) - offScreenSpawnRange;
        }

        e.setPosition(new DoublePoint(enemyX, enemyY));
        EnemyGame.getEntityManager().addEnemy(e);
        timeSinceSpawnSeconds = 0;
    }
}
