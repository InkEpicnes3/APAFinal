package enemygame.entities;

import enemygame.data.SpriteAnimations;
import enemygame.graphics.DrawLayer;
import enemygame.graphics.Sprite;
import enemygame.util.DoublePoint;

import java.awt.*;

public class Coin extends Entity {
    public Coin(DoublePoint position) {
        super(position, new Dimension(35, 35), 0, -1, 0, EntityType.ITEM);
        setSprite(new Sprite(position, size, DrawLayer.PROJECTILES, SpriteAnimations.COIN_SPIN));
    }

    @Override
    public void tick(double frameTime) {}
}
