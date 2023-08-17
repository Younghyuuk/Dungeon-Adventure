package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DungeonTest {
    private static final String TEXT_DUNGEON_NAME = "test_dungeon.txt";
    private Dungeon myDungeon;

    @BeforeEach
    public final void setUp() {
        myDungeon = new Dungeon(TEXT_DUNGEON_NAME);
    }

    @Test
    public final void testDungeonCreation() {
        assertNotNull(myDungeon);
        assertNotNull(myDungeon.getRooms());
        assertNotNull(myDungeon.getVisitedRooms());
        assertNotNull(myDungeon.getDoors());
        assertNotNull(myDungeon.getTextFile());
        assertEquals(7, myDungeon.getDungeonHeight());
        assertEquals(7, myDungeon.getDungeonWidth());
    }

    @Test
    public final void createRooms() {
        boolean[][] visitedRooms = myDungeon.getVisitedRooms();

        for (int i = 0; i < myDungeon.getDungeonHeight(); i++) {
            for (int j = 0; j < myDungeon.getDungeonWidth(); j++) {
                assertTrue(visitedRooms[i][j]);
            }
        }
    }

    @Test
    public final void connectivity() {
        boolean[][] visitedRooms = myDungeon.getVisitedRooms();

        for (int i = 0; i < myDungeon.getDungeonHeight(); i++) {
            for (int j = 0; j < myDungeon.getDungeonWidth(); j++) {
                if (visitedRooms[i][j]) {
                    assertTrue(myDungeon.getIfVisited(i, j));
                }
            }
        }
    }

    @Test
    public final void validRoomCoordinates() {
        for (int i = 0; i < myDungeon.getDungeonHeight(); i++) {
            for (int j = 0; j < myDungeon.getDungeonWidth(); j++) {
                myDungeon.setMyVisited();
                assertTrue(myDungeon.isValid(i, j));
            }
        }
    }

    @Test
    public final void isValid() {
        assertFalse(myDungeon.isValid(-1, 0));
        assertFalse(myDungeon.isValid(0, -1));
        assertFalse(myDungeon.isValid(myDungeon.getDungeonHeight(), 0));
        assertFalse(myDungeon.isValid(0, myDungeon.getDungeonWidth()));
    }

    @Test
    public final void testRoomDimensions() {
        assertEquals(myDungeon.getDungeonHeight(), myDungeon.getRooms().length);
        assertEquals(myDungeon.getDungeonWidth(), myDungeon.getRooms()[0].length);
    }

    @Test
    public final void setDoors() {
        int[][] testDoors = new int[myDungeon.getDungeonHeight()][myDungeon.getDungeonWidth()];
        for (int i = 0; i < myDungeon.getDungeonHeight(); i++) {
            Arrays.fill(testDoors[i], -1);
        }

        int[][] dungeonDoorsBefore = myDungeon.getDoors();
        myDungeon.setDoors(testDoors);

        assertNotEquals(dungeonDoorsBefore, myDungeon.getDoors());
        assertEquals(myDungeon.getDoors(), testDoors);
    }

    @Test
    public final void textDungeon() {
        assertTrue(myDungeon.getTextFile().length() > 0);
    }

    @Test
    public final void differentDungeons() {
        String initialText = myDungeon.getTextFile();
        myDungeon = new Dungeon(TEXT_DUNGEON_NAME);
        String nextText = myDungeon.getTextFile();
        assertNotEquals(initialText, nextText);
    }
}