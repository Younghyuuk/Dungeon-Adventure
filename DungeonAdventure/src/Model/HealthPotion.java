package Model;

import View.GamePanel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

import static javax.imageio.ImageIO.read;

/**
 * Represents the basic health potion item.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public class HealthPotion extends Item implements Serializable {
    /**
     * The minimum amount of health that can be received.
     */
    private static final int MIN_HEALTH = 100;
    /**
     * The maximum amount of health that can be received.
     */
    private static final int MAX_HEALTH = 100;
    /**
     * The image associated with a 'HealthPotion'.
     */
    private transient BufferedImage myHealth;
    /**
     * The amount of health this 'HealthPotion' will give back.
     */
    private int myHealthBack;

    /**
     * Constructs a basic 'HealthPotion' object.
     *
     * @param theWorldX The world-x coordinate to draw the item at.
     * @param theWorldY The world-y coordinate to draw the item at.
     * @param theGP     The GamePanel to draw the item onto.
     */
    public HealthPotion(final int theWorldX, final int theWorldY, final GamePanel theGP) {
        super(theGP, theWorldX, theWorldY);
        findHealth();
        getItemImage();
        super.setImage(myHealth);
    }

    /**
     * Calculates the amount of health this 'HealthPotion' will give.
     */
    private void findHealth() {
        Random random = new Random();
        myHealthBack = random.nextInt(MAX_HEALTH - MIN_HEALTH + 1) + MIN_HEALTH;
    }

    /**
     * Gets the amount of health back this potion will give.
     *
     * @return The amount of health back obtainable from this potion.
     */
    public int getHealthBack() {
        return myHealthBack;
    }

    /**
     * Gets the value of the min health obtainable from the health potion.
     *
     * @return The minimum amount of health.
     */
    public int getMinHealth() {
        return MIN_HEALTH;
    }

    /**
     * Gets the value of the max health obtainable from the health potion.
     *
     * @return The maximum amount of health.
     */
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    /**
     * Gets the buffered image associated with a health potion.
     *
     * @return The image to use for a health potion.
     */
    public BufferedImage getHealthPotionImage() {
        return myHealth;
    }

    /**
     * Gets the images associated with the 'HealthPotion'.
     */
    @Override
    public void getItemImage() {
        try {
            myHealth = read(Objects.requireNonNull(getClass().getResourceAsStream("/Health/Health_1.png")));
//            myHealth2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Health/Health_2.png")));
//            myHealth3 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Health/Health_3.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
