package Model;

import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SpeedPotionTest {
    private SpeedPotion mySpeedPot;

    @BeforeEach
    public final void setUp() {
        GamePanel gp = new GamePanel();
        mySpeedPot = new SpeedPotion(100, 100, gp);
    }

    @Test
    void getItemImage() {
        BufferedImage itemImage = mySpeedPot.getSpeedPotionImage();
        assertNotNull(itemImage);
    }
}