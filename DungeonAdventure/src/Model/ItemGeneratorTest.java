package Model;

import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemGeneratorTest {
    private GamePanel myGP;

    @BeforeEach
    public final void setUp() {
        myGP = new GamePanel();
    }

    @Test
    void getRandomItem() {
        assertNotNull(ItemGenerator.getRandomItem(100, myGP));
    }
}