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
     * The randomly generated pillar chosen from A, E, I, P. That could be in the room.
     */
    private String myPillar;
    /**
     * The String representation of a pit.
     */
    private static final String PIT = "X";
    /**
     * The String representation of an entrance.
     */
    private static final String ENTRANCE = "i";
    /**
     * The String representation of an exit.
     */
    private static final String EXIT = "O";
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
     * @param theHealth Boolean to determine if the room will contain a health potion.
     * @param thePit Boolean to determine if the room will contain a pit.
     * @param theEntrance Boolean to determine if the room will contain an entrance.
     * @param theExit Boolean to determine if the room will contain an exit.
     * @param thePillar Boolean to determine if the room will contain a pillar.
     * @param theMultiple Boolean to determine if the room will contain a health potion, vision potion, and pit.
     */
    public Room(final boolean theHealth, final boolean thePit, final boolean theEntrance,
                final boolean theExit, final boolean thePillar, final boolean theMultiple) {
        // Set up the size of the room
        myRoom = new String[ROOM_HEIGHT][ROOM_WIDTH];
    }

    /**
     * Creates the randomly generated room based on what will be inside of it.
     *
     * @param theItem The random thing that could be in the room.
     */
    protected void createRoom(final Type theItem) {

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
