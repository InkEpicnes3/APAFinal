package enemygame.graphics;

import enemygame.EnemyGame;
import enemygame.entities.EntityType;
import enemygame.managers.DrawableManager;
import enemygame.managers.EntityManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final DrawableManager drawableManager = new DrawableManager();

    private static final Color backgroundColor = new Color(11, 11, 11);

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawableManager.draw(g);

        drawDebug(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawDebug(Graphics g) {
        // Info
        EntityManager entityManager = EnemyGame.getEntityManager();
        g.setColor(Color.WHITE);
        g.drawString("Enemies: " + entityManager.getNumEntities(EntityType.ENEMY), 10, 20);
        g.drawString("Player Projectiles: " + entityManager.getNumEntities(EntityType.PLAYER_PROJECTILE), 10, 35);
        g.drawString("Enemy Projectiles: " + entityManager.getNumEntities(EntityType.ENEMY_PROJECTILE), 10, 50);
    }

    public static DrawableManager getDrawableManager() {
        return drawableManager;
    }
}
