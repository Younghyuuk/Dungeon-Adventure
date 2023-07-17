package Model;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * This class represents the randomly generated dungeon.
 */
public class Dungeon {
    /**
     * A 2D array of all the rooms in the dungeon.
     */
    private Room[][] myRooms;
    /**
     * A 2D array of all the visited rooms in the dungeon. <br>
     * A cell in the array that is true means that we have visited that room.
     */
    private Boolean[][] myVisited;
    /**
     * A 2D array that contains the doors for each room.
     */
    private int[][] myDoors;
    /**
     * The file to output the text version of the dungeon to.
     */
    private static final String TEXT_DUNGEON = "dungeon.txt";
    /**
     * The height, in rooms, of the dungeon (the Y).
     */
    private static final int DUNGEON_HEIGHT = 3;
    /**
     * The width, in rooms, of the dungeon (the X).
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
     * A direction vector for the X direction (the columns). <br>
     * Used to traverse in DFS to adjacent cells.
     */
    private static final int[] DIRECTION_VECTOR_X = {-1, 0, 1, 0};
    /**
     * A direction vector for the Y direction (the rows). <br>
     * Used to traverse in DFS to adjacent cells.
     */
    private static final int[] DIRECTION_VECTOR_Y = {0, 1, 0, -1};

    /**
     * Constructs the randomly generated dungeon.
     */
    public Dungeon() {
        // Set up the size of the dungeon
        myRooms = new Room[DUNGEON_WIDTH][DUNGEON_HEIGHT];
        // And the array of the visited rooms
        myVisited = new Boolean[DUNGEON_WIDTH][DUNGEON_HEIGHT];
        // Then we need to initially populate 'myVisited'
        for (int i = 0; i < DUNGEON_WIDTH; i++) {
            Arrays.fill(myVisited[i], false); // TODO: might be wrong
        }
        // Next, we need to create every room
        createRooms();
    }

    /**
     * Goes through the 'myRooms' 2D array and creates each room. <br>
     * Randomly generates which item will be in a room, or no items at all.
     */
    protected void createRooms() {
        // We want to create the random generator
        Random random = new Random();
        // Then we pass in the random generator to our 'getRandomItem' method
        // to get a random item
        RoomItem randomItem = getRandomItem(random);
        // We start off by creating the first room
        int firstX = random.nextInt(LAST_ROOM_X - FIRST_ROOM_X + 1) + FIRST_ROOM_X;
        int firstY = random.nextInt(LAST_ROOM_Y - FIRST_ROOM_Y + 1) + FIRST_ROOM_Y;
        // We then want to add this to a stack as an 'XYPair'
        Stack<XYPair> rooms = new Stack<XYPair>();
        rooms.add(new XYPair(firstX, firstY));

        // Then, we will use this to start looping and create every room with DFS
        while (!rooms.empty()) {
            // We want to grab the top pair
            XYPair pair = rooms.pop();
            // Then we will grab the x and y
            int x = pair.x;
            int y = pair.y;
            // Then we will check if this is a valid cell
            if (!isValid(x, y)) {
                // If the room is not valid, we will skip this iteration of the loop
                // and move on to the next room in the stack
                continue;
            }
            // If we got here, the room is valid, so we will add it to our visited rooms
            myVisited[x][y] = true;
            // Next, we need to figure out what doors the first room will have
            int doorType = getRandomDoor(firstX, firstY);
            Room newRoom = new Room(randomItem, firstX, firstY, doorType);
            // Then we add that to 'myRooms'
            myRooms[firstX][firstY] = newRoom;
            // Finally, we will push all the adjacent cells into the stack
            // to continue looping through and add all the rooms.
            for (int i = 0; i < 4; i++) {
                int adjacentX = x + DIRECTION_VECTOR_X[i];
                int adjacentY = y + DIRECTION_VECTOR_Y[i];
                rooms.push(new XYPair(adjacentX, adjacentY));
            }
        }
    }

    /**
     * Helper method to use in DFS to determine if an index is valid. <br>
     * So, if we have not visited the room yet, or it is not out of bounds it is valid.
     *
     * @param theX The X value of the room we are checking.
     * @param theY The Y value of the room we are checking
     * @return True if the room is valid. False if the room isn't.
     */
    private boolean isValid(final int theX, final int theY) {
        boolean valid = true;
        // Base Case: the room is out of bounds
        if (theX < 0 || theX > DUNGEON_WIDTH || theY < 0 || theY > DUNGEON_HEIGHT) {
            valid = false;
        }

        // Next, we want to check if we have already created this room
        if (myVisited[theX][theY]) {
            valid = false;
        }

        return valid;
    }

    /**
     * Helper method that decides what doors the room will have based on its X and Y.
     *
     * @param theX The X value of the room we want to add doors to.
     * @param theY The Y value of the room we want to add doors to.
     * @return Returns the number associated with what doors the room will have.
     */
    private int getRandomDoor(final int theX, final int theY) {
        // Create the random object
        Random random = new Random();
        int randomDoor;
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
        } else { // Finally, if the room is not in a corner or a wall we go here
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

    /**
     * Inner class to represent an xy pair to be used in a stack for DFS.
     */
    private class XYPair {
        /**
         * The x value in the xy pair.
         */
        private int x;
        /**
         * The y value in the xy pair.
         */
        private int y;

        /**
         * Constructs an xy pair.
         *
         * @param theX The X value for the xy pair.
         * @param theY The Y value for the xy pair.
         */
        public XYPair(final int theX, final int theY) {
            x = theX;
            y = theY;
        }
    }
}
