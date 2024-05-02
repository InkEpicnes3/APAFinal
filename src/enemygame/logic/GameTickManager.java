package enemygame.logic;

import java.util.ArrayList;

public class GameTickManager {
    private final ArrayList<GameTick> gameTickObjects;

    public GameTickManager() {
        gameTickObjects = new ArrayList<>();
    }

    public void tick() {
        gameTickObjects.forEach(GameTick::tick);
    }

    public void add(GameTick gt) {
        gameTickObjects.add(gt);
    }

    public void remove(GameTick gt) {
        gameTickObjects.remove(gt);
    }
}
