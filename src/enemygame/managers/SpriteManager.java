package enemygame.managers;

import enemygame.graphics.Sprite;
import enemygame.util.interfaces.GameTick;

import java.util.ArrayList;

public class SpriteManager implements GameTick {
    private final ArrayList<Sprite> sprites;

    public SpriteManager() {
        sprites = new ArrayList<>();
    }

    @Override
    public void tick(double frameTime) {
        sprites.forEach(s -> s.tick(frameTime));
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }
}
