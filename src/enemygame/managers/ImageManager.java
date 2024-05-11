package enemygame.managers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    public static final BufferedImage CURSOR_IMG = load("cursor.png");
    public static final BufferedImage PURPLE_FACE = load("purple_face_256x.png");
    public static final BufferedImage BLUE_FACE = load("blue_face_256x.png");
    public static final BufferedImage RED_FACE = load("red_face_256x.png");

    public static final BufferedImage PROJECTILE_GREEN_SPIN_1 = load("projectile_green_spin_1.png");
    public static final BufferedImage PROJECTILE_GREEN_SPIN_2 = load("projectile_green_spin_2.png");
    public static final BufferedImage PROJECTILE_GREEN_SPIN_3 = load("projectile_green_spin_3.png");
    public static final BufferedImage PROJECTILE_GREEN_SPIN_4 = load("projectile_green_spin_4.png");
    public static final BufferedImage PROJECTILE_RED_SPIN_1 = load("projectile_red_spin_1.png");
    public static final BufferedImage PROJECTILE_RED_SPIN_2 = load("projectile_red_spin_2.png");
    public static final BufferedImage PROJECTILE_RED_SPIN_3 = load("projectile_red_spin_3.png");
    public static final BufferedImage PROJECTILE_RED_SPIN_4 = load("projectile_red_spin_4.png");
    public static final BufferedImage PROJECTILE_YELLOW_SPIN_1 = load("projectile_yellow_spin_1.png");
    public static final BufferedImage PROJECTILE_YELLOW_SPIN_2 = load("projectile_yellow_spin_2.png");
    public static final BufferedImage PROJECTILE_YELLOW_SPIN_3 = load("projectile_yellow_spin_3.png");
    public static final BufferedImage PROJECTILE_YELLOW_SPIN_4 = load("projectile_yellow_spin_4.png");
    public static final BufferedImage PROJECTILE_BLUE_SPIN_1 = load("projectile_blue_spin_1.png");
    public static final BufferedImage PROJECTILE_BLUE_SPIN_2 = load("projectile_blue_spin_2.png");
    public static final BufferedImage PROJECTILE_BLUE_SPIN_3 = load("projectile_blue_spin_3.png");
    public static final BufferedImage PROJECTILE_BLUE_SPIN_4 = load("projectile_blue_spin_4.png");

    public static BufferedImage load(String imageName) {
        try {
            return ImageIO.read(new File(System.getProperty("user.dir") + "/res/" + imageName));
        }
        catch (IOException e) {
            return null;
        }
    }
}
