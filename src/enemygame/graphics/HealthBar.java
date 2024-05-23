package enemygame.graphics;

import enemygame.data.Images;
import enemygame.util.DoublePoint;
import enemygame.util.GameObject;
import enemygame.util.HealthComponent;
import enemygame.util.interfaces.Drawable;

import java.awt.*;

public class HealthBar extends GameObject implements Drawable {
    private HealthComponent health;

    public HealthBar(DoublePoint position, Dimension size, HealthComponent health) {
        super(position, size);
        this.health = health;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Images.HEALTH_BAR_EMPTY, (int) position.getX(), (int) position.getY(),
                (int) getSize().getWidth(), (int) getSize().getHeight(), null);
        g.drawImage(Images.HEALTH_BAR_INNER, (int) position.getX() + 56, (int) position.getY() + 14,
                (int) (448 * (health.getCurrentHealth() / health.getMaxHealth())), 35, null);
    }

    @Override
    public DrawLayer getLayer() {
        return null;
    }
}
