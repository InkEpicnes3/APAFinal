package enemygame.gui;

import enemygame.EnemyGame;
import enemygame.logic.GameTick;
import enemygame.util.DoublePoint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedSprite implements Drawable, GameTick {
    private DoublePoint position;
    private Dimension size;
    private final BufferedImage[] images;
    private int framesPerImage, currentFrame;
    private int currentImageIndex;

    public AnimatedSprite(DoublePoint position, Dimension size, BufferedImage[] images, int framesPerImage) {
        this.position = position;
        this.size = size;
        this.images = images;
        this.framesPerImage = framesPerImage;
        currentFrame = 0;
        currentImageIndex = 0;
        EnemyGame.getGamePanel().getDrawableManager().add(this);
    }

    @Override
    public void tick() {
        currentFrame++;
        if (currentFrame == framesPerImage) {
            currentFrame = 0;
            currentImageIndex++;
            if (currentImageIndex == images.length)
                currentImageIndex = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(images[currentImageIndex], (int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight(), null);
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

    public int getFramesPerImage() {
        return framesPerImage;
    }

    public void setFramesPerImage(int framesPerImage) {
        this.framesPerImage = framesPerImage;
    }
}
