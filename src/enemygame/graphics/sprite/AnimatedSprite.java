package enemygame.graphics.sprite;

import enemygame.EnemyGame;
import enemygame.interfaces.Drawable;
import enemygame.interfaces.GameTick;
import enemygame.util.DoublePoint;

import java.awt.*;
import java.util.HashMap;

public class AnimatedSprite implements Drawable, GameTick {
    private DoublePoint position;
    private Dimension size;
    private final int drawLayer;
    private final HashMap<String, SpriteAnimation> animations;
    private SpriteAnimation currentAnimation;
    private int currentSpriteFrames, currentSpriteIndex;

    public AnimatedSprite(DoublePoint position, Dimension size, int drawLayer, SpriteAnimation... animations) {
        this.position = position;
        this.size = size;
        this.animations = new HashMap<>();
        for (SpriteAnimation f : animations)
            this.animations.put(f.getAnimName(), f);
        this.drawLayer = drawLayer;
        changeAnimTo(animations[0].getAnimName());
        EnemyGame.getGamePanel().addDrawable(drawLayer, this);
        EnemyGame.getSpriteManager().addAnimatedSprite(this);
    }

    public void changeAnimTo(String animName) {
        currentAnimation = animations.get(animName);
        currentSpriteIndex = 0;
        currentSpriteFrames = 0;
    }

    @Override
    public void tick(double frameTime) {
        currentSpriteFrames++;
        if (currentSpriteFrames == currentAnimation.getFramesPerSprite()) {
            currentSpriteFrames = 0;
            currentSpriteIndex++;
            if (currentSpriteIndex == currentAnimation.getNumFrames()) {
                if (currentAnimation.isRepeating())
                    currentSpriteIndex = 0;
                else
                    currentSpriteIndex--;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(currentAnimation.getSpriteImage(currentSpriteIndex), (int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight(), null);
    }

    public boolean isDone() {
        return currentAnimation.isRepeating() || currentSpriteIndex == currentAnimation.getNumFrames() - 1;
    }

    public String getCurrentAnimationName() {
        return currentAnimation.getAnimName();
    }

    public DoublePoint getPosition() {
        return position;
    }

    public void setPosition(DoublePoint position) {
        this.position = position;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public int getDrawLayer() {
        return drawLayer;
    }
}
