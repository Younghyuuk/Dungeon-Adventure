package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * This class represents the randomly generated dungeon.
 */
public class Dungeon implements Serializable {
    /**
     * A 2D array of all the rooms in the dungeon.
     */
    private Room[][] myRooms;
    /**
     * A 2D array of all the visited rooms in the dungeon. <br>
     * A cell in the array that is true means that we have visited that room.
     */
    private boolean[][] myVisited;
    /**
     * A 2D array that contains the doors for each room.
     */
    private int[][] myDoors;
    /**
     * The file to output the text version of the dungeon to.
     */
    private final String TEXT_DUNGEON = "Resources/map/dungeon.txt";
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
     * Constructs the randomly generated dungeon.
     */
    public Dungeon() {
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
        // Next, we need to create every room
        createRooms();
        // Finally, we will output the dungeon to a text file to use with the GUI
        textDungeon(TEXT_DUNGEON);
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
            Room newRoom = new Room(randomItem, row, col, doorType);
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
    private boolean isValid(final int theRow, final int theCol) {
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
    private void setMyVisited() {
        for (int i = 0; i < DUNGEON_HEIGHT; i++) {
            Arrays.fill(myVisited[i], false);
        }
    }

    /**
     * Helper method to initialize 'myDoors'.
     */
    private void setMyDoors() {
        for (int i = 0; i < DUNGEON_HEIGHT; i++) {
            Arrays.fill(myDoors[i], -1);
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
        // All 4 - 0, North - 1, East - 2, South - 3, West - 4, NS - 5, NE - 6, NW - 7, ES - 8, EW - 9,
        // SW - 10, NSE - 11, NSW - 12, NEW - 13, SEW - 14
        // theDirection: 0 - W, 1 - S, 2 - E, 3 - N
        boolean connected = false;
        // We have the door types for both rooms, so we can simply check them against each other
        if (theDirection == 0 && (theDoorType == 4 || theDoorType == 7 || theDoorType == 9 || theDoorType == 0 ||
                theDoorType == 10 || theDoorType == 12 || theDoorType == 13 || theDoorType == 14)
                || (theAdjDoorType == 2 || theAdjDoorType == 6 || theAdjDoorType == 8 || theAdjDoorType == 9 ||
                theAdjDoorType == 11 || theAdjDoorType == 13 || theAdjDoorType == 14)) {
            // If we went WEST from the main room, we need to check if the main room
            // contains any WEST doors and/or if the adjacent room contains any EAST ones
            connected = true;
        } else if (theDirection == 1 && (theDoorType == 3 || theDoorType == 5 || theDoorType == 8 ||
                theDoorType == 0 || theDoorType == 10 || theDoorType == 11 || theDoorType == 12 || theDoorType == 14)
                || (theAdjDoorType == 1 || theAdjDoorType == 5 || theAdjDoorType == 6 || theAdjDoorType == 7 ||
                theAdjDoorType == 11 || theAdjDoorType == 12 || theAdjDoorType == 13)) {
            // If we went SOUTH from the main room, we need to check if the main room
            // contains any SOUTH doors and/or if the adjacent room contains any NORTH ones
            connected = true;
        } else if (theDirection == 2 && (theDoorType == 2 || theDoorType == 6 || theDoorType == 8 ||
                theDoorType == 0 || theDoorType == 9 || theDoorType == 11 || theDoorType == 13 || theDoorType == 14)
                || (theAdjDoorType == 4 || theAdjDoorType == 7 || theAdjDoorType == 9 || theAdjDoorType == 10 ||
                theAdjDoorType == 12 || theAdjDoorType == 13 || theAdjDoorType == 14)) {
            // If we went EAST from the main room, we need to check if the main room
            // contains any EAST doors and/or if the adjacent room contains any WEST ones
            connected = true;
        } else if (theDirection == 3 && (theDoorType == 1 || theDoorType == 5 || theDoorType == 6 ||
                theDoorType == 0 || theDoorType == 7 || theDoorType == 11 || theDoorType == 12 || theDoorType == 13)
                || (theAdjDoorType == 3 || theAdjDoorType == 5 || theAdjDoorType == 8 || theAdjDoorType == 10 ||
                theAdjDoorType == 11 || theAdjDoorType == 12 || theAdjDoorType == 14)) {
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
     * Helper method used by 'connectRooms' to change the given door type of a room.
     *
     * @param theRow        The row the room is in.
     * @param theCol        The column the room is in.
     * @param theDirection  The direction a new door was added in.
     * @param theDoorType   The door type the room currently has.
     */
    private void changeDoorType(final int theRow, final int theCol, final int theDirection, final int theDoorType) {
        // All 4 - 0, North - 1, East - 2, South - 3, West - 4, NS - 5, NE - 6, NW - 7, SE - 8, EW - 9,
        // SW - 10, NSE - 11, NSW - 12, NEW - 13, SEW - 14
        // theDirection: 0 - W, 1 - S, 2 - E, 3 - N

        // We need to figure out what door type to change a room to
        if (theDirection == 0) { // West
            switch(theDoorType) {
                case 0, 4, 7, 9, 10, 12, 13, 14:
                    break;
                case 1:
                    myDoors[theRow][theCol] = 7;
                    break;
                case 2:
                    myDoors[theRow][theCol] = 9;
                    break;
                case 3:
                    myDoors[theRow][theCol] = 10;
                    break;
                case 5:
                    myDoors[theRow][theCol] = 12;
                    break;
                case 6:
                    myDoors[theRow][theCol] = 13;
                    break;
                case 8:
                    myDoors[theRow][theCol] = 14;
                    break;
                case 11:
                    myDoors[theRow][theCol] = 0;
                    break;
            }
        } else if (theDirection == 1) { // South
            switch (theDoorType) {
                case 0, 3, 5, 8, 10, 11, 12, 14:
                    break;
                case 1:
                    myDoors[theRow][theCol] = 5;
                    break;
                case 2:
                    myDoors[theRow][theCol] = 8;
                    break;
                case 4:
                    myDoors[theRow][theCol] = 10;
                    break;
                case 6:
                    myDoors[theRow][theCol] = 11;
                    break;
                case 7:
                    myDoors[theRow][theCol] = 12;
                    break;
                case 9:
                    myDoors[theRow][theCol] = 14;
                    break;
                case 13:
                    myDoors[theRow][theCol] = 0;
                    break;
            }
        } else if (theDirection == 2) { // East
            switch (theDoorType) {
                case 0, 2, 6, 8, 9, 11, 13, 14:
                    break;
                case 1:
                    myDoors[theRow][theCol] = 6;
                    break;
                case 3:
                    myDoors[theRow][theCol] = 8;
                    break;
                case 4:
                    myDoors[theRow][theCol] = 9;
                    break;
                case 5:
                    myDoors[theRow][theCol] = 11;
                    break;
                case 7:
                    myDoors[theRow][theCol] = 13;
                    break;
                case 10:
                    myDoors[theRow][theCol] = 14;
                    break;
                case 12:
                    myDoors[theRow][theCol] = 0;
                    break;
            }
        } else if (theDirection == 3) { // North
            switch (theDoorType) {
                case 0, 1, 5, 6, 7, 11, 12, 13:
                    break;
                case 2:
                    myDoors[theRow][theCol] = 6;
                    break;
                case 3:
                    myDoors[theRow][theCol] = 5;
                    break;
                case 4:
                    myDoors[theRow][theCol] = 7;
                    break;
                case 8:
                    myDoors[theRow][theCol] = 11;
                    break;
                case 9:
                    myDoors[theRow][theCol] = 13;
                    break;
                case 10:
                    myDoors[theRow][theCol] = 12;
                    break;
                case 14:
                    myDoors[theRow][theCol] = 0;
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
        // All 4 - 0, North - 1, East - 2, South - 3, West - 4, NS - 5, NE - 6, NW - 7, ES - 8, EW - 9,
        // SW - 10, NSE - 11, NSW - 12, NEW - 13, SEW - 14
        // We need to check if the room is in a corner or on a wall of the dungeon
        if (theRow == 0 && theCol == 0) { // Upper left corner
            // Since we are boxed in by two walls, we can only choose from
            // an east or south door or both
            int[] directions = {2, 3, 8};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == 0 && theCol == LAST_ROOM_COL) { // Upper right corner
            // We can only have S, W, SW
            int[] directions = {3, 4, 10};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == LAST_ROOM_ROW && theCol == 0) { // Lower left corner
            // We can only have North or East or both
            int[] directions = {1, 2, 6};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == LAST_ROOM_ROW && theCol == LAST_ROOM_COL) { // Lower right corner
            // We can only have North or West or both
            int[] directions = {1, 4, 7};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if ((0 < theRow && theRow < LAST_ROOM_ROW) && theCol == 0) { // Left wall
            // We can only have N, E, S, NE, NS, SE, or NSE
            int[] directions = {1, 2, 3, 5, 6, 8, 11};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if ((0 < theRow && theRow < LAST_ROOM_ROW) && theCol == LAST_ROOM_COL) { // Right wall
            // We can only have N, S, W, NS, NW, SW, NSW
            int[] directions = {1, 3, 4, 5, 7, 10, 12};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == 0 && (0 < theCol && theCol < LAST_ROOM_COL)) { // Top wall
            // We can only have E, S, W, SE, EW, SW, SEW
            int[] directions = {2, 3, 4, 8, 9, 10, 14};
            // Next, we get a random index from the array
            int randomIndex = random.nextInt(directions.length);
            // Finally, we store the value for what doors there will be
            randomDoor = directions[randomIndex];
        } else if (theRow == LAST_ROOM_ROW && (0 < theCol && theCol < LAST_ROOM_COL)) { // Bottom wall
            // We can only have N, W, E, NE, NW, EW, NEW
            int[] directions = {1, 4, 2, 6, 7, 9, 13};
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
        // We want to add this door type to the 'myDoors' array, so we can connect them later
        myDoors[theRow][theCol] = randomDoor;
        // Finally, we return the door type.
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
//            String dungeon = this.toString();
            FileWriter fileWriter = new FileWriter(theFileName);
            fileWriter.write(this.toString());
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
