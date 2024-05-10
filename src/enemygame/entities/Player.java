package enemygame.entities;

import enemygame.EnemyGame;
import enemygame.gui.Drawable;
import enemygame.logic.InputManager;
import enemygame.logic.ProjectileManager;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity implements Drawable {
    private final InputManager input;
    private final ProjectileManager projectileManager;

    private final int fireCooldown = 10;
    private int currentFireCooldown = 0;

    public Player(DoublePoint position) {
        super(position, new Dimension(50, 50), 6);
        input = EnemyGame.getInput();
        projectileManager = EnemyGame.getProjectileManager();
        EnemyGame.getGamePanel().getDrawableManager().add(this);
    }

    @Override
    public void tick() {
        velocity = new Vector();
        if (input.isKeyPressed(KeyEvent.VK_W))
            velocity.add(new Vector(0, -1));
        if (input.isKeyPressed(KeyEvent.VK_S))
            velocity.add(new Vector(0, 1));
        if (input.isKeyPressed(KeyEvent.VK_A))
            velocity.add(new Vector(-1, 0));
        if (input.isKeyPressed(KeyEvent.VK_D))
            velocity.add(new Vector(1, 0));
        velocity.setLength(speed);
        move();

        if (position.getX() < 0)
            position.setX(0);
        if (position.getX() > EnemyGame.getWindow().getWidth() - size.getWidth())
            position.setX(EnemyGame.getWindow().getWidth() - size.getWidth());
        if (position.getY() < 0)
            position.setY(0);
        if (position.getY() > EnemyGame.getWindow().getHeight() - size.getHeight())
            position.setY(EnemyGame.getWindow().getHeight() - size.getHeight());

        currentFireCooldown--;
        if (input.isMousePressed() && currentFireCooldown <= 0) {
            currentFireCooldown = fireCooldown;
            projectileManager.addProjectile(
                    new Projectile(new DoublePoint((int) position.getX(), (int) position.getY()),
                            new Vector(position, input.getMousePosition()), 20));
        }

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect((int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight());
    }
}
