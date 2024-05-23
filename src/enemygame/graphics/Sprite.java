package enemygame.graphics;

import enemygame.EnemyGame;
import enemygame.util.DoublePoint;
import enemygame.util.GameObject;
import enemygame.util.interfaces.Drawable;
import enemygame.util.interfaces.GameTick;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Sprite extends GameObject implements Drawable, GameTick {
    private final HashMap<String, SpriteAnimation> animations;
    private final DrawLayer layer;
    private SpriteAnimation currentAnimation;
    private boolean flipped;
    private int currentSpriteFrames, currentSpriteIndex;

    public Sprite(DoublePoint position, Dimension size, DrawLayer layer, BufferedImage sprite) {
        this(position, size, layer, new SpriteAnimation("", 1, false, sprite));
    }

    public Sprite(DoublePoint position, Dimension size, DrawLayer layer, SpriteAnimation... animations) {
        super(position, size);
        this.layer = layer;
        flipped = false;

        this.animations = new HashMap<>();
        for (SpriteAnimation anim : animations)
            this.animations.put(anim.getName(), anim);

        GamePanel.getDrawableManager().addDrawable(this);
        EnemyGame.getSpriteManager().addSprite(this);
        changeAnimTo(animations[0].getName());
    }

    public void changeAnimTo(String animName) {
        currentAnimation = animations.get(animName);
        currentSpriteIndex = 0;
        currentSpriteFrames = 0;
    }

    @Override
    public void tick(double frameTime) {
        currentSpriteFrames++;
        if (currentSpriteFrames == currentAnimation.getFramesPerImage()) {
            currentSpriteFrames = 0;
            currentSpriteIndex++;
            if (currentSpriteIndex == currentAnimation.getNumFrames()) {
                if (currentAnimation.isRepeating()) currentSpriteIndex = 0;
                else currentSpriteIndex--;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!flipped)
            g.drawImage(currentAnimation.getSpriteImage(currentSpriteIndex),
                    (int) position.getX(), (int) position.getY(),
                    (int) size.getWidth(), (int) size.getHeight(),
                    null);
        else
            g.drawImage(currentAnimation.getSpriteImage(currentSpriteIndex),
                    (int) (position.getX() + size.getWidth()), (int) position.getY(),
                    -(int) size.getWidth(), (int) size.getHeight(),
                    null);

    }

    @Override
    public DrawLayer getLayer() {
        return layer;
    }

    public void kill() {
        GamePanel.getDrawableManager().removeDrawable(this);
        EnemyGame.getSpriteManager().removeSprite(this);
    }

    public boolean isCurrentAnimationFinished() {
        return !currentAnimation.isRepeating() && currentSpriteIndex == currentAnimation.getNumFrames() - 1;
    }

    public double getAnimationProgress() {
        return (double) currentSpriteIndex / currentAnimation.getNumFrames();
    }

    public String getCurrentAnimationName() {
        return currentAnimation.getName();
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
}
