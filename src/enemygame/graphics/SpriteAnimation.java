package enemygame.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class SpriteAnimation {
    private final String name;
    private final int framesPerImage;
    private final boolean repeating;
    private final ArrayList<BufferedImage> sprites;

    public SpriteAnimation(String animName, int framesPerSprite, boolean repeating, BufferedImage... spriteImages) {
        this.name = animName;
        this.framesPerImage = framesPerSprite;
        this.repeating = repeating;
        sprites = new ArrayList<>();
        sprites.addAll(Arrays.asList(spriteImages));
    }

    public String getName() {
        return name;
    }

    public int getFramesPerImage() {
        return framesPerImage;
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

