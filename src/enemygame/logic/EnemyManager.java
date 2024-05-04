package enemygame.logic;

import enemygame.EnemyGame;
import enemygame.entities.Enemy;
import enemygame.util.DoublePoint;

import java.awt.*;
import java.util.ArrayList;

public class EnemyManager implements GameTick {
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Enemy> removalQueue;

    private final Dimension screenSize;
    private final int minOnScreenXPos, maxOnScreenXPos;
    private final int offScreenSpawnRange;

    private final int spawnCooldown = 60;
    private final int maxEnemies = 20;
    private int numSpawnedEnemies = 0;
    private int currentSpawnCooldown = 0;

    public EnemyManager(Dimension screenSize, int offScreenSpawnRange) {
        enemies = new ArrayList<>();
        removalQueue = new ArrayList<>();
        this.screenSize = screenSize;
        this.offScreenSpawnRange = offScreenSpawnRange;
        minOnScreenXPos = -100;
        maxOnScreenXPos = (int) screenSize.getWidth() + 100;
    }

    @Override
    public void tick() {
        for (Enemy e : enemies) {
            e.tick();
            if (e.getHealth() <= 0)
                queueEnemyForRemoval(e);
        }

        currentSpawnCooldown--;
        for (Enemy e : removalQueue) {
            enemies.remove(e);
            EnemyGame.getGamePanel().getDrawableManager().remove(e);
            numSpawnedEnemies--;
        }
        removalQueue.clear();
    }

    public void trySpawnEnemy() {
        if (currentSpawnCooldown > 0 || numSpawnedEnemies > maxEnemies)
            return;

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

        enemies.add(new Enemy(new DoublePoint(enemyX, enemyY), EnemyGame.getPlayer()));
        numSpawnedEnemies++;
        currentSpawnCooldown = spawnCooldown;
    }

    public void queueEnemyForRemoval(Enemy e) {
        removalQueue.add(e);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public int getNumSpawnedEnemies() {
        return numSpawnedEnemies;
    }
}
