package Model;

import java.util.Random;

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
        // Next, we need to create every room
        createRooms(myRooms);
    }

    /**
     * Goes through the 'myRooms' 2D array and creates each room. <br>
     * Randomly generates which item will be in a room, or no items at all.
     *
     * @param theRooms A 2D array of rooms.
     */
    protected void createRooms(final Room[][] theRooms) {
        // We want to create the random generator
        Random random = new Random();
        // We then use a nested for loop for the 'myRooms' 2D array
        for (int i = 0; i < DUNGEON_HEIGHT; i++) {
            for (int j = 0; j < DUNGEON_WIDTH; j++) {
                // Then we pass in the random generator to our 'getRandomItem' method
                // to get a random item
                RoomItem randomItem = getRandomItem(random);
                // We then create a new 'Room' with that item
                Room newRoom = new Room(randomItem, i, j);
                // And finally add that to 'myRooms'
                myRooms[i][j] = newRoom;
            }
        }
    }

    /**
     * Helper method to generate the random item(s) that a room will contain.
     *
     * @param theRandom The random object to use to get a random item.
     * @return Returns the generated random 'RoomItem'.
     */
    private RoomItem getRandomItem(final Random theRandom) {
        return null;
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
