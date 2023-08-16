package Model;

import Control.Keyboard;
import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void regularAttack() {
    }

    @Test
    void specialSkill() {
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