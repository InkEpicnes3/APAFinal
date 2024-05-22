package enemygame.data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {
    public static final BufferedImage CURSOR_IMG = load("cursor.png");
    public static final BufferedImage PURPLE_FACE = load("purple_face_256x.png");
    public static final BufferedImage LIGHT_BLUE_FACE = load("light_blue_face_256x.png");
    public static final BufferedImage RED_FACE = load("red_face_256x.png");

    public static final BufferedImage COIN_01 = load("coin_01.png");
    public static final BufferedImage COIN_02 = load("coin_02.png");
    public static final BufferedImage COIN_03 = load("coin_03.png");
    public static final BufferedImage COIN_04 = load("coin_04.png");
    public static final BufferedImage COIN_05 = load("coin_05.png");
    public static final BufferedImage COIN_06 = load("coin_06.png");
    public static final BufferedImage COIN_07 = load("coin_07.png");
    public static final BufferedImage COIN_08 = load("coin_08.png");

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

    public static final BufferedImage RED_ROCKET_UP_1 = load("rocket_red_up_1.png");
    public static final BufferedImage RED_ROCKET_UP_2 = load("rocket_red_up_2.png");
    public static final BufferedImage RED_ROCKET_RIGHT_1 = load("rocket_red_right_1.png");
    public static final BufferedImage RED_ROCKET_RIGHT_2 = load("rocket_red_right_2.png");
    public static final BufferedImage RED_ROCKET_DIAGONAL_1 = load("rocket_red_up_right_1.png");
    public static final BufferedImage RED_ROCKET_DIAGONAL_2 = load("rocket_red_up_right_2.png");

    public static BufferedImage load(String imageName) {
        try {
            return ImageIO.read(new File(System.getProperty("user.dir") + "/res/" + imageName));
        }
        catch (IOException e) {
            return null;
        }
    }
}
