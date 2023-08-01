package Model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static javax.imageio.ImageIO.read;

/**
 * Represents a basic Health Potion object.
 */
public class HealthPotion extends Item {
    /**
     * The row that the 'HealthPotion' will live in.
     */
    private int myRow;
    /**
     * The column that the 'HealthPotion' will live in.
     */
    private int myCol;
    /**
     * The images associated with a 'HealthPotion'.
     */
    private BufferedImage myHealth1, myHealth2, myHealth3;
    /**
     * The number associated with a 'HealthPotion' item.
     */
    private static final int ITEM_NUMBER = 4;
    /**
     * The chance a health potion will spawn in any given room.
     */
    private static final double SPAWN_CHANCE = 0.10;
    /**
     * The minimum amount of health that can be received.
     */
    private static final int MIN_HEALTH = 5;
    /**
     * The maximum amount of health that can be received.
     */
    private static final int MAX_HEALTH = 15;

    /**
     * Constructs a basic 'HealthPotion' object.
     *
     * @param theDungeon The dungeon to place the 'HealthPotion' in.
     */
    public HealthPotion(final Dungeon theDungeon) {
        // TODO: Get random row and col in correct spots
        super(ITEM_NUMBER, SPAWN_CHANCE, theDungeon);
        getItemImage();
    }

    public int getRowAndCol() {
        // We want to randomly select from the rows and columns available
        Random random = new Random();
        int[] rows = {0, 1, 2, 3, 4, 5, 6};
        int[] cols = {0, 1, 2, 3, 4, 5, 6};

        return 0;
    }

    /**
     * Gets the amount of health this 'HealthPotion' will give.
     *
     * @return The amount of health obtainable.
     */
    public int getHealth() {
        Random random = new Random();
        return random.nextInt(MAX_HEALTH - MIN_HEALTH + 1) + MIN_HEALTH;
    }

    /**
     * Gets the images associated with the 'HealthPotion'.
     */
    @Override
    public void getItemImage() {
        try {
            myHealth1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Health/Health_1.png")));
            myHealth2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Health/Health_2.png")));
            myHealth3 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Health/Health_3.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
