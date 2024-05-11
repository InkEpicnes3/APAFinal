package enemygame.entities;

import enemygame.EnemyGame;
import enemygame.graphics.GamePanel;
import enemygame.graphics.sprite.Sprite;
import enemygame.managers.EntityManager;
import enemygame.managers.ImageManager;
import enemygame.managers.InputManager;
import enemygame.util.DoublePoint;
import enemygame.util.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    private Sprite sprite;
    private InputManager input;
    private EntityManager entityManager;

    private final int fireCooldown = 10;
    private int currentFireCooldown = 0;

    public Player(DoublePoint position) {
        super(position, new Dimension(50, 50), 400, 100);
        sprite = new Sprite(position, size, GamePanel.LAYER_PLAYER, ImageManager.PURPLE_FACE);
        input = EnemyGame.getInput();
        entityManager = EnemyGame.getEntityManager();
        entityManager.setPlayer(this);
    }

    @Override
    public void tick(double frameTime) {
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
        applyVelocity(frameTime);

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
            Projectile p = new Projectile(new DoublePoint((int) position.getX(), (int) position.getY()),
                    new Vector(position, input.getMousePosition()), 20);
            entityManager.addPlayerProjectile(p);
        }
    }

    public void kill() {

    }
}