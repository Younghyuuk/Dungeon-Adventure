package View;

import java.awt.image.BufferedImage;

public class Tile {
    /**
     * The tile's image.
     */
    private transient BufferedImage myImage;
    /**
     * Boolean check to see if the tile has collision.
     */
    private boolean myCollision = false;

    /**
     * @return the tile's image.
     */
    public BufferedImage getMyImage() {
        return myImage;
    }

    /**
     * @return the tiles collision state.
     */
    public boolean getMyCollision() {
        return myCollision;
    }

    /**
     * Sets the tiles image.
     * @param theImage the image the tile should have.
     */
    public void setMyImage(final BufferedImage theImage) {
        myImage = theImage;
    }

    /**
     * Sets the collision state of the tile.
     * @param theCollision the collision state the tile should have.
     */
    public void setMyCollision(final boolean theCollision) {
        myCollision = theCollision;
    }
}
