package Model;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Represents a basic Health Potion object.
 */
public class HealthPotion extends Item {
    /**
     * The number associated with a 'HealthPotion' item.
     */
    private static final int ITEM_NUMBER = 4;
    /**
     * The chance a health potion will spawn in any given room.
     */
    private static final double SPAWN_CHANCE = 0.10;
    public HealthPotion(final Dungeon theDungeon) {
        super(ITEM_NUMBER, SPAWN_CHANCE, 0,0, theDungeon);
        getItemImage();
    }

    @Override
    public void getItemImage() {
        try {
            health1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Health/Health_1.png")));
            health2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Health/Health_2.png")));
            health2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/Health/Health_3.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
