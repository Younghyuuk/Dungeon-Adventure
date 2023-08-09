package Model;

import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * An abstract class representing an item that could appear in a room.
 */
public abstract class Item {
    /**
     * The game panel to draw the item onto.
     */
    private GamePanel myGamePanel;
    /**
     * The world-x coordinate of the item.
     */
    private int myWorldXCoordinate;
    /**
     * The world-y coordinate of the item.
     */
    private int myWorldYCoordinate;
    /**
     * The Rectangle that acts as the item's hit box.
     */
    private Rectangle mySolidArea;
    /**
     * The image representing the current item.
     */
    private BufferedImage myItemImage;
    /**
     * Boolean that determines whether an item has been "found," or not. <br>
     * In other words: has the player collided with the item?
     */
    private boolean myFound;

    /**
     * Constructs an item.
     *

     * @param theGP          The 'GamePanel' to draw the item onto.
     * @param theWorldX      The world-x coordinate to draw the item at.
     * @param theWorldY      The world-y coordinate to draw the item at.

     */
    public Item(final GamePanel theGP, final int theWorldX, final int theWorldY) {
        myGamePanel = theGP;
        myWorldXCoordinate = theWorldX;
        myWorldYCoordinate = theWorldY;
        mySolidArea = new Rectangle(0, 0, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize());
        myFound = false;
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
     * Gets this item's hit box to be used in collision.
     *
     * @return The hit box of the item.
     */
    public Rectangle getMySolidArea() {
        return mySolidArea;
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

    /**
     * Gets whether the item has been picked up/ran into or not.
     *
     * @return True or false depending on if the player has collided with the item.
     */
    public boolean getFound() {
        return myFound;
    }

    /**
     * Sets whether the item has been picked up/collided with.
     *
     * @param theFound The boolean value to set 'myFound' to.
     */
    public void setFound(final boolean theFound) {
        myFound = theFound;
    }


    /**
     * Sets the image associated with the item.
     *
     * @param theImage The image to set 'myItemImage' to.
     */
    public void setImage(final BufferedImage theImage) {
//        if (theImage.toString().equals("myHealth_1")) {
            myItemImage = theImage;
//        }

    }

    /**
     * Resets the item's hit box.
     */
    public void resetSolidArea(){
        mySolidArea.x = 0;
        mySolidArea.y = 0;
    }

    /**
     * Draws the current item onto the game panel.
     *
     * @param theGraphics The 2D graphics object to draw the item with.
     */
    public void draw(final Graphics2D theGraphics) {
        int screenX = myWorldXCoordinate - myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX();
        int screenY = myWorldYCoordinate - myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY();

        if (myWorldXCoordinate + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldXCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleX() &&
                myWorldXCoordinate - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX() &&
                myWorldYCoordinate + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldYCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleY() &&
                myWorldYCoordinate - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY()) {

            theGraphics.drawImage(myItemImage, screenX, screenY, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        }
    }
}
