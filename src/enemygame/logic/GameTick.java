package enemygame.logic;

import enemygame.EnemyGame;

public interface GameTick {
    void tick();

    static void register(GameTick t) {
        EnemyGame.getTickManager().add(t);
    }
}
