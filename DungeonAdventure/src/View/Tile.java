package View;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private transient BufferedImage myImage;
    private boolean myCollision = false;

    public BufferedImage getMyImage() {
        return myImage;
    }

    public boolean getMyCollision() {
        return myCollision;
    }

    public void setMyImage(final BufferedImage theImage) {
        myImage = theImage;
    }

    public void setMyCollision(final boolean theCollision) {
        myCollision = theCollision;
    }
}
