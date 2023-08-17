package Model;

import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PitTest {
    private GamePanel myGP;

    @BeforeEach
    public final void setUp() {
        myGP = new GamePanel();
    }

    @Test
    public final void testPitConstruction() {
        int worldX = 100;
        int worldY = 200;
        Pit pit = new Pit(worldX, worldY, myGP);

        assertEquals(worldX, pit.getMyWorldXCoordinate());
        assertEquals(worldY, pit.getMyWorldYCoordinate());
        assertNotNull(pit.getMySolidArea());
        assertNotNull(pit.getPitImage());
    }

    @Test
    public final void testSetPitDamage() {
        Pit pit = new Pit(0, 0, myGP);
        pit.setPitDamage();
        int damage = pit.getPitDamage();
        assertTrue(damage >= pit.getMinDamage() && damage <= pit.getMaxDamage());
    }

    @Test
    public void testGetPitDamage() {
        Pit pit = new Pit(0, 0, myGP);
        int damage = pit.getPitDamage();
        assertTrue(damage >= pit.getMinDamage() && damage <= pit.getMaxDamage());
    }

    @Test
    public void testGetItemImage() {
        Pit pit = new Pit(0, 0, myGP);
        assertNotNull(pit.getPitImage());
    }
}