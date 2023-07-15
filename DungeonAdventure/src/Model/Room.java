package Model;

import java.lang.reflect.Type;

/**
 * This class represents a single, randomly generated, room.
 */
public class Room {
    /**
     * A 2D array of Strings that will represent the room.
     */
    private String[][] myRoom;
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
     * The height of a room.
     */
    private static final int ROOM_HEIGHT = 16;
    /**
     * The width of a room.
     */
    private static final int ROOM_WIDTH = 12;

    /**
     * Constructs a randomly generated room. <br>
     * Takes in what will be in the room.
     *
     * @param theRandomRoomItem The random item(s) that the room will contain.
     */
    public Room(final RoomItem theRandomRoomItem) {
        // Set up the size of the room
        myRoom = new String[ROOM_HEIGHT][ROOM_WIDTH];
        // Next, we will pass in the random item generated from 'Dungeon'
        createRoom(theRandomRoomItem);
    }

    /**
     * Creates a randomly generated room that will only contain one item
     *
     * @param theItem The random item(s) that could be in the room.
     */
    protected void createRoom(final RoomItem theItem) {

    }

    /**
     * Get method to get the amount of health the health potion will give.
     */
    public void getHealth() {

    }

    /**
     * @return The String representation of the room.
     */
    @Override
    public String toString() {
        return null;
    }

}
