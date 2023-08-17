package Model;

import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class HealthPotionTest {
    private HealthPotion myHealthPot;
    @BeforeEach
    public final void setUp() {
        GamePanel gp = new GamePanel();
        myHealthPot = new HealthPotion(100, 100, gp);
    }

    @Test
    public final void getHealthBack() {
        int healthBack = myHealthPot.getHealthBack();
        assertTrue(healthBack >= myHealthPot.getMinHealth() && healthBack <= myHealthPot.getMaxHealth());
    }

    @Test
    public final void getItemImage() {
        BufferedImage itemImage = myHealthPot.getHealthPotionImage();
        assertNotNull(itemImage);
    }
}