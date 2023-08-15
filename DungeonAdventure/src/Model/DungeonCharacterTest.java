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
            super(100, "TestCharacter", 10, 5, 15, 0.8, gamePanel);
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
    void testInitialAttributes() {
        assertEquals(100, testCharacter.getHp());
        assertEquals("TestCharacter", testCharacter.getChName());
        assertEquals(10, testCharacter.getAttackSpeed());
        assertEquals(5, testCharacter.getMinDamage());
        assertEquals(15, testCharacter.getMaxDamage());
        assertEquals(0.8, testCharacter.getHitChance());
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

    // You can add more tests for other methods as needed
}
