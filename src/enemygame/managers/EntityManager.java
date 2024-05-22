package enemygame.managers;

import enemygame.EnemyGame;
import enemygame.entities.*;
import enemygame.util.interfaces.GameTick;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class EntityManager implements GameTick {
    private final HashMap<EntityType, ArrayList<Entity>> entities;

    public EntityManager() {
        entities = new HashMap<>();
        for (EntityType type : EntityType.values())
            entities.put(type, new ArrayList<>());
        entities.get(EntityType.PLAYER).add(null);
    }

    @Override
    public void tick(double frameTime) {
        for (ArrayList<Entity> entityList : entities.values())
            for (Entity e : entityList)
                e.tick(frameTime);

        collidePlayerProjectiles();
        collideEnemyProjectiles();
        collidePlayerWithCoins();
        removeInvalidProjectiles(getEntities(EntityType.PLAYER_PROJECTILE));
        removeInvalidProjectiles(getEntities(EntityType.ENEMY_PROJECTILE));
        removeDeadEnemies();
    }

    private void collidePlayerProjectiles() {
        ArrayList<Entity> enemies = getEntities(EntityType.ENEMY);
        ArrayList<Entity> playerProjectiles = getEntities(EntityType.PLAYER_PROJECTILE);
        for (Entity e : enemies)
            for (Entity p : playerProjectiles)
                if (e instanceof Enemy && p instanceof Projectile && e.collidesWith(p)) {
                    Projectile projectile = (Projectile) p;
                    e.getVelocity().add(((Projectile) p).getKnockback());
                    projectile.hit();
                    e.getHealth().damage(p.getHitbox().getDamage());
                }
    }

    private void collideEnemyProjectiles() {
        ArrayList<Entity> enemyProjectiles = getEntities(EntityType.ENEMY_PROJECTILE);
        Player player = getPlayer();
        for (Entity p : enemyProjectiles)
            if (p instanceof Projectile && p.collidesWith(player)) {
                Projectile projectile = (Projectile) p;
                player.getVelocity().add(projectile.getKnockback());
                projectile.hit();
            }
    }

    private void collidePlayerWithCoins() {
        ArrayList<Entity> coins = getEntities(EntityType.ITEM);
        for (int i = 0; i < coins.size(); i++)
            if (coins.get(i) instanceof Coin && getPlayer().collidesWith(coins.get(i))) {
                coins.get(i).kill();
                EnemyGame.incrementGameScore();
            }
    }

    private void removeInvalidProjectiles(ArrayList<Entity> projectiles) {
        ArrayList<Projectile> invalidProjectiles = new ArrayList<>();
        Rectangle screenRect = new Rectangle(new Point(), EnemyGame.getWindowSize());
        for (Entity projectile : projectiles) {
            Projectile p = (Projectile) projectile;
            if (!p.getCollision().intersects(screenRect) || p.hasReachedHitLimit())
                invalidProjectiles.add((Projectile) projectile);
        }
        invalidProjectiles.forEach(Projectile::kill);
    }

    private void removeDeadEnemies() {
        ArrayList<Entity> enemies = getEntities(EntityType.ENEMY);
        for (int i = 0; i < enemies.size(); i++) {
            Entity e = enemies.get(i);
            if (e instanceof Enemy && e.getHealth().isDead())
                e.kill();
        }
    }

    public void addEntity(Entity e) {
        if (e.getType() == EntityType.PLAYER)
            setPlayer((Player) e);
        else
            entities.get(e.getType()).add(e);
    }

    public Player getPlayer() {
        return (Player) entities.get(EntityType.PLAYER).get(0);
    }

    public void setPlayer(Player player) {
        entities.get(EntityType.PLAYER).set(0, player);
    }

    public int getNumEntities(EntityType entityType) {
        return entities.get(entityType).size();
    }

    public ArrayList<Entity> getEntities(EntityType entityType) {
        return entities.get(entityType);
    }
}
