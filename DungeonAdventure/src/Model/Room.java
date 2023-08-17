package Model;

import java.io.Serializable;


/**
 * This class represents a single, randomly generated, room.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public class Room implements Serializable {
    private static final long serialversionUID = 12345L;
    /**
     * A 2D array of Strings that will represent the room.
     */
    private String[][] myRoom;
    /**
     * The height of a room.
     */
    private static final int ROOM_HEIGHT = 7;
    /**
     * The width of a room.
     */
    private static final int ROOM_WIDTH = 7;

    /**
     * Constructs a randomly generated room. <br>
     * Takes in what will be in the room.
     *
     * @param theDoorType   The door(s) that the room will contain.
     */
    public Room(final int theDoorType) {
        // Set up the size of the room
        myRoom = new String[ROOM_HEIGHT][ROOM_WIDTH];
        // Next, we will pass in the random item generated from 'Dungeon'
        createRoom(theDoorType);
    }

    /**
     * Creates a randomly generated room that will contain a random item (or items).
     *
     * @param theDoorType   The door(s) that are being added to this room.
     */
    protected void createRoom(final int theDoorType) {
        // All 4 - 0, North - 1, East - 2, South - 3, West - 4, NS - 5, NE - 6, NW - 7, ES - 8, EW - 9,
        // SW - 10, NSE - 11, NSW - 12, NEW - 13, SEW - 14

        // First, we will create the base room with just walls and a floor
        for (int i = 0; i < ROOM_HEIGHT; i++) {
            for (int j = 0; j < ROOM_WIDTH; j++) {
                if (i == 0 || j == 0 || j == ROOM_WIDTH - 1 || i == ROOM_HEIGHT - 1) {
                    myRoom[i][j] = "1";
                } else {
                    myRoom[i][j] = "0";
                }
            }
        }
        // Then we add the doors
        // If we are adding all 4 doors, we do not have to figure out additional doors
        if (theDoorType == 0) {
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
        } else if (theDoorType == 1) { // North
            myRoom[0][ROOM_WIDTH / 2] = "3";
        } else if (theDoorType == 2) { // East
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3";
        } else if (theDoorType == 3) { // South
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3";
        } else if (theDoorType == 4) { // West
            myRoom[ROOM_HEIGHT / 2][0] = "3";
        } else if (theDoorType == 5) { // NS
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
        } else if (theDoorType == 6) { // NE
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
        } else if (theDoorType == 7) { // NW
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        } else if (theDoorType == 8) { // SE
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
        } else if (theDoorType == 9) { // EW
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        } else if (theDoorType == 10) { // SW
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        } else if (theDoorType == 11) { // NSE
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
        } else if (theDoorType == 12) { // NSW
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        } else if (theDoorType == 13) { // NEW
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        } else if (theDoorType == 14) { // SEW
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        }
    }

    /**
     * Adds a door based on the input direction.
     *
     * @param theDirection The direction to add a door to.
     */
    protected void addDoor(final int theDirection) {
        // theDirection: 0 - W, 1 - S, 2 - E, 3 - N
        if (theDirection == 0) { // West
            myRoom[ROOM_HEIGHT / 2][0] = "3";
        } else if (theDirection == 1) { // South
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3";
        } else if (theDirection == 2) { // East
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3";
        } else if (theDirection == 3) { // North
            myRoom[0][ROOM_WIDTH / 2] = "3";
        }
    }

    /**
     * Get method to get the room height.
     *
     * @return Returns the height of the room.
     */
    public int getRoomHeight() {
        return ROOM_HEIGHT;
    }

    /**
     * Get method to get the room width.
     *
     * @return Returns the width of the room.
     */
    public int getRoomWidth() {
        return ROOM_WIDTH;
    }

    /**
     * Gets the contents of the room.
     *
     * @return The contents of the room in a 2D string array.
     */
    public String[][] getRoom() {
        return myRoom;
    }

    /**
     * @return The String representation of the room.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ROOM_HEIGHT; i++) {
            for (int j = 0; j < ROOM_WIDTH; j++) {
                sb.append(myRoom[i][j]);
                sb.append(" ");
            }
            sb.append("\n"); // Move to the next line after each row
        }
        return sb.toString();
    }
}
