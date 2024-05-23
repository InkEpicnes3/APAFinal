package enemygame.managers;

import enemygame.EnemyGame;
import enemygame.entities.*;
import enemygame.util.interfaces.GameTick;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityManager implements GameTick {
    private final HashMap<EntityType, ArrayList<Entity>> entities;
    private Player player;

    public EntityManager() {
        entities = new HashMap<>();
        for (EntityType type : EntityType.values())
            entities.put(type, new ArrayList<>());
    }

    @Override
    public void tick(double frameTime) {
        for (ArrayList<Entity> entityList : entities.values())
            for (Entity e : entityList)
                e.tick(frameTime);
        player.tick(frameTime);

        collidePlayerProjectiles();
        collideEnemyProjectiles();
        collidePlayerWithEnemies();
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
                if (e.getType() == EntityType.ENEMY && p.getType() == EntityType.PLAYER_PROJECTILE && p.collidesWith(e)) {
                    Projectile projectile = (Projectile) p;
                    e.getVelocity().add(((Projectile) p).getKnockback());
                    projectile.hit();
                    e.getHealth().damage(p.getDamage());
                }
    }

    private void collideEnemyProjectiles() {
        ArrayList<Entity> enemyProjectiles = getEntities(EntityType.ENEMY_PROJECTILE);
        for (Entity p : enemyProjectiles)
            if (p.getType() == EntityType.ENEMY_PROJECTILE && p.collidesWith(player)) {
                Projectile projectile = (Projectile) p;
                player.damage(p.getDamage());
                player.getVelocity().add(projectile.getKnockback());
                projectile.hit();
            }
    }

    private void collidePlayerWithEnemies() {
        ArrayList<Entity> enemies = getEntities(EntityType.ENEMY);
        for (Entity e : enemies)
            if (e.getType() == EntityType.ENEMY && e.collidesWith(player))
                player.damage(e.getDamage());
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
        for (Entity projectile : projectiles) {
            Projectile p = (Projectile) projectile;
            if (!p.isVisible() || p.hasReachedHitLimit())
                invalidProjectiles.add((Projectile) projectile);
        }
        invalidProjectiles.forEach(Projectile::kill);
    }

    private void removeDeadEnemies() {
        ArrayList<Entity> enemies = getEntities(EntityType.ENEMY);
        for (int i = 0; i < enemies.size(); i++) {
            Entity e = enemies.get(i);
            if (e.getType() == EntityType.ENEMY && e.getHealth().isDead())
                e.kill();
        }
    }

    public void clearAllEntities() {
        for (ArrayList<Entity> entityList : entities.values())
            for (int i = entityList.size() - 1; i >= 0; i--)
                entityList.get(i).kill();
        boolean newEntitiesSpawned = false;
        for (ArrayList<Entity> entityList : entities.values())
            if (entityList.size() != 0)
                newEntitiesSpawned = true;
        if (newEntitiesSpawned)
            clearAllEntities();
    }

    public void addEntity(Entity e) {
        if (e.getType() == EntityType.PLAYER)
            setPlayer((Player) e);
        else
            entities.get(e.getType()).add(e);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getNumEntities(EntityType entityType) {
        return entities.get(entityType).size();
    }

    public ArrayList<Entity> getEntities(EntityType entityType) {
        return entities.get(entityType);
    }
}
