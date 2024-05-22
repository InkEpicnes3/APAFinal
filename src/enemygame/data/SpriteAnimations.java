package enemygame.data;


import enemygame.graphics.SpriteAnimation;

public class SpriteAnimations {
    public static final SpriteAnimation COIN_SPIN = new SpriteAnimation("CoinSpin", 7, true,
            Images.COIN_01,
            Images.COIN_02,
            Images.COIN_03,
            Images.COIN_04,
            Images.COIN_05,
            Images.COIN_06,
            Images.COIN_07,
            Images.COIN_08);

    public static final SpriteAnimation BLUE_PROJECTILE_SPIN = new SpriteAnimation("BlueProjectileSpin", 6, true,
            Images.PROJECTILE_BLUE_SPIN_1,
            Images.PROJECTILE_BLUE_SPIN_4,
            Images.PROJECTILE_BLUE_SPIN_2,
            Images.PROJECTILE_BLUE_SPIN_3);
    public static final SpriteAnimation GREEN_PROJECTILE_SPIN = new SpriteAnimation("GreenProjectileSpin", 6, true,
            Images.PROJECTILE_GREEN_SPIN_1,
            Images.PROJECTILE_GREEN_SPIN_4,
            Images.PROJECTILE_GREEN_SPIN_2,
            Images.PROJECTILE_GREEN_SPIN_3);
    public static final SpriteAnimation RED_PROJECTILE_SPIN = new SpriteAnimation("RedProjectileSpin", 6, true,
            Images.PROJECTILE_RED_SPIN_1,
            Images.PROJECTILE_RED_SPIN_4,
            Images.PROJECTILE_RED_SPIN_2,
            Images.PROJECTILE_RED_SPIN_3);
    public static final SpriteAnimation YELLOW_PROJECTILE_SPIN = new SpriteAnimation("YellowProjectileSpin", 6, true,
            Images.PROJECTILE_YELLOW_SPIN_1,
            Images.PROJECTILE_YELLOW_SPIN_4,
            Images.PROJECTILE_YELLOW_SPIN_2,
            Images.PROJECTILE_YELLOW_SPIN_3);

    public static final SpriteAnimation RED_ROCKET_UP = new SpriteAnimation("RedRocketUp", 3, true,
            Images.RED_ROCKET_UP_1,
            Images.RED_ROCKET_UP_2);
    public static final SpriteAnimation RED_ROCKET_RIGHT = new SpriteAnimation("RedRocketRight", 3, true,
            Images.RED_ROCKET_RIGHT_1,
            Images.RED_ROCKET_RIGHT_2);
    public static final SpriteAnimation RED_ROCKET_DIAGONAL = new SpriteAnimation("RedRocketDiagonal", 3, true,
            Images.RED_ROCKET_DIAGONAL_1,
            Images.RED_ROCKET_DIAGONAL_2);
}
