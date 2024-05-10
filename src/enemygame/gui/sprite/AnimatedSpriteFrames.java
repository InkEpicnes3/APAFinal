package enemygame.gui.sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class AnimatedSpriteFrames {
    private final String animName;
    private final int framesPerSprite;
    private final boolean repeating;
    private final ArrayList<BufferedImage> sprites;

    public AnimatedSpriteFrames(String animName, int framesPerSprite, boolean repeating, BufferedImage... spriteImages) {
        this.animName = animName;
        this.framesPerSprite = framesPerSprite;
        this.repeating = repeating;
        sprites = new ArrayList<>();
        sprites.addAll(Arrays.asList(spriteImages));
    }

    public String getAnimName() {
        return animName;
    }

    public int getFramesPerSprite() {
        return framesPerSprite;
    }

    public boolean isRepeating() {
        return repeating;
    }

    public int getNumFrames() {
        return sprites.size();
    }

    public BufferedImage getSpriteImage(int spriteIndex) {
        return sprites.get(spriteIndex);
    }
}
