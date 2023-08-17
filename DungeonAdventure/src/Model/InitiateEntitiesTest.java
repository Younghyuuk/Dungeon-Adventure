package Model;

import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InitiateEntitiesTest {

    private GamePanel myGP;
    private InitiateEntities myIE;

    @BeforeEach
    public final void setUp() {
        myGP = new GamePanel();
        myIE = new InitiateEntities(myGP);
    }

    @Test
    public final void testGetMyFourPillarsArray() {
        List<FourPillars> fourPillarsList = myIE.getMyFourPillarsArray();
        assertNotNull(fourPillarsList);
    }

    @Test
    public final void testGetMyMonsterArray() {
        List<Monster> monsterList = myIE.getMyMonsterArray();
        assertNotNull(monsterList);
    }

    @Test
    public final void testGetMyItemArray() {
        List<Item> itemList = myIE.getMyItemArray();
        assertNotNull(itemList);
    }
    @Test
    void createMonster() {
    }

    @Test
    void createItems() {
    }
}