package Model;

import View.GamePanel;

import java.awt.*;
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
     * The number associated with a 'HealthPotion' item.
     */
    private static final String ITEM_NAME = "HEALTH_POTION";
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
     * The image associated with a 'HealthPotion'.
     */
    private BufferedImage myHealth;
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
        super(theGP, theWorldX, theWorldY, ITEM_NAME, SPAWN_CHANCE);
        findHealth();
        getItemImage();
        setImage(myHealth);
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
