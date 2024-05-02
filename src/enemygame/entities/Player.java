package enemygame.entities;

import enemygame.logic.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Player extends Entity implements KeyListener {
    private final ArrayList<Integer> pressedKeys;

    public Player(Point position, Dimension size, int speed) {
        super(position, size, speed);
        pressedKeys = new ArrayList<>();
    }

    @Override
    public void tick() {
        velocity = new Vector();
        if (pressedKeys.contains(KeyEvent.VK_W))
            velocity.add(new Vector(0, -1));
        if (pressedKeys.contains(KeyEvent.VK_S))
            velocity.add(new Vector(0, 1));
        if (pressedKeys.contains(KeyEvent.VK_A))
            velocity.add(new Vector(-1, 0));
        if (pressedKeys.contains(KeyEvent.VK_D))
            velocity.add(new Vector(1, 0));
        velocity.setLength(speed);
        move();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect((int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight());
    }

    @Override
    public Rectangle getCollision() {
        return new Rectangle(position, size);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedKeys.contains(e.getKeyCode()))
            pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove((Integer) e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
