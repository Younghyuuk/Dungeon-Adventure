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
     * @param theRandomRoomItem The random item(s) that the room will contain.
     * @param theX The X position this room is being added to in 'myRooms' in 'Dungeon'.
     * @param theY The Y position this room is being added to in 'myRooms' in 'Dungeon'.
     * @param theDoorType The previous location of the door in the previous room.
     */
    public Room(final RoomItem theRandomRoomItem, final int theX, final int theY, final int theDoorType) {
        // Set up the size of the room
        myRoom = new String[ROOM_HEIGHT][ROOM_WIDTH];
        // Next, we will pass in the random item generated from 'Dungeon'
        createRoom(theRandomRoomItem, theX, theY, theDoorType);
        // We also want to generate the health obtainable from this room's health potion
        if (theRandomRoomItem.getValue().equals("H")) {
            Random random = new Random();
            myHealth = random.nextInt(HEALTH_MAX - HEALTH_MIN + 1) + HEALTH_MIN;
        }
    }

    /**
     * Creates a randomly generated room that will contain a random item (or items).
     *
     * @param theItem The random item(s) that could be in the room.
     */
    protected void createRoom(final RoomItem theItem, final int theX, final int theY, final int theDoorType) {
        // All 4 - 0, North - 1, East - 2, South - 3, West - 4, NS - 5, NE - 6, NW - 7, ES - 8, EW - 9,
        // SW - 10, NSE - 11, NSW - 12, NEW - 13, SEW - 14
        // First, we will create a random generator to randomly decide where doors are
        Random random = new Random();
        // We need to check if the room is in a corner or on a wall of the dungeon
        if (theX == 0 && theY == 0) { // Upper left corner
            // Since we are boxed in by two walls, we can only choose from
            // an east or south door or both

        } else if (theX == 8 && theY == 0) { // Upper right corner

        } else if (theX == 0 && theY == 8) { // Lower left corner

        } else if (theX == 8 && theY == 8) { // Lower right corner

        } else if (theX == 0 && (1 <= theY && theY <= 7)) { // Left wall

        } else if (theX == 8 && (1 <= theY && theY <= 7)) { // Right wall

        } else if (theY == 0 && (1 <= theX && theX <= 7)) { // Top wall

        } else if (theY == 8 && (1 <= theX && theX <= 7)) { // Bottom wall

        } else { // Finally, if the room is not in a corner or on a wall we go here

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
     * @param theX The X position of this room in 'Dungeon'.
     * @param theY The Y position of this room in 'Dungeon'.
     * @return The door type associated with the specified room in 'Dungeon'.
     */
    public int getDoors(final int theX, final int theY) {
        return doors[theX][theY];
    }

    /**
     * @return The String representation of the room.
     */
    @Override
    public String toString() {
        return null;
    }

}
