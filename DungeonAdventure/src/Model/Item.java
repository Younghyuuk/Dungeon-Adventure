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
    public Item(final int theItem, final double theSpawnChance, final int theRow, final int theCol,
                                                                final Dungeon theDungeon) {
        // First set up the spawnLocation array
        int height = theDungeon.getDungeonHeight() * theDungeon.getDungeonHeight();
        int width = theDungeon.getDungeonWidth() * theDungeon.getDungeonWidth();
        spawnLocation = new int[height][width];
        // Then set up everything else
        spawnChance = theSpawnChance;
        spawnLocation[theRow][theCol] = theItem;
    }

    /**
     * Gets the images associated with the specific item.
     */
    public void getItemImage() {}

    /**
     * Sets the spawn location of an item in the world.
     */
    public void setSpawnLocation(final int theRow, final int theCol) {}

    /**
     * Gets the 2D array of all spawn locations of every item.
     *
     * @return Returns the array of spawn locations.
     */
    public int[][] getSpawnLocations() {
        return spawnLocation;
    }
}
