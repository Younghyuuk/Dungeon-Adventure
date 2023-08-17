package Model;

import Control.Keyboard;
import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class OgreTest {

    private Monster myOgre;
    private MonsterDataBase myMD;
    private Heroes myHero;
    private GamePanel myGp;
    private Keyboard myKey;

    @BeforeEach
    public void setup() {
        myGp = new GamePanel();
        myKey = new Keyboard(myGp);
        myMD = new MonsterDataBase(myGp);
        myOgre = myMD.getMonster("Ogre");
        myHero = new Priestess(myGp, myKey);
    }


    @Test
    public void testRegularAttack() {
        String result = myOgre.regularAttack(myHero);
        assertNotNull(result); // Ensure that the result is not null
    }

    @Test
    public void testHeal() {
        String healLog = myOgre.heal();
        assertNotNull(healLog); // Ensure that the result is not null
    }

    @Test
    public void testGetMonsterImage() {
        assertDoesNotThrow(() -> {
            Graphics2D graphics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();
            myOgre.getMonsterImage();
            // Ensure that the images are loaded without throwing exceptions
        });
    }
}