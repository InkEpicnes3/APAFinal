package enemygame.graphics;

import enemygame.EnemyGame;
import enemygame.interfaces.Drawable;
import enemygame.managers.ImageManager;
import enemygame.util.DoublePoint;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class GamePanel extends JPanel {
    public static final int LAYER_BG = 0;
    public static final int LAYER_ENEMY = 1;
    public static final int LAYER_PROJECTILE = 2;
    public static final int LAYER_PLAYER = 3;
    private static final Color backgroundColor = new Color(0x0B0B0B);
    private ArrayList<Drawable>[] drawables;

    public GamePanel(Dimension size) {
        setPreferredSize(size);
        drawables = new ArrayList[4];
        for (int i = 0; i < drawables.length; i++)
            drawables[i] = new ArrayList<>();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (ArrayList<Drawable> drawLayer : drawables) {
            try {
                for (Drawable d : drawLayer) {
                    d.draw(g);
                }
            }
            catch (ConcurrentModificationException ignored) {
                // Threads doing thready things - I don't care
            }
        }

        DoublePoint mousePos = EnemyGame.getInput().getMousePosition();
        g.drawImage(ImageManager.CURSOR_IMG, (int) mousePos.getX() - 20, (int) mousePos.getY() - 20, 40, 40, null);

        // DEBUG INFO
        int numDrawables = 0;
        for (ArrayList<Drawable> drawLayer : drawables)
            numDrawables += drawLayer.size();
        int numAnimatedSprites = EnemyGame.getSpriteManager().getNumSprites();
        int numEnemies = EnemyGame.getEntityManager().getNumEnemies();
        int numPlayerProjectiles = EnemyGame.getEntityManager().getNumPlayerProjectiles();
        int numEnemyProjectiles = EnemyGame.getEntityManager().getNumEnemyProjectiles();

        g.setColor(Color.WHITE);
        g.drawString("Drawbles: " + numDrawables, 10, 15);
        g.drawString("AnimSprites: " + numAnimatedSprites, 10, 30);
        g.drawString("Enemies: " + numEnemies, 10, 45);
        g.drawString("PlayerProj: " + numPlayerProjectiles, 10, 60);
        g.drawString("EnemyProj: " + numEnemyProjectiles, 10, 75);

        EnemyGame.getWindow().getToolkit().sync();
    }

    public void addDrawable(int layer, Drawable d) {
        drawables[layer].add(d);
    }

    public void removeDrawable(int layer, Drawable d) {
        drawables[layer].remove(d);
    }
}
