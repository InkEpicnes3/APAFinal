package enemygame.logic;

import enemygame.util.DoublePoint;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener {
    private final ArrayList<Integer> pressedKeys;
    private DoublePoint mousePosition;
    private boolean mousePressed;

    public InputManager(JFrame window) {
        pressedKeys = new ArrayList<>();

        mousePosition = new DoublePoint(0, 0);
        mousePressed = false;

        window.addKeyListener(this);
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
    }

    public boolean isKeyPressed(Integer keyCode) {
        return pressedKeys.contains(keyCode);
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public DoublePoint getMousePosition() {
        return mousePosition;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedKeys.contains(e.getKeyCode())) pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove((Integer) e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition = new DoublePoint(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition = new DoublePoint(e.getPoint());
    }
}
