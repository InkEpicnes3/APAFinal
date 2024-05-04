package enemygame.logic;

import enemygame.EnemyGame;
import enemygame.entities.Projectile;

import java.util.ArrayList;

public class ProjectileManager implements GameTick {
    private final ArrayList<Projectile> projectiles;
    private final ArrayList<Projectile> removalQueue;

    public ProjectileManager() {
        projectiles = new ArrayList<>();
        removalQueue = new ArrayList<>();
    }

    @Override
    public void tick() {
        projectiles.forEach(GameTick::tick);
        for (Projectile p : removalQueue) {
            projectiles.remove(p);
            EnemyGame.getGamePanel().getDrawableManager().remove(p);
        }
        removalQueue.clear();

        projectiles.forEach(p -> {
            if (p.getPosition().getX() > EnemyGame.getWindow().getWidth()
                    || p.getPosition().getX() < -p.getSize().getWidth()
                    || p.getPosition().getY() > EnemyGame.getWindow().getHeight()
                    || p.getPosition().getY() < -p.getSize().getHeight())
                queueForRemoval(p);
        });
    }

    public void queueForRemoval(Projectile p) {
        removalQueue.add(p);
    }

    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }
}
