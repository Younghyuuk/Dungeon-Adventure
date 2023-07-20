package Model;

import java.io.FileWriter;
import java.io.IOException;
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
    private static final String TEXT_DUNGEON = "Resources/map/dungeon.txt";
    /**
     * The height, in rooms, of the dungeon (the Y).
     */
    private static final int DUNGEON_HEIGHT = 3;
    /**
     * The width, in rooms, of the dungeon (the X).
     */
    private static final int DUNGEON_WIDTH = 3;
    /**
     * The index of the first column in the dungeon.
     */
    private static final int FIRST_ROOM_COL = 0;
    /**
     * The index of the last column in the dungeon.
     */
    private static final int LAST_ROOM_COL = DUNGEON_WIDTH - 1;
    /**
     * The index of the first row in the dungeon.
     */
    private static final int FIRST_ROOM_ROW = 0;
    /**
     * The index of the last row in the dungeon.
     */
    private static final int LAST_ROOM_ROW = DUNGEON_HEIGHT - 1;
    /**
     * A direction vector for the columns. <br>
     * Used to traverse in DFS to adjacent cells.
     */
    private static final int[] DIRECTION_VECTOR_COLUMNS = {-1, 0, 1, 0};
    /**
     * A direction vector for the rows. <br>
     * Used to traverse in DFS to adjacent cells.
     */
    private static final int[] DIRECTION_VECTOR_ROWS = {0, 1, 0, -1};

    /**
     * Constructs the randomly generated dungeon.
     */
    public Dungeon() {
        // Set up the size of the dungeon
        myRooms = new Room[DUNGEON_HEIGHT][DUNGEON_WIDTH];
        // And the array of the visited rooms
        myVisited = new Boolean[DUNGEON_HEIGHT][DUNGEON_WIDTH];
        // Then we need to initially populate 'myVisited'
        for (int i = 0; i < DUNGEON_HEIGHT; i++) {
            Arrays.fill(myVisited[i], false); // TODO: might be wrong
        }
        // Next, we need to create every room
        createRooms();
        // Finally, we will output the dungeon to a text file to use with the GUI
        textDungeon(TEXT_DUNGEON);
    }

    public Room[][] getRooms(){
        return myRooms;
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
        int firstCol = random.nextInt(LAST_ROOM_COL - FIRST_ROOM_COL + 1) + FIRST_ROOM_COL;
        int firstRow = random.nextInt(LAST_ROOM_ROW - FIRST_ROOM_ROW + 1) + FIRST_ROOM_ROW;
        // We then want to add this to a stack as an 'XYPair'
        Stack<RowColPair> rooms = new Stack<RowColPair>();
        rooms.add(new RowColPair(firstRow, firstCol));

        // Then, we will use this to start looping and create every room with DFS
        while (!rooms.empty()) {
            // We want to grab the top pair
            RowColPair pair = rooms.pop();
            // Then we will grab the x and y
            int row = pair.row;
            int col = pair.column;
            // Then we will check if this is a valid cell
            if (!isValid(row, col)) {
                // If the room is not valid, we will skip this iteration of the loop
                // and move on to the next room in the stack
                continue;
            }
            // If we got here, the room is valid, so we will add it to our visited rooms
            myVisited[row][col] = true;
            // Next, we need to figure out what doors the first room will have
            int doorType = getRandomDoor(row, col);
            Room newRoom = new Room(randomItem, row, col, doorType);
            // Then we add that to 'myRooms'
            myRooms[row][col] = newRoom;
            // Finally, we will push all the adjacent cells into the stack
            // to continue looping through and add all the rooms.
            for (int i = 0; i < 4; i++) {
                int adjacentRow = row + DIRECTION_VECTOR_ROWS[i];
                int adjacentCol = col + DIRECTION_VECTOR_COLUMNS[i];
                rooms.push(new RowColPair(adjacentRow, adjacentCol));
            }
        }
    }

    /**
     * Helper method to use in DFS to determine if an index is valid. <br>
     * So, if we have not visited the room yet, or it is not out of bounds it is valid.
     *
     * @param theRow The row of the room we are checking.
     * @param theCol The column of the room we are checking
     * @return True if the room is valid. False if the room isn't.
     */
    private boolean isValid(final int theRow, final int theCol) {
        // Base Case: the room is out of bounds
        if (theRow < 0 || theRow >= DUNGEON_HEIGHT || theCol < 0 || theCol >= DUNGEON_WIDTH) {
            return false;
        }

        return !myVisited[theRow][theCol];
    }

    /**
     * Helper method that decides what doors the room will have based on its X and Y.
     *
     * @param theRow The row of the room we want to add doors to.
     * @param theCol The column of the room we want to add doors to.
     * @return Returns the number associated with what doors the room will have.
     */
    private int getRandomDoor(final int theRow, final int theCol) {
        // Create the random object
        Random random = new Random();
        int randomDoor;
        // All 4 - 0, North - 1, East - 2, South - 3, West - 4, NS - 5, NE - 6, NW - 7, SE - 8, SW - 9,
        // EW - 10, NSE - 11, NSW - 12, NEW - 13, SEW - 14
        // We need to check if the room is in a corner or on a wall of the dungeon
        if (theRow == 0 && theCol == 0) { // Upper left corner
            // Since we are boxed in by two walls, we can only choose from
            // an east or south door or both
            int[] directions = {2, 3, 8};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == 0 && theCol == 7) { // Upper right corner
            // We can only have West or South or both
            int[] directions = {4, 3, 9};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == 7 && theCol == 0) { // Lower left corner
            // We can only have North or East or both
            int[] directions = {1, 2, 6};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == 7 && theCol == 7) { // Lower right corner
            // We can only have North or West or both
            int[] directions = {1, 4, 7};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if ((1 <= theRow && theRow <= 6) && theCol == 0) { // Left wall
            // We can only have N, E, S, NE, NS, SE, or NSE
            int[] directions = {1, 2, 3, 5, 6, 8, 11};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if ((1 <= theRow && theRow <= 6) && theCol == 7) { // Right wall
            // We can only have N, W, S, NW, NS, SW, NSW
            int[] directions = {1, 3, 4, 7, 5, 9, 12};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == 0 && (1 <= theCol && theCol <= 6)) { // Top wall
            // We can only have W, S, E, SW, SE, EW, SEW
            int[] directions = {4, 3, 2, 9, 8, 10, 14};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == 7 && (1 <= theCol && theCol <= 6)) { // Bottom wall
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
     * Uses 'toString' to create a text representation of the dungeon
     * and then outputs that to a text file.
     *
     * @param theFileName The name of the file to output to.
     */
    protected void textDungeon(final String theFileName) {
        try {
            String dungeon = this.toString();
            FileWriter fileWriter = new FileWriter(theFileName);
            fileWriter.write(dungeon);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred when printing to output file: " + e.getMessage());
        }
    }

    /**
     * Get method to check if a given room has already been visited.
     *
     * @param theRow The row the room is stored in.
     * @param theCol The column the room is stored in.
     * @return Returns whether the room has been visited.
     */
    public boolean getIfVisited(final int theRow, final int theCol) {
        return myVisited[theRow][theCol];
    }

    /**
     * @return The String representation of the dungeon.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // First, we will loop through every row in the dungeon
        for (int row = 0; row < DUNGEON_HEIGHT; row++) {
            // Then we will create an ARRAY of StringBuilders to store every room
            StringBuilder[] roomStrings = new StringBuilder[17];
            for (int i = 0; i < 17; i++) {
                // Here we are just populating the array with StringBuilders
                roomStrings[i] = new StringBuilder();
            }

            // Next, we loop through every column in the dungeon
            for (int col = 0; col < DUNGEON_WIDTH; col++) {
                if (myRooms[row][col] != null) {
                    // We get the text representation of the current room
                    String[] roomText = myRooms[row][col].toString().split("\n");

                    // And then append every line of the current room to the StringBuilder
                    // in the current spot in the ARRAY of StringBuilders
                    for (int i = 0; i < 17; i++) {
                        roomStrings[i].append(roomText[i]).append("");
                    }
                }
            }
            // Finally, before returning, we will append each StringBuilder
            // to the main StringBuilder
            for (StringBuilder roomString : roomStrings) {
                sb.append(roomString).append("\n");
            }
        }

        return sb.toString();
    }

    public int getDungeonHeight(){
        return DUNGEON_HEIGHT;
    }
    public int getDungeonWidth() {
        return DUNGEON_WIDTH;
    }
    /**
     * Inner class to represent a row-column pair to be used in a stack for DFS.
     */
    private class RowColPair {
        /**
         * The row in the row-column pair.
         */
        private int row;
        /**
         * The column in the row-column pair.
         */
        private int column;

        /**
         * Constructs a row-column pair.
         *
         * @param theRow The index of the row in the row-column pair.
         * @param theCol The index of the column in the row-column pair.
         */
        public RowColPair(final int theRow, final int theCol) {
            row = theRow;
            column = theCol;
        }
    }
}
