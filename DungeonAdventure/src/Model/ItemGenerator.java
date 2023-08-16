package Model;

import View.GamePanel;

import java.io.Serializable;
import java.util.Random;

/**
 * This class is designed specifically to generate a random item
 * that can be added into the dungeon.
 */
public class ItemGenerator implements Serializable {

    /**
     * Represents the amount of different items that are available.
     */
    private static final int NUMBER_OF_ITEM_TYPES = 3;

    /**
     * Gets a random item to place into the dungeon.
     *
     * @param theInitialXY  The initial X&Y coordinate (this will just be 0).
     * @param theGP         The 'GamePanel' The items will be drawn onto.
     * @return A randomly generated item.
     */
    public static Item getRandomItem(final int theInitialXY, final GamePanel theGP) {
        // We will randomly select the item type
        Random random = new Random();
        int randomItem = random.nextInt(NUMBER_OF_ITEM_TYPES);

        return switch (randomItem) {
            case 0 -> // Health Potion
                    new HealthPotion(theInitialXY, theInitialXY, theGP);
            case 1 -> // Pit
                    new Pit(theInitialXY, theInitialXY, theGP);
            case 2 -> // Vision Potion
                    new SpeedPotion(theInitialXY, theInitialXY, theGP);
            default -> // There was an unexpected randomItem value outside the specified range
                    throw new IllegalArgumentException("Item type is outside the range (0 - 2).");
        };
    }
}
