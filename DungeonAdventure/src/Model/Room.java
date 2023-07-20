package Model;

import java.util.Random;

/**
 * This class represents a single, randomly generated, room.
 */
public class Room {
    /**
     * A 2D array of Strings that will represent the room.
     */
    private String[][] myRoom;
    /**
     * A 2D array of integers that stores the door types of every room that is added to the dungeon. <br>
     */
    private int[][] doors;
    /**
     * The amount of health the health pot (if in the room) will give. <br>
     * Ranges from 5-15 hit points.
     */
    private int myHealth;
    /**
     * The amount of damage that will be taken if the player falls into the pit (if in the room). <br>
     * Ranges from 1-20 hit points.
     */
    private int myPitDamage;
    /**
     * Represents the minimum amount of health obtainable from a health potion.
     */
    private static final int HEALTH_MIN = 5;
    /**
     * Represents the maximum amount of health obtainable from a health potion.
     */
    private static final int HEALTH_MAX = 15;
    /**
     * The height of a room.
     */
    private static final int ROOM_HEIGHT = 17;
    /**
     * The width of a room.
     */
    private static final int ROOM_WIDTH = 13;

    /**
     * Constructs a randomly generated room. <br>
     * Takes in what will be in the room.
     *
     * @param theRandomItem The random item(s) that the room will contain.
     * @param theRow        The row this room is being added to in 'myRooms' in 'Dungeon'.
     * @param theCol        The column this room is being added to in 'myRooms' in 'Dungeon'.
     * @param theDoorType   The door(s) that the room will contain.
     */
    public Room(final RoomItem theRandomItem, final int theRow, final int theCol, final int theDoorType) {
        // Set up the size of the room
        myRoom = new String[ROOM_HEIGHT][ROOM_WIDTH];
        // Next, we will pass in the random item generated from 'Dungeon'
        createRoom(theRandomItem, theRow, theCol, theDoorType);
        // We also want to generate the health obtainable from this room's health potion
//        if (theRandomItem.getValue().equals("H")) {
//            Random random = new Random();
//            myHealth = random.nextInt(HEALTH_MAX - HEALTH_MIN + 1) + HEALTH_MIN;
//        }
    }

    /**
     * Creates a randomly generated room that will contain a random item (or items).
     *
     * @param theItem The random item(s) that could be in the room.
     */
    protected void createRoom(final RoomItem theItem, final int theRow, final int theCol, final int theDoorType) {
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
        } else if (theDoorType == 1) {
            myRoom[0][ROOM_WIDTH / 2] = "3";
        } else if (theDoorType == 2) {
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3";
        } else if (theDoorType == 3) {
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3";
        } else if (theDoorType == 4) {
            myRoom[ROOM_HEIGHT / 2][0] = "3";
        } else if (theDoorType == 5) {
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
        } else if (theDoorType == 6) {
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
        } else if (theDoorType == 7) {
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        } else if (theDoorType == 8) {
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
        } else if (theDoorType == 9) {
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        } else if (theDoorType == 10) {
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        } else if (theDoorType == 11) {
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
        } else if (theDoorType == 12) {
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        } else if (theDoorType == 13) {
            myRoom[0][ROOM_WIDTH / 2] = "3"; // North
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        } else if (theDoorType == 14) {
            myRoom[ROOM_HEIGHT - 1][ROOM_WIDTH / 2] = "3"; // South
            myRoom[ROOM_HEIGHT / 2][ROOM_WIDTH - 1] = "3"; // East
            myRoom[ROOM_HEIGHT / 2][0] = "3"; // West
        }
    }

    /**
     * Get method to get the amount of health the health potion will give.
     *
     * @return Returns the amount of health obtainable from this room's health potion
     * (if it has one).
     */
    public int getHealth() {
        return myHealth;
    }

    /**
     * Get method to get the door types of a certain room in the dungeon.
     *
     * @param theRow The row of this room in 'Dungeon'.
     * @param theCol The column of this room in 'Dungeon'.
     * @return The door type associated with the specified room in 'Dungeon'.
     */
    public int getDoors(final int theRow, final int theCol) {
        return doors[theRow][theCol];
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
