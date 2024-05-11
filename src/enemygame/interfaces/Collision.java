package enemygame.interfaces;

import java.awt.*;

public interface Collision {
    Rectangle getCollision();

    default boolean collidesWith(Collision c) {
        return getCollision().intersects(c.getCollision());
    }
}
