package Model;

import View.GamePanel;

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
    private BufferedImage myPit;
    /**
     * The number associated with a 'Pit' item.
     */
    private static final String ITEM_NAME = "PIT";
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
     * The amount of damage this pit will cause.
     */
    private int myPitDamage;

    /**
     * Constructs a basic 'Pit' object.
     *
     * @param theWorldX The world-x coordinate to draw the item at.
     * @param theWorldY The world-y coordinate to draw the item at.
     * @param theGP     The GamePanel to draw the item onto.
     */
    public Pit(final int theWorldX, final int theWorldY, final GamePanel theGP) {
        super(theGP, theWorldX, theWorldY, ITEM_NAME, SPAWN_CHANCE);
        getItemImage();
        setPitDamage();
        super.setImage(myPit);
    }

    /**
     * Calculates the amount of damage this pit will cause to the player if hit.
     */
    public void setPitDamage() {
        Random random = new Random();
        myPitDamage = random.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
    }

    /**
     * Gets the amount of damage this pit will cause if it collides with the player.
     *
     * @return The damage the pit will cause.
     */
    public int getPitDamage() {
        return myPitDamage;
    }

    /**
     * Gets the images associated with the 'Pit'.
     */
    @Override
    public void getItemImage() {
        try {
//            myPit1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/sandBrickFloor.png")));
            myPit = read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/pit.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
