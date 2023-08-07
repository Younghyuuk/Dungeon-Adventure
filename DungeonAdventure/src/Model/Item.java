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
     * Constructs an item.
     *
     * @param theSpawnChance The chance the item will spawn.
     * @param theRow The row the item will spawn in.
     * @param theCol The column the item will spawn in.
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
    public abstract void getItemImage();

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
}
