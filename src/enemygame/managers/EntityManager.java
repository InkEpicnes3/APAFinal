package enemygame.managers;

import enemygame.entities.Enemy;
import enemygame.entities.Player;
import enemygame.entities.Projectile;
import enemygame.interfaces.GameTick;
import enemygame.util.Vector;

import java.util.ArrayList;

public class EntityManager implements GameTick {
    private Player player;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Projectile> playerProjectiles;
    private final ArrayList<Projectile> enemyProjectiles;

    public EntityManager() {
        enemies = new ArrayList<>();
        playerProjectiles = new ArrayList<>();
        enemyProjectiles = new ArrayList<>();
    }

    @Override
    public void tick(double frameTime) {
        player.tick(frameTime);

        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.tick(frameTime);
            if (e.getHealthComponent().isDead()) {
                enemies.remove(e);
                e.kill();
            }
        }
        for (int i = 0; i < playerProjectiles.size(); i++) {
            Projectile p = playerProjectiles.get(i);
            p.tick(frameTime);
            if (!p.isVisible() || p.hasCollided()) {
                playerProjectiles.remove(p);
                p.kill();
            }
        }
        for (int i = 0; i < enemyProjectiles.size(); i++) {
            Projectile p = enemyProjectiles.get(i);
            p.tick(frameTime);
            if (!p.isVisible() || p.hasCollided()) {
                enemyProjectiles.remove(p);
                p.kill();
            }
        }

        // Enemies hitting players with body
        for (Enemy e : enemies)
            if (e.collidesWith(player))
                player.getHealthComponent().damage(e.getDamage());
        // Enemy projectiles hitting player
        for (Projectile p : enemyProjectiles)
            if (p.collidesWith(player)) {
                player.getHealthComponent().damage(p.getDamage());
                p.markCollided();
            }
        // Player projectiles hitting enemies
        for (Projectile p : playerProjectiles)
            for (Enemy e : enemies)
                if (p.collidesWith(e)) {
                    e.getHealthComponent().damage(p.getDamage());
                    e.setVelocity(new Vector(-e.getVelocity().getX() * 8, -e.getVelocity().getY() * 8));
                    e.applyVelocity(frameTime);
                    p.markCollided();
                }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public void addPlayerProjectile(Projectile p) {
        playerProjectiles.add(p);
    }

    public void addEnemyProjectile(Projectile p) {
        enemyProjectiles.add(p);
    }

    public int getNumEnemies() {
        return enemies.size();
    }

    public int getNumPlayerProjectiles() {
        return playerProjectiles.size();
    }

    public int getNumEnemyProjectiles() {
        return enemyProjectiles.size();
    }
}
