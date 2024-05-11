package enemygame.graphics.sprite;

import enemygame.EnemyGame;
import enemygame.interfaces.Drawable;
import enemygame.util.DoublePoint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite implements Drawable {
    private DoublePoint position;
    private Dimension size;
    private int drawLayer;
    private BufferedImage image;

    public Sprite(DoublePoint position, Dimension size, int drawLayer, BufferedImage image) {
        this.position = position;
        this.size = size;
        this.drawLayer = drawLayer;
        this.image = image;
        EnemyGame.getGamePanel().addDrawable(drawLayer, this);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight(), null);
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

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
