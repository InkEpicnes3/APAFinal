package enemygame.managers;

import enemygame.EnemyGame;
import enemygame.graphics.sprite.AnimatedSprite;
import enemygame.interfaces.GameTick;

import java.util.ArrayList;

public class AnimatedSpriteManager implements GameTick {
    private ArrayList<AnimatedSprite> sprites;

    public AnimatedSpriteManager() {
        sprites = new ArrayList<>();
    }

    @Override
    public void tick(double frameTime) {
        sprites.forEach(s -> s.tick(frameTime));
    }

    public void addAnimatedSprite(AnimatedSprite sprite) {
        sprites.add(sprite);
    }

    public void removeAnimatedSprite(AnimatedSprite sprite) {
        sprites.remove(sprite);
        EnemyGame.getGamePanel().removeDrawable(sprite.getDrawLayer(), sprite);
    }

    public int getNumSprites() {
        return sprites.size();
    }
}
