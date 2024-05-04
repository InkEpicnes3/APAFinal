package enemygame.entities;

import enemygame.EnemyGame;
import enemygame.logic.InputManager;
import enemygame.logic.ProjectileManager;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    private final InputManager input;
    private final ProjectileManager projectileManager;

    private final int fireCooldown = 10;
    private int currentFireCooldown = 0;

    public Player(DoublePoint position, Dimension size, int speed) {
        super(position, size, speed);
        input = EnemyGame.getInput();
        projectileManager = EnemyGame.getProjectileManager();
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

        currentFireCooldown--;
        if (input.isMousePressed() && currentFireCooldown <= 0) {
            currentFireCooldown = fireCooldown;
            projectileManager.addProjectile(
                    new Projectile(new DoublePoint((int) position.getX(), (int) position.getY()),
                                   new Vector(position, input.getMousePosition()), 20));
        }

        move();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect((int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight());

        g.setColor(Color.WHITE);
        g.drawString(String.format("%f, %f", velocity.getX(), velocity.getY()), (int) position.getX(),
                (int) position.getY());
    }
}
