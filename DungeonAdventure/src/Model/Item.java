package Model;

/**
 * An abstract class representing an item that could appear in a room.
 */
public abstract class Item {
    /**
     * The chance the item will spawn.
     */
    private double spawnChance;
    /**
     * The locations every item will spawn in, in a world.
     */
    private int[][] spawnLocation;
    /**
     * The world-x coordinate of the item.
     */
    private int myWorldXCoordinate;
    /**
     * The world-y coordinate of the item.
     */
    private int myWorldYCoordinate;

    /**
     * Constructs an item.
     *
     * @param theItem The type of item this item will be.
     * @param theSpawnChance The chance the item will spawn.
     * @param theDungeon The dungeon that this item is in.
     */
    public Item(final int theItem, final double theSpawnChance, final Dungeon theDungeon) {
        // First set up the spawnLocation array
        int height = theDungeon.getDungeonHeight() * theDungeon.getDungeonHeight();
        int width = theDungeon.getDungeonWidth() * theDungeon.getDungeonWidth();
        spawnLocation = new int[height][width];
        // Then set up everything else
        spawnChance = theSpawnChance;
//        spawnLocation[theRow][theCol] = theItem;
    }

    /**
     * Gets the images associated with the specific item.
     */
    public void getItemImage() {}

    /**
     * Sets the spawn location of an item in the world.
     *
     * @param theItem The item to put in the dungeon.
     * @param theRow The row to put the item in.
     * @param theCol The column to put the item in.
     */
    public void setSpawnLocation(final int theItem, final int theRow, final int theCol) {
        spawnLocation[theRow][theCol]  = theItem;
    }

    /**
     * Gets the 2D array of all spawn locations of every item.
     *
     * @return Returns the array of spawn locations.
     */
    public int[][] getSpawnLocations() {
        return spawnLocation;
    }

    /**
     * Gets the world-x coordinate of the item.
     *
     * @return The world-x coordinate of the item.
     */
    public int getMyWorldXCoordinate() {
        return myWorldXCoordinate;
    }

    /**
     * Gets the world-y coordinate of the item.
     *
     * @return The world-y coordinate of the item.
     */
    public int getMyWorldYCoordinate() {
        return myWorldYCoordinate;
    }

    /**
     * Sets the world-x coordinate of the item.
     *
     * @param theX The world-x coordinate to set this item's world-x to.
     */
    public void setMyWorldXCoordinate(final int theX) {
        myWorldXCoordinate = theX;
    }

    /**
     * Sets the world-y coordinate of the item.
     *
     * @param theY The world-y coordinate to set this item's world-y to.
     */
    public void setMyWorldYCoordinate(final int theY) {
        myWorldYCoordinate = theY;
    }
}
