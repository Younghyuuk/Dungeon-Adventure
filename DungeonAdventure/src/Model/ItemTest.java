package Model;

import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private MockItem myMockItem;
    private GamePanel myGP;
    @BeforeEach
    public final void setUp() {
        myGP = new GamePanel();
        myMockItem = new MockItem(myGP, 100, 100);
    }

    @Test
    public final void getMyWorldXCoordinate() {
        assertEquals(100, myMockItem.getMyWorldXCoordinate());
    }

    @Test
    public final void getMyWorldYCoordinate() {
        assertEquals(100, myMockItem.getMyWorldYCoordinate());
    }

    @Test
    public final void getMySolidArea() {
        assertEquals(0, myMockItem.getMySolidArea().x);
        assertEquals(0, myMockItem.getMySolidArea().y);
        assertEquals(myGP.getSpriteSize(), myMockItem.getMySolidArea().width);
        assertEquals(myGP.getSpriteSize(), myMockItem.getMySolidArea().height);
    }

    @Test
    public final void setMyWorldXCoordinate() {
        int newX = 50;
        int newY = 50;
        myMockItem.setMyWorldXCoordinate(newX);
        myMockItem.setMyWorldYCoordinate(newY);

        assertEquals(newX, myMockItem.getMyWorldXCoordinate());
        assertEquals(newY, myMockItem.getMyWorldYCoordinate());
    }

    @Test
    void setMyWorldYCoordinate() {
    }

    @Test
    public final void testFound() {
        assertFalse(myMockItem.getFound());
        myMockItem.setFound(true);
        assertTrue(myMockItem.getFound());
    }

    @Test
    void setFound() {
    }

    @Test
    void resetSolidArea() {
    }

    class MockItem extends Item {
        public MockItem(final GamePanel theGP, final int theWorldX, final int theWorldY) {
            super(theGP, theWorldX, theWorldY);
        }

        @Override
        public void getItemImage() {}
    }
}