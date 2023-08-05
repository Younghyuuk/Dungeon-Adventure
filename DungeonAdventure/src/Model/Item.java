package Model;

import View.GamePanel;

import java.awt.*;

/**
 * An abstract class representing an item that could appear in a room.
 */
public abstract class Item {
    /**
     * The game panel to draw the item onto.
     */
    private GamePanel myGamePanel;
    /**
     * The chance the item will spawn.
     */
    private double mySpawnChance;
    /**
     * The world-x coordinate of the item.
     */
    private int myWorldXCoordinate;
    /**
     * The world-y coordinate of the item.
     */
    private int myWorldYCoordinate;
    /**
     * The item this item is.
     */
    private String myItem;
    /**
     * The Rectangle that acts as the item's hit box.
     */
    private Rectangle myHitBox;

    /**
     * Constructs an item.
     *
     * @param theGP          The 'GamePanel' to draw the item onto.
     * @param theWorldX      The world-x coordinate to draw the item at.
     * @param theWorldY      The world-y coordinate to draw the item at.
     * @param theItem        The type of item this item will be.
     * @param theSpawnChance The chance the item will spawn.
     */
    public Item(final GamePanel theGP, final int theWorldX, final int theWorldY,
                                final String theItem, final double theSpawnChance) {
        myGamePanel = theGP;
        myWorldXCoordinate = theWorldX;
        myWorldYCoordinate = theWorldY;
        myItem = theItem;
        mySpawnChance = theSpawnChance;
    }

    /**
     * Gets the images associated with the specific item.
     */
    public abstract void getItemImage();

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
