package enemygame.logic;

import java.awt.*;

public interface Collision {
    Rectangle getCollision();

    default boolean collidesWith(Collision other) {
        return getCollision().intersects(other.getCollision());
    }
}
