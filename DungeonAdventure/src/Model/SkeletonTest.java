package Model;

import Control.Keyboard;
import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SkeletonTest {

    private final GamePanel mockGamePanel = new GamePanel();
    private final Keyboard key = new Keyboard(mockGamePanel);
    private final MonsterDataBase md = new MonsterDataBase(mockGamePanel);
    private Monster skeleton;
    private Heroes myHero;

    @BeforeEach
    public void setUp() {
        skeleton = md.getMonster("Skeleton");
        myHero = new Thief(mockGamePanel, key);
    }
    @Test
    public void testGetMonsterImage1() {
        // Ensure that images are loaded.
        skeleton.getMonsterImage();
    }
    @Test
    public void testGetMonsterImage2() {
        // Ensure that images are loaded.
        assertNotNull(skeleton.up1);
    }
    @Test
    public void testGetMonsterImage3() {
        // Ensure that images are loaded.
        assertNotNull(skeleton.up2);
    }
    @Test
    public void testGetMonsterImage4() {
        // Ensure that images are loaded.
        assertNotNull(skeleton.down1);
    }
    @Test
    public void testGetMonsterImage5() {
        // Ensure that images are loaded.
        assertNotNull(skeleton.left1);
    }
    @Test
    public void testGetMonsterImage6() {
        // Ensure that images are loaded.
        assertNotNull(skeleton.left2);
    }
    @Test
    public void testGetMonsterImage7() {
        // Ensure that images are loaded.
        assertNotNull(skeleton.right1);
    }
    @Test
    public void testGetMonsterImage8() {
        // Ensure that images are loaded.
        assertNotNull(skeleton.right2);
    }
    @Test
    public void testNameIsSkeleton() {
        assertEquals("Skeleton", skeleton.getChName());
    }

    @Test
    public void testRegularAttack(){

    }
}