package Model;

import Control.Keyboard;
import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;


import static org.junit.jupiter.api.Assertions.*;

public class MonsterTest {

    private TestMonster monster;
    private Heroes myPriestess;
    private GamePanel myGp;
    private Keyboard myKey;

    @BeforeEach
    public void setup() {
        myGp = new GamePanel();
        myKey = new Keyboard(myGp);
        monster = new TestMonster(100, 10, 5, 15, 0.8,
                0.2, 10, 20, myGp);
        myPriestess = new Priestess(myGp, myKey);
    }

    private static class TestMonster extends Monster {
        public TestMonster(int theHp, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance,
                           double theChanceHeal, int theMinHeal, int theMaxHeal, GamePanel theGamePanel) {
            super(theHp, "Test Monster", theAttackSpeed, theMinDamage, theMaxDamage, theHitChance,
                    theChanceHeal, theMinHeal, theMaxHeal, theGamePanel);
        }

        @Override
        public String regularAttack(DungeonCharacter theOpp) {
            return "Test Monster attacks";
        }

        @Override
        public void getMonsterImage() {
            // Provide a dummy implementation for testing
        }
    }


    @Test
    public void testRegularAttack() {
        String result = monster.regularAttack(myPriestess);
        assertNotNull(result); // Ensure that the result is not null
        assertEquals("Test Monster attacks", result);
    }

    @Test
    public void testHeal() {
        String healLog = monster.heal();
        assertNotNull(healLog); // Ensure that the result is not null
    }
}
