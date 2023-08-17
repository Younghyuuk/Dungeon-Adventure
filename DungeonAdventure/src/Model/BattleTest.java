package Model;

import Control.Keyboard;
import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {

    private Battle myBattle;
    private Heroes myHero;
    private Monster myMonster;
    private GamePanel myGp;
    private Keyboard myKey;

    @BeforeEach
    public void setup() {
        myGp = new GamePanel();
        myKey = new Keyboard(myGp);
        myHero = new Priestess(myGp, myKey);
        myMonster = new MonsterDataBase(myGp).getMonster("Gremlin");
        myBattle = new Battle(myHero, myMonster);
    }

    @Test
    public void testStartBattle() {
        myBattle.startBattle();
        assertTrue(myHero.isAlive() || myMonster.isAlive()); // At least one of them should be alive
    }

    @Test
    public void testHeroAttack() {
        String attackLog = myBattle.heroAttack();
        assertNotNull(attackLog);
        assertFalse(attackLog.isEmpty());
    }

    @Test
    public void testMonsterAttack() {
        String attackLog = myBattle.monsterAttack();
        assertNotNull(attackLog);
        assertFalse(attackLog.isEmpty());
    }

    @Test
    public void testGetMyBattleLog() {
        String[] battleLog = myBattle.getMyBattleLog();
        assertNotNull(battleLog);
        assertEquals(100, battleLog.length);
    }

    @Test
    public void testAddToLog() {
        String message = "Test Message";
        myBattle.addToLog(message);
        String[] battleLog = myBattle.getMyBattleLog();

        assertNotNull(battleLog[0]);
    }
}
