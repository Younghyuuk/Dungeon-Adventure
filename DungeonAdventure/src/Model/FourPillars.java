package Model;

import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class FourPillars {

    BufferedImage abstraction,encapsulation,inheritance,polymorphism;
    int myWorldXCoordinate;
    int myWorldYCoordinate;
    String myName;
    GamePanel myGamePanel;
    boolean myFound = false;
    public Rectangle mySolidArea;

    FourPillars(int theXCoordinates, int theYCoordinates, String theName, GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
        myName = theName;
        myWorldXCoordinate = theXCoordinates;
        myWorldYCoordinate = theYCoordinates;
        mySolidArea = new Rectangle(0, 0, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize());
        getPillarsImage();
    }
    public Rectangle getMySolidArea() {
        return mySolidArea;
    }
    public void resetSolidArea(){
        mySolidArea.x = 0;
        mySolidArea.y = 0;
    }
    public void getPillarsImage() {
        try {
            abstraction = read(Objects.requireNonNull(getClass().getResourceAsStream("/fourPillars/a.png")));
            encapsulation = read(Objects.requireNonNull(getClass().getResourceAsStream("/fourPillars/e.png")));
            inheritance = read(Objects.requireNonNull(getClass().getResourceAsStream("/fourPillars/i.png")));
            polymorphism = read(Objects.requireNonNull(getClass().getResourceAsStream("/fourPillars/p.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D theGraphics){
        int screenX = myWorldXCoordinate - myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX();
        int screenY = myWorldYCoordinate - myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY();

        if (myWorldXCoordinate + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldXCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleX() &&
                myWorldXCoordinate - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX() &&
                myWorldYCoordinate + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldYCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleY() &&
                myWorldYCoordinate - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY()) {
            BufferedImage image = switch (myName) {
                case "a" -> abstraction;
                case "e" -> encapsulation;
                case "i" -> inheritance;
                case "p" -> polymorphism;
                default -> null;
            };
            theGraphics.drawImage(image, screenX, screenY, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        }
    }
    public int getMyWorldXCoordinate(){
        return myWorldXCoordinate;
    }
    public int getMyWorldYCoordinate(){
        return myWorldYCoordinate;
    }
    public boolean getFound(){
        return myFound;
    }
    public void setFound(boolean theFound) {
        myFound = theFound;
    }
}
