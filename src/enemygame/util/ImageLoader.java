package enemygame.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage load(String imageName) {
        try {
            return ImageIO.read(new File(System.getProperty("user.dir") + "/res/" + imageName));
        }
        catch (IOException e) {
            return null;
        }
    }
}
