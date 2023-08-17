package Model;

import Control.Keyboard;
import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class ThiefTest {
    private final GamePanel mockGamePanel = new GamePanel();
    private final Keyboard key = new Keyboard(mockGamePanel);
    private final MonsterDataBase md = new MonsterDataBase(mockGamePanel);
    private Monster skeleton;
    private Heroes myHero;
    @BeforeEach
    void setUp() {
        skeleton = md.getMonster("Skeleton");
        myHero = new Thief(mockGamePanel, key);
    }

    @Test
    public void testRegularAttack() {
        String result = myHero.regularAttack(skeleton);
        assertNotNull(result); // Ensure that the result is not null
        assertTrue(result.contains("hits") || result.contains("misses horribly"),
                "Unexpected regular attack result: " + result);
    }

    @Test
    public void testSpecialSkill() {
        String result = myHero.specialSkill(skeleton);
        assertNotNull(result); // Ensure that the result is not null
        assertTrue(result.contains("Thief launches special skill surprise attack!"),
                "Special skill result should contain 'Thief launches special skill surprise attack!'");
    }

    @Test
    public void testHpBarDrawing() {
        // Create a dummy Graphics2D object for testing
        Graphics2D graphics2D = (Graphics2D) new BufferedImage(1, 1,
                BufferedImage.TYPE_INT_ARGB).getGraphics();

        assertDoesNotThrow(() -> {
            myHero.drawHpBar(graphics2D);
        }, "Drawing the HP bar should not throw an exception");
    }

    @Test
    public void testGetHeroImage1() {
        // Ensure that images are loaded.
        assertNotNull(myHero.up1);
    }
    @Test
    public void testGetHeroImage2() {
        // Ensure that images are loaded.
        assertNotNull(myHero.up1);
    }
    @Test
    public void testGetHeroImage3() {
        // Ensure that images are loaded.
        assertNotNull(myHero.up2);
    }
    @Test
    public void testGetHeroImage4() {
        // Ensure that images are loaded.
        assertNotNull(myHero.down1);
    }
    @Test
    public void testGetHeroImage5() {
        // Ensure that images are loaded.
        assertNotNull(myHero.left1);
    }
    @Test
    public void testGetHeroImage6() {
        // Ensure that images are loaded.
        assertNotNull(myHero.left2);
    }
    @Test
    public void testGetHeroImage7() {
        // Ensure that images are loaded.
        assertNotNull(myHero.right1);
    }
    @Test
    public void testGetHeroImage8() {
        // Ensure that images are loaded.
        assertNotNull(myHero.right2);
    }
}