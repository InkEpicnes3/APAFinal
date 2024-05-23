package enemygame.entities;

import enemygame.data.Images;
import enemygame.entities.projectiles.Projectile;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.logic.InputManager;
import enemygame.util.DoublePoint;
import enemygame.EnemyGame;
import enemygame.util.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    private final InputManager input;
    private final double fireCooldown;
    private double timeSinceFire;
    private final double hitInvincibilityTime;
    private double timeSinceHit;
    private boolean inputEnabled;

    public Player(DoublePoint position, InputManager input) {
        super(position, new Dimension(50, 50), 400, 100, 0, EntityType.PLAYER);
        setSprite(new Sprite(this.position, this.size, DrawLayer.PLAYER, Images.PURPLE_FACE));
        this.input = input;
        inputEnabled = true;

        fireCooldown = 0.1;
        timeSinceFire = 0.0;
        hitInvincibilityTime = 1;
        timeSinceHit = 0;
    }

    @Override
    public void tick(double frameTime) {
        timeSinceHit += frameTime;

        if (inputEnabled) {
            // Movement from input
            velocity = new Vector();
            if (input.isKeyPressed(KeyEvent.VK_W))
                velocity.add(new Vector(0, -1));
            if (input.isKeyPressed(KeyEvent.VK_S))
                velocity.add(new Vector(0, 1));
            if (input.isKeyPressed(KeyEvent.VK_A))
                velocity.add(new Vector(-1, 0));
            if (input.isKeyPressed(KeyEvent.VK_D))
                velocity.add(new Vector(1, 0));
            velocity.setLength(movementSpeed);
            applyVelocity(frameTime);

            // Don't let player go offscreen
            Dimension windowSize = EnemyGame.getWindowSize();
            if (position.getX() < 0)
                position.setX(0);
            if (position.getX() > windowSize.getWidth() - size.getWidth())
                position.setX(windowSize.getWidth() - size.getWidth());
            if (position.getY() < 0)
                position.setY(0);
            if (position.getY() > windowSize.getHeight() - size.getHeight())
                position.setY(windowSize.getHeight() - size.getHeight());

            // Shooting
            timeSinceFire += frameTime;
            if (input.isMousePressed() && timeSinceFire >= fireCooldown) {
                EnemyGame.getEntityManager().addEntity(new Projectile(new DoublePoint(position), new Vector(this.position, input.getMousePosition()), true));
                timeSinceFire = 0.0;
            }
        }
    }

    public void disableInput() {
        inputEnabled = false;
    }

    public void enableInput() {
        inputEnabled = true;
    }

    public void resetHealth() {
        health.setCurrentHealth(health.getMaxHealth());
    }

    public void damage(double amount) {
        if (amount != 0 && timeSinceHit > hitInvincibilityTime) {
            health.damage(amount);
            timeSinceHit = 0;
        }
    }

    public boolean isInHitCooldown() {
        return timeSinceHit <= hitInvincibilityTime;
    }
}
