package Model;

import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FourPillarsTest {
    private GamePanel myGP;
    @BeforeEach
    public final void setUp() {
        myGP = new GamePanel();
    }
    @Test
    public final void getItemImage() {
        FourPillars fourPillars = new FourPillars(100, 100, "a", myGP);
        assertNotNull(fourPillars.getMyAbstraction());
        assertNotNull(fourPillars.getMyEncapsulation());
        assertNotNull(fourPillars.getMyInheritance());
        assertNotNull(fourPillars.getMyPolymorphism());
    }

    @Test
    public final void getCorrectCoordsAndNames() {
        String a = "a";
        String e = "e";
        String i = "i";
        String p = "p";
        int worldX = 100;
        int worldY = 100;

        FourPillars fourPillarsA = new FourPillars(worldX, worldY, a, myGP);
        FourPillars fourPillarsE = new FourPillars(worldX, worldY, e, myGP);
        FourPillars fourPillarsI = new FourPillars(worldX, worldY, i, myGP);
        FourPillars fourPillarsP = new FourPillars(worldX, worldY, p, myGP);

        assertEquals(a, fourPillarsA.getMyName());
        assertEquals(e, fourPillarsE.getMyName());
        assertEquals(i, fourPillarsI.getMyName());
        assertEquals(p, fourPillarsP.getMyName());

        assertEquals(worldX, fourPillarsE.getMyWorldXCoordinate());
        assertEquals(worldY, fourPillarsE.getMyWorldYCoordinate());
    }


    @Test
    void findImage() {
        String a = "a";
        String e = "e";
        String i = "i";
        String p = "p";
        int worldX = 100;
        int worldY = 100;

        FourPillars fourPillarsA = new FourPillars(worldX, worldY, a, myGP);
        FourPillars fourPillarsE = new FourPillars(worldX, worldY, e, myGP);
        FourPillars fourPillarsI = new FourPillars(worldX, worldY, i, myGP);
        FourPillars fourPillarsP = new FourPillars(worldX, worldY, p, myGP);

        assertEquals(fourPillarsA.getMyAbstraction(), fourPillarsA.findImage());
        assertEquals(fourPillarsE.getMyEncapsulation(), fourPillarsE.findImage());
        assertEquals(fourPillarsI.getMyInheritance(), fourPillarsI.findImage());
        assertEquals(fourPillarsP.getMyPolymorphism(), fourPillarsP.findImage());
    }
}