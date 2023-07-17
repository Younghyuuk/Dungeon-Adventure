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
     * A 2D array that contains the doors for each room.
     */
    private int[][] myDoors;
    /**
     * Integer representation of what doors are assigned to the previous room. <br>
     * 0 - All four doors. <br>
     * 1 - A North door. <br>
     * 2 - An East door. <br>
     * 3 - A South door. <br>
     * 4 - A West door. <br>
     */
    private int myPrev;
    /**
     * The file to output the text version of the dungeon to.
     */
    private static final String TEXT_DUNGEON = "dungeon.txt";
    /**
     * The height, in rooms, of the dungeon.
     */
    private static final int DUNGEON_HEIGHT = 3;
    /**
     * The width, in rooms, of the dungeon.
     */
    private static final int DUNGEON_WIDTH = 3;
    /**
     * The index of the first room in the dungeon in the x direction.
     */
    private static final int FIRST_ROOM_X = 0;
    /**
     * The index of the last room in the dungeon in the x direction.
     */
    private static final int LAST_ROOM_X = 3;
    /**
     * The index of the first room in the dungeon in the y direction.
     */
    private static final int FIRST_ROOM_Y = 0;
    /**
     * The index of the last room in the dungeon in the y direction.
     */
    private static final int LAST_ROOM_Y = 3;

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
        // Then we pass in the random generator to our 'getRandomItem' method
        // to get a random item
        RoomItem randomItem = getRandomItem(random);
        // We start off by creating the first room
        int firstRoomX = random.nextInt(LAST_ROOM_X - FIRST_ROOM_X + 1) + FIRST_ROOM_X;
        int firstRoomY = random.nextInt(LAST_ROOM_Y - FIRST_ROOM_Y + 1) + FIRST_ROOM_Y;
        // Next, we need to figure out what doors the first room will have
        int doorType = getRandomDoor(firstRoomX, firstRoomY);
        Room firstRoom = new Room(randomItem, firstRoomX, firstRoomY, doorType);
        // Then we add that to 'myRooms'
        myRooms[firstRoomX][firstRoomY] = firstRoom;
        // We need to iterate and create each room, so we will create a loop
        int rooms = 1;
        while (rooms <= 9) {

            rooms++;
        }
    }

    private int getRandomDoor(final int theX, final int theY) {
        // Create the random object
        Random random = new Random();
        int randomDoor = 0;
        // All 4 - 0, North - 1, East - 2, South - 3, West - 4, NS - 5, NE - 6, NW - 7, SE - 8, SW - 9,
        // EW - 10, NSE - 11, NSW - 12, NEW - 13, SEW - 14
        // We need to check if the room is in a corner or on a wall of the dungeon
        if (theX == 0 && theY == 0) { // Upper left corner
            // Since we are boxed in by two walls, we can only choose from
            // an east or south door or both
            int[] directions = {2, 3, 8};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theX == 8 && theY == 0) { // Upper right corner
            // We can only have West or South or both
            int[] directions = {4, 3, 9};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theX == 0 && theY == 8) { // Lower left corner
            // We can only have North or East or both
            int[] directions = {1, 2, 6};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theX == 8 && theY == 8) { // Lower right corner
            // We can only have North or West or both
            int[] directions = {1, 4, 7};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theX == 0 && (1 <= theY && theY <= 7)) { // Left wall
            // We can only have N, E, S, NE, NS, SE, or NSE
            int[] directions = {1, 2, 3, 5, 6, 8, 11};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theX == 8 && (1 <= theY && theY <= 7)) { // Right wall
            // We can only have N, W, S, NW, NS, SW, NSW
            int[] directions = {1, 3, 4, 7, 5, 9, 12};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theY == 0 && (1 <= theX && theX <= 7)) { // Top wall
            // We can only have W, S, E, SW, SE, EW, SEW
            int[] directions = {4, 3, 2, 9, 8, 10, 14};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theY == 8 && (1 <= theX && theX <= 7)) { // Bottom wall
            // We can only have N, W, E, NW, NE, EW, NEW
            int[] directions = {1, 4, 2, 7, 6, 10, 13};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else { // Finally, if the room is not in a corner or on a wall we go here
            int[] directions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        }
        return randomDoor;
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
