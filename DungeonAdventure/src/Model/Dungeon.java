package Model;

/**
 * This class represents the randomly generated dungeon.
 */
public class Dungeon {
    /**
     * A 2D array of all the rooms in the dungeon.
     */
    private Room[][] myRooms;
    /**
     * The file to output the text version of the dungeon to.
     */
    private static final String TEXT_DUNGEON = "dungeon.txt";
    /**
     * The height, in rooms, of the dungeon.
     */
    private static final int DUNGEON_HEIGHT = 8;
    /**
     * The width, in rooms, of the dungeon.
     */
    private static final int DUNGEON_WIDTH = 8;

    /**
     * Constructs the randomly generated dungeon.
     */
    public Dungeon() {
        // Set up the size of the dungeon
        myRooms = new Room[DUNGEON_HEIGHT][DUNGEON_WIDTH];
    }

    /**
     * Goes through the 'myRooms' 2D array and creates each room. <br>
     * Randomly generates which item will be in a room, or no items at all.
     *
     * @param theRooms A 2D array of rooms.
     */
    protected void createRooms(final Room[][] theRooms) {

    }

    /**
     * Converts the 2D array into text to be used in 'toString()' and
     * output to a text file so that the Dungeon can be drawn on the GUI.
     *
     * @param theRooms A 2D array of rooms.
     */
    protected void textDungeon(final Room theRooms) {

    }

    /**
     * @return The String representation of the dungeon.
     */
    @Override
    public String toString() {
        return null;
    }
}
