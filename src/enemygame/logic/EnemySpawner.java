package enemygame.logic;

import enemygame.EnemyGame;
import enemygame.entities.enemies.BubbleEnemy;
import enemygame.entities.enemies.Enemy;
import enemygame.entities.EntityType;
import enemygame.entities.enemies.RocketEnemy;
import enemygame.util.DoublePoint;
import enemygame.util.interfaces.GameTick;

import java.awt.*;

public class EnemySpawner implements GameTick {
    private final EntityManager entityManager;

    private double spawnCooldown;
    private double timeSinceSpawn;
    private int numEnemiesSpawned;
    private int maxEnemies;

    public EnemySpawner(EntityManager entityManager) {
        this.entityManager = entityManager;

        this.spawnCooldown = 1;
        timeSinceSpawn = 0;
        numEnemiesSpawned = 0;
        this.maxEnemies = 255;
    }

    public void trySpawnEnemy() {
        if (timeSinceSpawn < spawnCooldown || entityManager.getNumEntities(EntityType.ENEMY) >= maxEnemies)
            return;

        Enemy enemy;
        // Equations generated by Google sheets & Desmos
        if (Math.random() < -0.4 + 0.18 * Math.log(0.2 * numEnemiesSpawned))
            enemy = new BubbleEnemy(new DoublePoint(), entityManager.getPlayer());
        else if (Math.random() < -0.3 + 0.19 * Math.log(0.2 * numEnemiesSpawned))
            enemy = new RocketEnemy(new DoublePoint(), entityManager.getPlayer());
        else
            enemy = new Enemy(new DoublePoint(), entityManager.getPlayer());
        Dimension screenSize = EnemyGame.getWindowSize();

        int minOnScreenXPos = (int) (-enemy.getSize().getWidth());
        int maxOnScreenXPos = (int) (screenSize.getWidth() + enemy.getSize().getWidth());
        int offScreenSpawnRange = 500;

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

        enemy.setPosition(new DoublePoint(enemyX, enemyY));
        entityManager.addEntity(enemy);
        timeSinceSpawn = 0;
        numEnemiesSpawned++;
    }

    @Override
    public void tick(double frameTime) {
        timeSinceSpawn += frameTime;
    }
}
