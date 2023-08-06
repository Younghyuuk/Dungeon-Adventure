package Model;

import View.GamePanel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static javax.imageio.ImageIO.read;

public class VisionPotion extends Item {
    /**
     * The images associated with a 'HealthPotion'.
     */
    private BufferedImage myVision1, myVision2, myVision3;
    /**
     * The number associated with a 'HealthPotion' item.
     */
    private static final String ITEM_NAME = "VISION_POTION";
    /**
     * The chance a health potion will spawn in any given room.
     */
    private static final double SPAWN_CHANCE = 0.10;

    /**
     * Constructs a basic 'HealthPotion' object.
     *
     * @param theWorldX The world-x coordinate to draw the item at.
     * @param theWorldY The world-y coordinate to draw the item at.
     * @param theGP     The GamePanel to draw the item onto.
     */
    public VisionPotion(final int theWorldX, final int theWorldY, final GamePanel theGP) {
        super(theGP, theWorldX, theWorldY, ITEM_NAME, SPAWN_CHANCE);
        getItemImage();
    }

    /**
     * Gets the images associated with the 'VisionPotion'.
     */
    @Override
    public void getItemImage() {
        try {
            myVision1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Vision/Vision_1.png")));
            myVision2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Vision/Vision_2.png")));
            myVision3 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Vision/Vision_3.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
