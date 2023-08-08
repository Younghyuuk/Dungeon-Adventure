package Model;

import View.GamePanel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static javax.imageio.ImageIO.read;

/**
 * This class represents a vision potion that is able to be picked up within the dungeon.
 */
public class VisionPotion extends Item {
    /**
     * The images associated with a 'HealthPotion'.
     */
    private BufferedImage myVision;

    /**
     * Constructs a basic 'HealthPotion' object.
     *
     * @param theWorldX The world-x coordinate to draw the item at.
     * @param theWorldY The world-y coordinate to draw the item at.
     * @param theGP     The GamePanel to draw the item onto.
     */
    public VisionPotion(final int theWorldX, final int theWorldY, final GamePanel theGP) {
        super(theGP, theWorldX, theWorldY);
        getItemImage();
        super.setImage(myVision);
    }

    /**
     * Gets the images associated with the 'VisionPotion'.
     */
    @Override
    public void getItemImage() {
        try {
            myVision = read(Objects.requireNonNull(getClass().getResourceAsStream("/vision/visionPotion.png")));
//            myVision2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Vision/Vision_2.png")));
//            myVision3 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Vision/Vision_3.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
