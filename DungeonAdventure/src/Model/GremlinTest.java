package Model;

import Control.Keyboard;
import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class GremlinTest {

    private Monster myGremlin;
    private MonsterDataBase myMD;
    private Heroes myHero;
    private GamePanel myGp;
    private Keyboard myKey;

    @BeforeEach
    public void setup() {
        myGp = new GamePanel();
        myKey = new Keyboard(myGp);
        myMD = new MonsterDataBase(myGp);
        myGremlin = myMD.getMonster("Gremlin");
        myHero = new Priestess(myGp, myKey);
    }


    @Test
    public void testRegularAttack() {
        String result = myGremlin.regularAttack(myHero);
        assertNotNull(result); // Ensure that the result is not null
    }

    @Test
    public void testHeal() {
        String healLog = myGremlin.heal();
        assertNotNull(healLog); // Ensure that the result is not null
    }

    @Test
    public void testGetMonsterImage() {
        assertDoesNotThrow(() -> {
            Graphics2D graphics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();
            myGremlin.getMonsterImage();
            // Ensure that the images are loaded without throwing exceptions
        });
    }
}