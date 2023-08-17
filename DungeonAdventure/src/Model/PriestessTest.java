package Model;

import static org.junit.jupiter.api.Assertions.*;

import Control.Keyboard;
import View.GamePanel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PriestessTest {

    private GamePanel myGp;
    private Keyboard myKeyboard;
    private Priestess myPriestess;

    private MonsterDataBase myMon;

    @BeforeEach
    public void setUp() {
        myGp = new GamePanel();
        myKeyboard = new Keyboard(myGp);
        myPriestess = new Priestess(myGp, myKeyboard);
        myMon = new MonsterDataBase(myGp);
    }

    @Test
    public void testRegularAttack() {
        // Create a mock opponent for testing
        Monster opp = myMon.getMonster("Gremlin");
        opp.setChName("Gremlin");
        String attackResult = myPriestess.regularAttack(opp);

        assertNotNull(attackResult);
    }

    @Test
    public void testSpecialSkill() {
        // Set the priestess HP to a low value for testing
        myPriestess.setHp(50);

        // Create a mock ally for testing
        MonsterDataBase myMon = new MonsterDataBase(myGp);

        String specialSkillResult = myPriestess.specialSkill(myMon.getRandomMonster());

        assertNotNull(specialSkillResult);
        assertTrue(specialSkillResult.contains(myPriestess.getChName()));
        assertTrue(specialSkillResult.contains("heals itself"));
        assertTrue(specialSkillResult.contains(Integer.toString(myPriestess.getHp())));
    }

    @Test
    public void testGetBlockChance() {
        assertEquals(0.3, myPriestess.getBlockChance());
    }

    @Test
    public void testHpBarDrawing() {
        // Create a dummy Graphics2D object for testing
        Graphics2D graphics2D = (Graphics2D) new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();

        assertDoesNotThrow(() -> {
            myPriestess.drawHpBar(graphics2D);
        }, "Drawing the HP bar should not throw an exception");
    }

    @Test
    public void testHeroesImageLoading() {
        assertDoesNotThrow(() -> {
            myPriestess.getHeroesImage();
        }, "Loading heroes images should not throw an exception");

        assertNotNull(myPriestess.up1, "Image 'up1' should be loaded");
        assertNotNull(myPriestess.up2, "Image 'up2' should be loaded");
        assertNotNull(myPriestess.down1, "Image 'down1' should be loaded");
        assertNotNull(myPriestess.down2, "Image 'down2' should be loaded");
        assertNotNull(myPriestess.left1, "Image 'left1' should be loaded");
        assertNotNull(myPriestess.left2, "Image 'left2' should be loaded");
        assertNotNull(myPriestess.right1, "Image 'right1' should be loaded");
        assertNotNull(myPriestess.right2, "Image 'right2' should be loaded");
    }
}

