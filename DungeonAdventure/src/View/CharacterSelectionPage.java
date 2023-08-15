package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class CharacterSelectionPage {
    /**
     * The main game panel.
     */
    private final GamePanel myGamePanel;
    /**
     * An image of a thief.
     */
    private transient BufferedImage myThiefImage;
    /**
     * An image of a warrior.
     */
    private transient BufferedImage myWarriorImage;
    /**
     * An image of a priest.
     */
    private transient BufferedImage myPriestImage;
    /**
     * The pen that is used to draw.
     */
    private Graphics2D myPen;
    /**
     * Integer that represents what command we are on.
     */
    private int myCommandNum = 0;

    /**
     * A constructor that creates a Character selection page object.
     * @param theGamePanel hte main game panel.
     */
    public CharacterSelectionPage(GamePanel theGamePanel){
        myGamePanel = theGamePanel;

    }

    /**
     * A method that draws the character selector page.
     * @param theGraphics the pen that is used to draw.
     */
    public void draw(Graphics2D theGraphics){
        myPen = theGraphics;
        setMyImages();
        selectCharacter();
    }

    /**
     * Method that draws the select character page.
     */
    public void selectCharacter(){
        int x;
        int y = myGamePanel.getSpriteSize() * 3;
        // Make the JPanels background black
        myPen.setColor(Color.DARK_GRAY);
        myPen.fillRect(0,0, myGamePanel.getMyScreenWidth(), myGamePanel.getMyScreenHeight());
        myPen.setFont(myPen.getFont().deriveFont(Font.BOLD,69F));

        String title = "Select Your Hero";
        x = getXToCenterString(title);

        // Title String Shadow
        myPen.setColor(Color.BLACK);
        myPen.drawString(title, x+5,y+5);
        // Title String
        myPen.setColor(Color.white);
        myPen.drawString(title, x,y);

        // Images for HeroPage
        myPen.drawImage(myThiefImage,(myGamePanel.getMyScreenWidth()/2) - (4* myGamePanel.getSpriteSize()),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);
        myPen.drawImage(myWarriorImage,(myGamePanel.getMyScreenWidth()/2) - myGamePanel.getSpriteSize(),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);
        myPen.drawImage(myPriestImage,(myGamePanel.getMyScreenWidth()/2) + (2*myGamePanel.getSpriteSize()),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);

        if (myCommandNum == 0){
            myPen.drawString("^",(myGamePanel.getMyScreenWidth()/2) - (4 * myGamePanel.getSpriteSize() - myGamePanel.getSpriteSize()/2),400);
        } else if (myCommandNum == 1) {
            myPen.drawString("^",(myGamePanel.getMyScreenWidth()/2) - (myGamePanel.getSpriteSize() - myGamePanel.getSpriteSize()/2),400);
        } else {
            myPen.drawString("^",(myGamePanel.getMyScreenWidth()/2) + (2 * myGamePanel.getSpriteSize() + myGamePanel.getSpriteSize()/2),400);
        }
    }

    /**
     * A method that helps center a string on a panel.
     * @param theText The string that needs to be centered.
     * @return A x coordinate that centers the string on a panel.
     */
    public int getXToCenterString(String theText){
        int length = (int) myPen.getFontMetrics().getStringBounds(theText,myPen).getWidth();
        int x = myGamePanel.getMyScreenWidth()/2 - length/2;
        return x;
    }

    /**
     * A method that sets the images.
     */
    public void setMyImages() {
        try {
            myThiefImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_down1.png")));
            myWarriorImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_down1.png")));
            myPriestImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_down1.png")));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A method that increments command field.
     */
    public void incMyCommandNum() {
        myCommandNum++;
    }

    /**
     * A method that decrements the command field.
     */
    public void decMyCommandNum() {
        myCommandNum--;
    }

    /**
     * Sets the command number to a number.
     * @param theNumber the number to set the command number to.
     */
    public void setMyCommandNum(int theNumber){
        myCommandNum = theNumber;
    }

    /**
     * A method that returns the command number.
     * @return the command number.
     */
    public int getMyCommandNum() {
        return myCommandNum;
    }
}
