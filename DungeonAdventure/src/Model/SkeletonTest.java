package Model;

import Control.Keyboard;
import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class SkeletonTest {
    private GamePanel myGp;
    private Keyboard myKey;
    private MonsterDataBase myMon;
    private Monster mySkeleton;
    private Heroes myHero;

    @BeforeEach
    public void setup() {
        myGp = new GamePanel();
        myKey = new Keyboard(myGp);
        myMon = new MonsterDataBase(myGp);
        mySkeleton = myMon.getMonster("Skeleton");
        myHero = new Warrior(myGp, myKey);
    }

    @Test
    public void testRegularAttack() {
        String result = mySkeleton.regularAttack(myHero);
        assertNotNull(result); // Ensure that the result is not null
    }

    @Test
    public void testGetMonsterImage() {
        assertDoesNotThrow(() -> {
            Graphics2D graphics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();
            mySkeleton.getMonsterImage();
            // Ensure that the images are loaded without throwing exceptions
        });
    }
}
