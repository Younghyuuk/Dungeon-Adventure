package Model;

import View.GamePanel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static javax.imageio.ImageIO.read;

/**
 * Represents a basic 'Pit' object that can spawn in any room of a dungeon.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public class Pit extends Item {
    /**
     * The minimum amount of damage that a pit can inflict.
     */
    private static final int MIN_DAMAGE = 1;
    /**
     * The maximum amount of damage that a pit can inflict.
     */
    private static final int MAX_DAMAGE = 20;
    /**
     * The images associated with a 'Pit'.
     */
    private transient BufferedImage myPit;
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
        super(theGP, theWorldX, theWorldY);
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
     * Gets the minimum amount of damage a pit can cause to the player.
     *
     * @return The minimum damage the player can take from the pit.
     */
    public int getMinDamage() {
        return MIN_DAMAGE;
    }

    /**
     * Gets the maximum amount of damage a pit can cause to the player.
     *
     * @return The maximum damage the player can take from the pit.
     */
    public int getMaxDamage() {
        return MAX_DAMAGE;
    }

    /**
     * Gets the buffered image associated with a pit.
     *
     * @return The image to use for a pit.
     */
    public BufferedImage getPitImage() {
        return myPit;
    }

    /**
     * Gets the images associated with the 'Pit'.
     */
    @Override
    public void getItemImage() {
        try {
//            myPit1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/sandBrickFloor.png")));
            myPit = read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/pit0.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
