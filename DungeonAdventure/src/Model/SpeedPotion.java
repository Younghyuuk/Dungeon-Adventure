package Model;

import View.GamePanel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;


/**
 * This class represents a vision potion that is able to be picked up within the dungeon.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public class SpeedPotion extends Item {
    /**
     * The speed able to be given by the speed potion.
     */
    private static final int SPEED_BOOST = 1;
    /**
     * The images associated with a 'HealthPotion'.
     */
    private transient BufferedImage mySpeedBoost;

    /**
     * Constructs a basic 'HealthPotion' object.
     *
     * @param theWorldX The world-x coordinate to draw the item at.
     * @param theWorldY The world-y coordinate to draw the item at.
     * @param theGP     The GamePanel to draw the item onto.
     */
    public SpeedPotion(final int theWorldX, final int theWorldY, final GamePanel theGP) {
        super(theGP, theWorldX, theWorldY);
        getItemImage();
        super.setImage(mySpeedBoost);
    }

    /**
     * Gets the amount of speed the speed potion will give to the player.
     *
     * @return The speed boost to give.
     */
    public int getSpeedBoost() {
        return SPEED_BOOST;
    }

    /**
     * Gets the buffered image associated with a speed potion.
     *
     * @return The image to use for a speed potion.
     */
    public BufferedImage getSpeedPotionImage() {
        return mySpeedBoost;
    }

    /**
     * Gets the images associated with the 'VisionPotion'.
     */
    @Override
    public void getItemImage() {
        try {
            mySpeedBoost = read(Objects.requireNonNull(getClass().getResourceAsStream("/speed/speedPotion.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
