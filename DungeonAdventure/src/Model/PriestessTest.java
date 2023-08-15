package Model;

import static org.junit.jupiter.api.Assertions.*;

import Control.Keyboard;
import View.GamePanel;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

    public class PriestessTest {

        private GamePanel gamePanel;
        private Keyboard keyboard;
        private Priestess priestess;

        @BeforeEach
        public void setUp() {
            gamePanel = new GamePanel();
            keyboard = new Keyboard(gamePanel);
            priestess = new TestPriestess(gamePanel, keyboard);
        }

        @Test
        public void testRegularAttack() {
            // Create a mock opponent for testing
            DungeonCharacter opponent = new DungeonCharacterMock(100, "opponent", 2,
                    0, 0, 0.1, gamePanel);

            String attackResult = priestess.regularAttack(opponent);

            assertNotNull(attackResult);
            assertTrue(attackResult.contains(priestess.getChName()));
//            assertTrue(attackResult.contains(opponent.getChName()));
            assertTrue(attackResult.contains("damage"));
        }

        @Test
        public void testSpecialSkill() {
            // Set the priestess HP to a low value for testing
            priestess.setHp(50);

            // Create a mock ally for testing
            DungeonCharacter ally = new DungeonCharacterMock(100, "Mock", 2,
                    0, 0, 0.1, gamePanel);

            String specialSkillResult = priestess.specialSkill(ally);

            assertNotNull(specialSkillResult);
            assertTrue(specialSkillResult.contains(priestess.getChName()));
            assertTrue(specialSkillResult.contains("heals itself"));
            assertTrue(specialSkillResult.contains(Integer.toString(priestess.getHp())));
        }

        @Test
        public void testGetBlockChance() {
            assertEquals(0.3, priestess.getBlockChance());
        }

        // ... Add more test cases for other methods ...

        private static class DungeonCharacterMock extends DungeonCharacter {
            /**
             * The constructor of DungeonCharacter that initializes the hp, name, attack speed,
             * minimum damage, maximum damage, and the hitchance of the character.
             *
             * @param theHp          amount of hp for character.
             * @param theChName      name of the character.
             * @param theAttackSpeed the attack speed character has.
             * @param theMinDamage   minimum amount of damage they can do.
             * @param theMaxDamage   maximum amount of damage they can do.
             * @param theHitChance   chance the character has to landing attack.
             * @param theGamePanel
             */
            protected DungeonCharacterMock(int theHp, String theChName, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance, GamePanel theGamePanel) {
                super(theHp, theChName, theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theGamePanel);
            }
            // Implement necessary methods for testing
        }

        private static class TestPriestess extends Priestess {
            public TestPriestess(GamePanel gamePanel, Keyboard keyboard) {
                super(gamePanel, keyboard);
            }

            // Override methods if needed for testing
        }
    }

