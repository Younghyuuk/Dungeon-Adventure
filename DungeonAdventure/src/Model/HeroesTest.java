package Model;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

import Control.Keyboard;
import org.junit.jupiter.api.Test;

import java.awt.*;
import View.GamePanel;
public class HeroesTest {

    private static class TestHeroes extends Heroes {
        public TestHeroes(GamePanel gamePanel, Keyboard keyboard) {
            super(100, "Test Hero", 10, 5, 15, 0.8, 0.2, gamePanel, keyboard);
        }

        @Override
        public void drawHpBar(Graphics2D theGraphics) {
            // Provide a dummy implementation for testing
        }

        @Override
        public String regularAttack(DungeonCharacter theOpp) {
            // Provide a dummy implementation for testing
            return null;
        }

        @Override
        public String specialSkill(DungeonCharacter theTarget) {
            // Provide a dummy implementation for testing
            return null;
        }

        @Override
        public void getHeroesImage() {
            // Provide a dummy implementation for testing
        }
    }

    @Test
    public void testBlockChance() {
        GamePanel gamePanel = new GamePanel();
        Keyboard keyboard = new Keyboard(gamePanel);
        Heroes hero = new TestHeroes(gamePanel, keyboard);

        double expectedBlockChance = 0.2;
        double actualBlockChance = hero.getBlockChance();

        assertEquals(expectedBlockChance, actualBlockChance, 0.001);
    }

    @Test
    public void testToString() {
        GamePanel gamePanel = new GamePanel();
        Keyboard keyboard = new Keyboard(gamePanel);
        Heroes hero = new TestHeroes(gamePanel, keyboard);

        String expectedToString = "Name: Test Hero\n" +
                "Hit Points: 100\n" +
                "Min Damage: 5\n" +
                "Max Damage: 15\n" +
                "Attack Speed: 10\n" +
                "Chance to Hit: 0.8\n" +
                "Chance to Block: 0.2\n";

        assertEquals(expectedToString, hero.toString());
    }
}
