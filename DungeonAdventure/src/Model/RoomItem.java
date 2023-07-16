package Model;

/**
 * Enum class that represents the items that can be found in a room in the dungeon.
 */
public enum RoomItem {
    /**
     * Represents a healing potion to restore player health.
     * Restores between 5-15 health.
     */
    HEALING_POTION("H"),
    /**
     * Represents a vision potion that allows the player to see 8 rooms
     * around and including the room they are currently in.
     */
    VISION_POTION("V"),
    /**
     * Represents multiple items being in a room (a health potion, a vision potion, and a pit).
     */
    MULTIPLE_ITEMS("M"),
    /**
     * Represents a pit that will deal between 1-20 hit points to the player.
     */
    PIT("X"),
    /**
     * Represents an entrance. Nothing else can be in the same room as this.
     */
    ENTRANCE("i"),
    /**
     * Represents an exit. Nothing else can be in the same room as this.
     */
    EXIT("O"),
    /**
     * Represents the Pillar of Abstraction. Can not be in the same room as another pillar.
     */
    PILLAR_A("A"),
    /**
     * Represents the Pillar of Encapsulation. Can not be in the same room as another pillar.
     */
    PILLAR_E("E"),
    /**
     * Represents the Pillar of Inheritance. Can not be in the same room as another pillar.
     */
    PILLAR_I("I"),
    /**
     * Represents the Pillar of Polymorphism. Can not be in the same room as another pillar.
     */
    PILLAR_P("P"),
    /**
     * Represents an empty room.
     */
    EMPTY(" ");

    /**
     * String value that represents what is assigned to a specific enum.
     */
    private final String value;

    /**
     * Constructor to set the value of each enum.<br>
     * For example, "V" for 'VISION_POTION'.
     *
     * @param theValue The value to set for a given enum.
     */
    RoomItem(final String theValue) {
        value = theValue;
    }

    /**
     * Get method to get the value associated with a specific enum.
     *
     * @return The value of a given enum.
     */
    public String getValue() {
        return value;
    }
}
