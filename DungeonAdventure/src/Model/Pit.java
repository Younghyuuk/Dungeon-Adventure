package Model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static javax.imageio.ImageIO.read;

/**
 * Represents a basic 'Pit' object that can spawn in any room of a dungeon.
 */
public class Pit extends Item{
    /**
     * The row that the 'Pit' will live in.
     */
    private int myRow;
    /**
     * The column that the 'Pit' will live in.
     */
    private int myCol;
    /**
     * The images associated with a 'Pit'.
     */
    private BufferedImage myPit1, myPit2;
    /**
     * The number associated with a 'Pit' item.
     */
    private static final int ITEM_NUMBER = 6;
    /**
     * The chance a pit will spawn in any given room.
     */
    private static final double SPAWN_CHANCE = 0.10;
    /**
     * The minimum amount of damage that a pit can inflict.
     */
    private static final int MIN_DAMAGE = 1;
    /**
     * The maximum amount of damage that a pit can inflict.
     */
    private static final int MAX_DAMAGE = 20;

    /**
     * Construts a basic 'Pit' object.
     *
     * @param theDungeon The dungeon that the pit will be placed in.
     */
    public Pit(final Dungeon theDungeon) {
        super(ITEM_NUMBER, SPAWN_CHANCE, theDungeon);
        getItemImage();
    }

    /**
     * Gets the amount of damage that this pit will cause.
     *
     * @return Returns the damage (1-20) it will inflict on the player.
     */
    public int getPitDamage() {
        Random random = new Random();
        return random.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
    }

    /**
     * Gets the images associated with the 'Pit'.
     */
    @Override
    public void getItemImage() {
        try {
            myPit1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/sandBrickFloor.png")));
            myPit2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/pit.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}