package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * This class creates the randomly generated Dungeon.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public class Dungeon implements Serializable {
    private static final long serialversionUID = 12345L;
    /**
     * The height, in rooms, of the dungeon (the Y).
     */
    private final int DUNGEON_HEIGHT = 7;
    /**
     * The width, in rooms, of the dungeon (the X).
     */
    private final int DUNGEON_WIDTH = 7;
    /**
     * The index of the first column in the dungeon.
     */
    private final int FIRST_ROOM_COL = 0;
    /**
     * The index of the last column in the dungeon.
     */
    private final int LAST_ROOM_COL = DUNGEON_WIDTH - 1;
    /**
     * The index of the first row in the dungeon.
     */
    private final int FIRST_ROOM_ROW = 0;
    /**
     * The index of the last row in the dungeon.
     */
    private final int LAST_ROOM_ROW = DUNGEON_HEIGHT - 1;
    /**
     * A direction vector for the columns. <br>
     * Used to traverse in DFS to adjacent cells.
     */
    private final int[] DIRECTION_VECTOR_COLUMNS = {-1, 0, 1, 0};
    /**
     * A direction vector for the rows. <br>
     * Used to traverse in DFS to adjacent cells.
     */
    private final int[] DIRECTION_VECTOR_ROWS = {0, 1, 0, -1};
    /**
     * Integer value representing the direction we have come from (west) in the DFS.
     */
    private static final int WEST = 0;
    /**
     * Integer value representing the direction we have come from (south) in the DFS.
     */
    private static final int SOUTH = 1;
    /**
     * Integer value representing the direction we have come from (east) in the DFS.
     */
    private static final int EAST = 2;
    /**
     * Integer value representing the direction we have come from (north) in the DFS.
     */
    private static final int NORTH = 3;
    /**
     * Integer value indicating doors in all 4 directions.
     */
    private static final int NESW = 0;
    /**
     * Integer value indicating doors in the north direction.
     */
    private static final int N = 1;
    /**
     * Integer value indicating doors in the east direction.
     */
    private static final int E = 2;
    /**
     * Integer value indicating doors in the south direction.
     */
    private static final int S = 3;
    /**
     * Integer value indicating doors in the west direction.
     */
    private static final int W = 4;
    /**
     * Integer value indicating doors in the north & south.
     */
    private static final int NS = 5;
    /**
     * Integer value indicating doors in the north & east.
     */
    private static final int NE = 6;
    /**
     * Integer value indicating doors in the north & west.
     */
    private static final int NW = 7;
    /**
     * Integer value indicating doors in the south & east.
     */
    private static final int SE = 8;
    /**
     * Integer value indicating doors in the east & west.
     */
    private static final int EW = 9;
    /**
     * Integer value indicating doors in the south & west.
     */
    private static final int SW = 10;
    /**
     * Integer value indicating doors in the north, south, and east.
     */
    private static final int NSE = 11;
    /**
     * Integer value indicating doors in the north, south, and west.
     */
    private static final int NSW = 12;
    /**
     * Integer value indicating doors in the north, east, and west.
     */
    private static final int NEW = 13;
    /**
     * Integer value indicating doors in the south, east, and west.
     */
    private static final int SEW = 14;
    /**
     * A 2D array that contains the doors for each room.
     */
    private int[][] myDoors;
    /**
     * A 2D array of all the rooms in the dungeon.
     */
    private final Room[][] myRooms;
    /**
     * A 2D array of all the visited rooms in the dungeon. <br>
     * A cell in the array that is true means that we have visited that room.
     */
    private final boolean[][] myVisited;
    /**
     * The text file to save for the dungeon.
     */
    private String myTextFile;

    /**
     * Constructs the randomly generated dungeon.
     */
    public Dungeon(final String theFileName) {
        // Set up the size of the dungeon
        myRooms = new Room[DUNGEON_HEIGHT][DUNGEON_WIDTH];
        // And the array of the visited rooms
        myVisited = new boolean[DUNGEON_HEIGHT][DUNGEON_WIDTH];
        // Then we need to initially populate 'myVisited'
        setMyVisited();
        // Then we need to populate the array of door types for each room
        myDoors = new int[DUNGEON_HEIGHT][DUNGEON_WIDTH];
        // And we need to populate it with a value that can't be a door type
        setMyDoors();
        myTextFile = "";
        // Next, we need to create every room
        createRooms();
        // Finally, we will output the dungeon to a text file to use with the GUI
        textDungeon(theFileName);
    }

    /**
     * Goes through the 'myRooms' 2D array and creates each room. <br>
     * Randomly generates which item will be in a room, or no items at all.
     */
    protected void createRooms() {
        // We want to create the random generator
        Random random = new Random();
        // We start off by creating the first room
        int firstCol = random.nextInt(LAST_ROOM_COL - FIRST_ROOM_COL + 1) + FIRST_ROOM_COL;
        int firstRow = random.nextInt(LAST_ROOM_ROW - FIRST_ROOM_ROW + 1) + FIRST_ROOM_ROW;
        // We then want to add this to a stack as an 'XYPair'
        Stack<RowColPair> rooms = new Stack<>();
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
            Room newRoom = new Room(doorType);
            // Then we add that to 'myRooms'
            myRooms[row][col] = newRoom;
            // Finally, we will push all the adjacent cells into the stack
            // to continue looping through and add all the rooms.
            for (int direction = 0; direction < 4; direction++) {
                int adjacentRow = row + DIRECTION_VECTOR_ROWS[direction];
                int adjacentCol = col + DIRECTION_VECTOR_COLUMNS[direction];
                rooms.push(new RowColPair(adjacentRow, adjacentCol));
            }
        }

        // After creating every room, we then need to go back and connect the rooms
        connectRooms(firstRow, firstCol);
    }

    /**
     * Helper method to use in DFS to determine if an index is valid. <br>
     * So, if we have not visited the room yet, or it is not out of bounds it is valid.
     *
     * @param theRow The row of the room we are checking.
     * @param theCol The column of the room we are checking
     * @return True if the room is valid. False if the room isn't.
     */
    protected boolean isValid(final int theRow, final int theCol) {
        // Base Case: the room is out of bounds
        if (theRow < 0 || theRow >= DUNGEON_HEIGHT || theCol < 0 || theCol >= DUNGEON_WIDTH) {
            return false;
        }

        return !myVisited[theRow][theCol];
    }

    /**
     * Helper method used by 'createRooms' to connect the doors in all the rooms.
     *
     * @param theFirstRow The row of the starting room used in the DFS by 'createRooms'.
     * @param theFirstCol The column of the starting room used in the DFS by 'createRooms'.
     */
    private void connectRooms(final int theFirstRow, final int theFirstCol) {
        // Since we need to go back and re-check every room, we will perform another DFS
        Stack<RowColPair> rooms = new Stack<>();
        // And since we are re-checking every room, we will reset myVisited
        setMyVisited();
        // And mark this first room as visited
        myVisited[theFirstRow][theFirstCol] = true;
        // Then we will add the first room to the stack
        rooms.add(new RowColPair(theFirstRow, theFirstCol));
        // And finally, we enter the DFS loop
        while (!rooms.empty()) {
            // We will grab the top pair
            RowColPair pair = rooms.pop();
            // Then we grab the row and column
            int row = pair.row;
            int col = pair.column;
            // Next, we grab the popped room's door type
            int doorType = myDoors[row][col];

            // Now, we loop through the rooms
            for (int direction = 0; direction < 4; direction++) {
                int adjRow = row + DIRECTION_VECTOR_ROWS[direction];
                int adjCol = col + DIRECTION_VECTOR_COLUMNS[direction];
                // Since we are checking adjacent rooms door types, we need to
                // see if the adjacent room is valid, and it hasn't been visited
                if (isValid(adjRow, adjCol) && myRooms[adjRow][adjCol] != null && !myVisited[adjRow][adjCol]) {
                    // We grab the adjacent room's door type
                    int adjDoorType = myDoors[adjRow][adjCol];
                    // Now, we will check if the rooms are connected by a door
                    if (isConnected(doorType, adjDoorType, direction)) {
                        int oppositeDirection = getOppositeDirection(direction);
                        // If they are connected, we will add doors to both rooms
                        myRooms[row][col].addDoor(direction);
                        myRooms[adjRow][adjCol].addDoor(oppositeDirection);
                        // We then need to change the door type for both rooms to reflect
                        // the addition of a new door
                        changeDoorType(row, col, direction, doorType); // main room
                        changeDoorType(adjRow, adjCol, oppositeDirection, adjDoorType); // adj room
                        // Finally, we will mark it as visited and add it to the stack
                        myVisited[adjRow][adjCol] = true;
                        rooms.add(new RowColPair(adjRow, adjCol));
                    }
                }
            }
        }
    }

    /**
     * Helper method to initialize (or reset) 'myVisited'.
     */
    protected void setMyVisited() {
        for (int i = 0; i < DUNGEON_HEIGHT; i++) {
            Arrays.fill(myVisited[i], false);
        }
    }


    /**
     * Helper method to initialize 'myDoors'.
     */
    private void setMyDoors() {
        for (int i = 0; i < DUNGEON_HEIGHT; i++) {
            Arrays.fill(myDoors[i], -N);
        }
    }

    private void setMyRooms() {
        for (int i = 0; i < DUNGEON_HEIGHT; i++) {
            Arrays.fill(myRooms[i], null);
        }
    }

    public void setDoors(int[][] theDoors) {
        myDoors = theDoors;
    }


    /**
     * Helper method used by 'connectRooms' to check if two rooms are connected or not.
     *
     * @param theDoorType       The door type of the main room.
     * @param theAdjDoorType    The door type of the adjacent room.
     * @param theDirection      The direction used to go from the main room to the adj room.
     * @return Returns true if they are connected, false otherwise.
     */
    private boolean isConnected(final int theDoorType, final int theAdjDoorType, final int theDirection) {
        boolean connected = false;
        // We have the door types for both rooms, so we can simply check them against each other
        if (theDirection == WEST && (theDoorType == W || theDoorType == NW || theDoorType == EW || theDoorType == NESW ||
                theDoorType == SW || theDoorType == NSW || theDoorType == NEW || theDoorType == SEW)
                || (theAdjDoorType == E || theAdjDoorType == NE || theAdjDoorType == SE || theAdjDoorType == EW ||
                theAdjDoorType == NSE || theAdjDoorType == NEW || theAdjDoorType == SEW)) {
            // If we went WEST from the main room, we need to check if the main room
            // contains any WEST doors and/or if the adjacent room contains any EAST ones
            connected = true;
        } else if (theDirection == SOUTH && (theDoorType == S || theDoorType == NS || theDoorType == SE ||
                theDoorType == NESW || theDoorType == SW || theDoorType == NSE || theDoorType == NSW || theDoorType == SEW)
                || (theAdjDoorType == N || theAdjDoorType == NS || theAdjDoorType == NE || theAdjDoorType == NW ||
                theAdjDoorType == NSE || theAdjDoorType == NSW || theAdjDoorType == NEW)) {
            // If we went SOUTH from the main room, we need to check if the main room
            // contains any SOUTH doors and/or if the adjacent room contains any NORTH ones
            connected = true;
        } else if (theDirection == EAST && (theDoorType == E || theDoorType == NE || theDoorType == SE ||
                theDoorType == NESW || theDoorType == EW || theDoorType == NSE || theDoorType == NEW || theDoorType == SEW)
                || (theAdjDoorType == W || theAdjDoorType == NW || theAdjDoorType == EW || theAdjDoorType == SW ||
                theAdjDoorType == NSW || theAdjDoorType == NEW || theAdjDoorType == SEW)) {
            // If we went EAST from the main room, we need to check if the main room
            // contains any EAST doors and/or if the adjacent room contains any WEST ones
            connected = true;
        } else if (theDirection == NORTH && (theDoorType == N || theDoorType == NS || theDoorType == NE ||
                theDoorType == NESW || theDoorType == NW || theDoorType == NSE || theDoorType == NSW || theDoorType == NEW)
                || (theAdjDoorType == S || theAdjDoorType == NS || theAdjDoorType == SE || theAdjDoorType == SW ||
                theAdjDoorType == NSE || theAdjDoorType == NSW || theAdjDoorType == SEW)) {
            // If we went NORTH from the main room, we need to check if the main room
            // contains any NORTH doors and/or if the adjacent room contains any SOUTH ones
            connected = true;
        }
        return connected;
    }

    /**
     * Helper method used by 'connectRooms' to get the opposite direction
     * based on the direction passed in.
     *
     * @param theDirection The direction we want to get the opposite of.
     * @return Returns the opposite direction of 'theDirection'.
     */
    private int getOppositeDirection(final int theDirection) {
        // Since theDirection: 0 - W, 1 - S, 2 - E, 3 - N, adding 2 gets the opposite direction
        // % 4 ensures we stay within the range of directions
        return (theDirection + 2) % 4;
    }

    /**
     * Helper method used by 'connectRooms' to change the given door type of room.
     *
     * @param theRow        The row the room is in.
     * @param theCol        The column the room is in.
     * @param theDirection  The direction a new door was added in.
     * @param theDoorType   The door type the room currently has.
     */
    private void changeDoorType(final int theRow, final int theCol, final int theDirection, final int theDoorType) {
        // We need to figure out what door type to change a room to
        if (theDirection == WEST) { // West
            switch(theDoorType) {
                case NESW, W, NW, EW, SW, NSW, NEW, SEW:
                    break;
                case N:
                    myDoors[theRow][theCol] = NW;
                    break;
                case E:
                    myDoors[theRow][theCol] = EW;
                    break;
                case S:
                    myDoors[theRow][theCol] = SW;
                    break;
                case NS:
                    myDoors[theRow][theCol] = NSW;
                    break;
                case NE:
                    myDoors[theRow][theCol] = NEW;
                    break;
                case SE:
                    myDoors[theRow][theCol] = SEW;
                    break;
                case NSE:
                    myDoors[theRow][theCol] = NESW;
                    break;
            }
        } else if (theDirection == SOUTH) { // South
            switch (theDoorType) {
                case NESW, S, NS, SE, SW, NSE, NSW, SEW:
                    break;
                case N:
                    myDoors[theRow][theCol] = NS;
                    break;
                case E:
                    myDoors[theRow][theCol] = SE;
                    break;
                case W:
                    myDoors[theRow][theCol] = SW;
                    break;
                case NE:
                    myDoors[theRow][theCol] = NSE;
                    break;
                case NW:
                    myDoors[theRow][theCol] = NSW;
                    break;
                case EW:
                    myDoors[theRow][theCol] = SEW;
                    break;
                case NEW:
                    myDoors[theRow][theCol] = NESW;
                    break;
            }
        } else if (theDirection == EAST) { // East
            switch (theDoorType) {
                case NESW, E, NE, SE, EW, NSE, NEW, SEW:
                    break;
                case N:
                    myDoors[theRow][theCol] = NE;
                    break;
                case S:
                    myDoors[theRow][theCol] = SE;
                    break;
                case W:
                    myDoors[theRow][theCol] = EW;
                    break;
                case NS:
                    myDoors[theRow][theCol] = NSE;
                    break;
                case NW:
                    myDoors[theRow][theCol] = NEW;
                    break;
                case SW:
                    myDoors[theRow][theCol] = SEW;
                    break;
                case NSW:
                    myDoors[theRow][theCol] = NESW;
                    break;
            }
        } else if (theDirection == NORTH) { // North
            switch (theDoorType) {
                case NESW, N, NS, NE, NW, NSE, NSW, NEW:
                    break;
                case E:
                    myDoors[theRow][theCol] = NE;
                    break;
                case S:
                    myDoors[theRow][theCol] = NS;
                    break;
                case W:
                    myDoors[theRow][theCol] = NW;
                    break;
                case SE:
                    myDoors[theRow][theCol] = NSE;
                    break;
                case EW:
                    myDoors[theRow][theCol] = NEW;
                    break;
                case SW:
                    myDoors[theRow][theCol] = NSW;
                    break;
                case SEW:
                    myDoors[theRow][theCol] = NESW;
                    break;
            }
        }
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
        // We need to check if the room is in a corner or on a wall of the dungeon
        if (theRow == FIRST_ROOM_ROW && theCol == FIRST_ROOM_COL) { // Upper left corner
            // Since we are boxed in by two walls, we can only choose from
            // an east or south door or both
            int[] directions = {E, S, SE};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == FIRST_ROOM_ROW && theCol == LAST_ROOM_COL) { // Upper right corner
            // We can only have S, W, SW
            int[] directions = {S, W, SW};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == LAST_ROOM_ROW && theCol == FIRST_ROOM_COL) { // Lower left corner
            // We can only have North or East or both
            int[] directions = {N, E, NE};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == LAST_ROOM_ROW && theCol == LAST_ROOM_COL) { // Lower right corner
            // We can only have North or West or both
            int[] directions = {N, W, NW};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if ((FIRST_ROOM_ROW < theRow && theRow < LAST_ROOM_ROW) && theCol == FIRST_ROOM_COL) { // Left wall
            // We can only have N, E, S, NE, NS, SE, or NSE
            int[] directions = {N, E, S, NS, NE, SE, NSE};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if ((FIRST_ROOM_ROW < theRow && theRow < LAST_ROOM_ROW) && theCol == LAST_ROOM_COL) { // Right wall
            // We can only have N, S, W, NS, NW, SW, NSW
            int[] directions = {N, S, W, NS, NW, SW, NSW};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == FIRST_ROOM_ROW && (FIRST_ROOM_COL < theCol && theCol < LAST_ROOM_COL)) { // Top wall
            // We can only have E, S, W, SE, EW, SW, SEW
            int[] directions = {E, S, W, SE, EW, SW, SEW};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == LAST_ROOM_ROW && (FIRST_ROOM_COL < theCol && theCol < LAST_ROOM_COL)) { // Bottom wall
            // We can only have N, W, E, NE, NW, EW, NEW
            int[] directions = {N, W, E, NE, NW, EW, NEW};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else { // Finally, if the room is not in a corner or a wall we go here
            int[] directions = {NESW, N, E, S, W, NS, NE, NW, SE, EW, SW, NSE, NSW, NEW, SEW};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        }
        // We want to add this door type to the 'myDoors' array, so we can connect them later
        myDoors[theRow][theCol] = randomDoor;
        // Finally, we return the door type.
        return randomDoor;
    }

    /**
     * Uses 'toString' to create a text representation of the dungeon
     * and then outputs that to a text file.
     *
     * @param theFileName The name of the file to output to.
     */
    public void textDungeon(final String theFileName) {
        try {
            FileWriter fileWriter = new FileWriter(theFileName);
            System.out.println("code reaches here");
            myTextFile = toString();
            fileWriter.write(myTextFile);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred when printing to output file: " + e.getMessage());
        }
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
            StringBuilder[] roomStrings = new StringBuilder[7];
            for (int i = 0; i < 7; i++) {
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
                    for (int i = 0; i < 7; i++) {
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
     * Get method to get the array that represents the dungeon.
     *
     * @return The 'myRooms' 2D Room array.
     */
    public Room[][] getRooms(){
        return myRooms;
    }

    /**
     * Gets the array of doors that indicate how many doors each room has.
     *
     * @return The 2D integer array of door types.
     */
    public int[][] getDoors() {
        return myDoors;
    }

    /**
     * Get method to get the array that represents all the visited rooms in the dungeon.
     *
     * @return The 2D array of visited rooms in the dungeon.
     */
    public boolean[][] getVisitedRooms() {
        return myVisited;
    }

    /**
     * Get method to get the height of the dungeon.
     *
     * @return Returns the dungeon height.
     */
    public int getDungeonHeight(){
        return DUNGEON_HEIGHT;
    }

    /**
     * Get method to get the width of the dungeon.
     *
     * @return Returns the dungeon width.
     */
    public int getDungeonWidth() {
        return DUNGEON_WIDTH;
    }

    /**
     * Get the text file (String) associated with the dungeon.
     *
     * @return The text file as a String.
     */
    public String getTextFile() {
        return myTextFile;
    }

    /**
     * Sets the text file to the input text file.
     *
     * @param theTextFile The input text file.
     */
    public void setTextFile(final String theTextFile) {
        myTextFile = theTextFile;
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
