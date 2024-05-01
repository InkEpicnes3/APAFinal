package enemygame;

import enemygame.gui.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EnemyGamePanel extends JPanel {
    private final ArrayList<Drawable> drawables;

    public EnemyGamePanel(Dimension size) {
        super(null, true);
        setPreferredSize(size);
        drawables = new ArrayList<>();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawables.forEach(d -> d.draw(g));
    }

    public void addDrawable(Drawable d) {
        drawables.add(d);
    }

    public void removeDrawable(Drawable d) {
        drawables.remove(d);
    }
}
