package Model;

import static org.junit.jupiter.api.Assertions.*;

import Control.Keyboard;
import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class ThiefTest {
    private GamePanel myGp;
    Keyboard myKey;
    private Thief myThief;
    //        private DungeonCharacter opponent;
    private MonsterDataBase myOpp;

    @BeforeEach
    public void setup() {
        myGp = new GamePanel();
        myKey = new Keyboard(myGp);
        myThief = new Thief(myGp, myKey);
        myOpp = new MonsterDataBase(myGp);
    }

    @Test
    public void testRegularAttack() {
        String result = myThief.regularAttack(myOpp.getRandomMonster());
        assertNotNull(result); // Ensure that the result is not null
        assertTrue(result.contains("hits") || result.contains("misses horribly"),
                "Unexpected regular attack result: " + result);
    }

    @Test
    public void testSpecialSkill() {
        String result = myThief.specialSkill(myOpp.getRandomMonster());
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
            myThief.drawHpBar(graphics2D);
        }, "Drawing the HP bar should not throw an exception");
    }

    @Test
    public void testHeroesImageLoading() {
        assertDoesNotThrow(() -> {
            myThief.getHeroesImage();
        }, "Loading heroes images should not throw an exception");

        assertNotNull(myThief.up1, "Image 'up1' should be loaded");
        assertNotNull(myThief.up2, "Image 'up2' should be loaded");
        assertNotNull(myThief.down1, "Image 'down1' should be loaded");
        assertNotNull(myThief.down2, "Image 'down2' should be loaded");
        assertNotNull(myThief.left1, "Image 'left1' should be loaded");
        assertNotNull(myThief.left2, "Image 'left2' should be loaded");
        assertNotNull(myThief.right1, "Image 'right1' should be loaded");
        assertNotNull(myThief.right2, "Image 'right2' should be loaded");

    }
}