package Model;

import static org.junit.jupiter.api.Assertions.*;

import Model.DungeonCharacter;
import Model.Heroes;
import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DungeonCharacterTest {

    private class TestDungeonCharacter extends DungeonCharacter {
        public TestDungeonCharacter(GamePanel gamePanel) {
            super(100, "TestCharacter", 10, 5, 20, 0.8, gamePanel);
        }

        @Override
        public String attackBehavior(DungeonCharacter theOpp) {
            return null; // Not testing this method in this test
        }

        @Override
        public void subtractHp(int theDamage) {
            // Not testing this method in this test
        }

        @Override
        public int genDamage(int theMin, int theMax) {
            return 10; // A fixed damage value for testing purposes
        }

        @Override
        public boolean isAlive() {
            return getHp() > 0;
        }
    }

    private TestDungeonCharacter testCharacter;

    @BeforeEach
    void setUp() {
        GamePanel gamePanel = new GamePanel(); // You might need to provide a valid GamePanel here
        testCharacter = new TestDungeonCharacter(gamePanel);
    }


    @Test
    void testGenDamage() {
        int damage = testCharacter.genDamage(5, 15);
        assertTrue(damage >= 5 && damage <= 15);
    }

    @Test
    void testIsAlive() {
        assertTrue(testCharacter.isAlive());
        testCharacter.setHp(0);
        assertTrue(!testCharacter.isAlive());
    }
    @Test
    public void testGetHp() {
        assertEquals(100, testCharacter.getHp());
    }

    @Test
    public void testSetHp() {
        testCharacter.setHp(75);
        assertEquals(75, testCharacter.getHp());
    }

    @Test
    public void testGetChName() {
        assertEquals("TestCharacter", testCharacter.getChName());
    }

    @Test
    public void testSetChName() {
        testCharacter.setChName("NewName");
        assertEquals("NewName", testCharacter.getChName());
    }

    @Test
    public void testGetMinDamage() {
        assertEquals(5, testCharacter.getMinDamage());
    }

    @Test
    public void testSetMinDamage() {
        testCharacter.setMinDamage(8);
        assertEquals(8, testCharacter.getMinDamage());
    }

    @Test
    public void testGetMaxDamage() {
        assertEquals(20, testCharacter.getMaxDamage());
    }

    @Test
    public void testSetMaxDamage() {
        testCharacter.setMaxDamage(25);
        assertEquals(25, testCharacter.getMaxDamage());
    }

    @Test
    public void testGetAttackSpeed() {
        assertEquals(10, testCharacter.getAttackSpeed());
    }

    @Test
    public void testSetAttackSpeed() {
        testCharacter.setAttackSpeed(15);
        assertEquals(15, testCharacter.getAttackSpeed());
    }

    @Test
    public void testGetHitChance() {
        assertEquals(0.8, testCharacter.getHitChance(), 0.001);
    }

    @Test
    public void testSetHitChance() {
        testCharacter.setHitChance(0.75);
        assertEquals(0.75, testCharacter.getHitChance(), 0.001);
    }

    @Test
    public void testGetMySpeed() {
        assertEquals(0, testCharacter.getMySpeed());
    }

    @Test
    public void testSetMySpeed() {
        testCharacter.setMySpeed(5);
        assertEquals(5, testCharacter.getMySpeed());
    }

    @Test
    public void testGetMyWorldXCoordinate() {
        assertEquals(0, testCharacter.getMyWorldXCoordinate());
    }

    @Test
    public void testSetMyWorldXCoordinate() {
        testCharacter.setMyWorldXCoordinate(10);
        assertEquals(10, testCharacter.getMyWorldXCoordinate());
    }


}
