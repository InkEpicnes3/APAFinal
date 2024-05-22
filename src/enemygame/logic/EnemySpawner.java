package enemygame.logic;

import enemygame.EnemyGame;
import enemygame.entities.Enemy;
import enemygame.entities.EntityType;
import enemygame.managers.EntityManager;
import enemygame.util.DoublePoint;
import enemygame.util.interfaces.GameTick;

import java.awt.*;

public class EnemySpawner implements GameTick {
    private final EntityManager entityManager;

    private double spawnCooldown;
    private double timeSinceSpawn;

    private int maxEnemies;

    public EnemySpawner(double spawnCooldown, int maxEnemies, EntityManager entityManager) {
        this.entityManager = entityManager;

        this.spawnCooldown = spawnCooldown;
        timeSinceSpawn = 0;

        this.maxEnemies = maxEnemies;
    }

    public void trySpawnEnemy() {
        if (timeSinceSpawn < spawnCooldown || entityManager.getNumEntities(EntityType.ENEMY) >= maxEnemies)
            return;

        Enemy enemy = new Enemy(new DoublePoint(), entityManager.getPlayer());
        Dimension screenSize = EnemyGame.getWindowSize();

        int minOnScreenXPos = (int) (-enemy.getSize().getWidth());
        int maxOnScreenXPos = (int) (screenSize.getWidth() + enemy.getSize().getWidth());
        int offScreenSpawnRange = 300;

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
    }

    @Override
    public void tick(double frameTime) {
        timeSinceSpawn += frameTime;
    }
}
