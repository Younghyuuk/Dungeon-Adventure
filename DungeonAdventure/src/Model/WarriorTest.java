package Model;

import Control.Keyboard;
import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class WarriorTest {
    GamePanel myGp;
    Keyboard myKey = new Keyboard(myGp);
    private Warrior myWarrior;
    private MonsterDataBase myOpp;

    @BeforeEach
    public void setup() {
        myGp = new GamePanel();
        myKey = new Keyboard(myGp);
        myWarrior = new Warrior(myGp, myKey);
        myOpp = new MonsterDataBase(myGp);
    }


    @Test
    public void testRegularAttack() {
        String result = myWarrior.regularAttack(myOpp.getRandomMonster());
        assertNotNull(result); // Ensure that the result is not null
        assertTrue(result.contains("hits") || result.contains("misses horribly"),
                "Unexpected regular attack result: " + result);

    }

    @Test
    public void testSpecialSkill() {
        String result = myWarrior.specialSkill(myOpp.getRandomMonster());
        assertNotNull(result); // Ensure that the result is not null
    }

    @Test
    public void testHpBarDrawing() {
        // Create a dummy Graphics2D object for testing
        Graphics2D graphics2D = (Graphics2D) new BufferedImage(1, 1,
                BufferedImage.TYPE_INT_ARGB).getGraphics();

        assertDoesNotThrow(() -> {
            myWarrior.drawHpBar(graphics2D);
        }, "Drawing the HP bar should not throw an exception");
    }

    @Test
    public void testHeroesImageLoading() {
        assertDoesNotThrow(() -> {
            myWarrior.getHeroesImage();
        }, "Loading heroes images should not throw an exception");

        assertNotNull(myWarrior.up1, "Image 'up1' should be loaded");
        assertNotNull(myWarrior.up2, "Image 'up2' should be loaded");
        assertNotNull(myWarrior.down1, "Image 'down1' should be loaded");
        assertNotNull(myWarrior.down2, "Image 'down2' should be loaded");
        assertNotNull(myWarrior.left1, "Image 'left1' should be loaded");
        assertNotNull(myWarrior.left2, "Image 'left2' should be loaded");
        assertNotNull(myWarrior.right1, "Image 'right1' should be loaded");
        assertNotNull(myWarrior.right2, "Image 'right2' should be loaded");
    }
}
